/**
 * 
 */
package net.lliira.vpn.site.interceptor;

import java.util.Map;

import net.lliira.vpn.model.user.User;
import net.lliira.vpn.site.Constants;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author jerric
 * 
 */
public class AuthenticationInterceptor implements Interceptor {

  /**
   * 
   */
  private static final long serialVersionUID = 4291439244245847491L;

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
    if (user == null) return Constants.RESULT_LOGIN;
    else return actionInvocation.invoke();
  }

}
