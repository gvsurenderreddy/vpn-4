/**
 * 
 */
package net.lliira.vpn.site.interceptor;

import java.util.Collection;
import java.util.Map;

import net.lliira.vpn.model.admin.AdminFactory;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.site.Constants;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author jerric
 * 
 */
public class AdminInterceptor implements Interceptor {

  /**
   * 
   */
  private static final long serialVersionUID = -4911829376904736059L;

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
  public String intercept(ActionInvocation actionInvocation) throws Exception {
    Map<String, Object> session =
        actionInvocation.getInvocationContext().getSession();
    User user = (User) session.get(Constants.SESSION_USER);
    Collection<String> roles = user.getRoles();
    if (!roles.contains(AdminFactory.ROLE_ADMIN)) return Constants.RESULT_HOME;
    else return actionInvocation.invoke();
  }

}
