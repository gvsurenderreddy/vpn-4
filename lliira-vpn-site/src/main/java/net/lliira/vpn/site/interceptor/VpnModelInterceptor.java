/**
 * 
 */
package net.lliira.vpn.site.interceptor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.lliira.vpn.model.VpnModel;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.user.DefaultUserFactory;
import net.lliira.vpn.model.user.Server;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author jerric
 * 
 */
public class VpnModelInterceptor implements Interceptor {

  /**
   * 
   */
  private static final long serialVersionUID = 7550546056541489975L;

  private static final Logger logger = Logger
      .getLogger(VpnModelInterceptor.class);

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
   */
  @Override
  public void destroy() {}

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
   */
  @Override
  public void init() {}

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony
   * .xwork2.ActionInvocation)
   */
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    logger.debug("entering VpnModelInterceptor...");
    Map<String, Object> context =
        invocation.getInvocationContext().getApplication();
    VpnModel vpnModel = getModel(context);
    DaoSession daoSession = vpnModel.openSession();

    Map<String, Object> appSession =
        invocation.getInvocationContext().getSession();
    User user = (User) appSession.get(Constants.SESSION_USER);
    Object action = invocation.getAction();

    try {
      // get the server list
      UserFactory userFactory = new DefaultUserFactory();
      List<Server> servers = userFactory.getServers(daoSession, user);
      logger.debug("Servers: " + servers.size());
      
      // set user to the action
      logger.debug("Setting user " + user);
      if (action instanceof DefaultAction) {
        DefaultAction abstractAction = (DefaultAction) action;
        abstractAction.setUser(user);
        abstractAction.setSession(daoSession);
        abstractAction.setServers(servers);
      }

      logger.debug("invoking action " + action.getClass().getName());
      String result = invocation.invoke();

      // retrieve the user, and put back into session
      logger.debug("Getting back user " + user);
      if (action instanceof DefaultAction) {
        DefaultAction abstractAction = (DefaultAction) action;
        user = abstractAction.getUser();
        if (user == null) appSession.remove(Constants.SESSION_USER);
        else appSession.put(Constants.SESSION_USER, user);
      }

      daoSession.commit();
      logger.debug("leaving VpnModelInterceptor... " + result);

      return result;
    } catch (Exception ex) {
      daoSession.rollback();
      logger.debug("leaving VpnModelInterceptor... " + ex);
      throw ex;
    } finally {
      daoSession.close();
    }
  }

  private synchronized VpnModel getModel(Map<String, Object> applicationContext)
      throws IOException {
    VpnModel vpnModel =
        (VpnModel) applicationContext
            .get(Constants.APPLICATION_SERVICE_FACTORY);
    if (vpnModel == null) {
      vpnModel = new VpnModel();
      applicationContext.put(Constants.APPLICATION_SERVICE_FACTORY, vpnModel);
      vpnModel.initialize();
    }
    return vpnModel;
  }

}
