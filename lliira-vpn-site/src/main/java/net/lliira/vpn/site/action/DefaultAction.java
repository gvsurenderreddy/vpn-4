/**
 * 
 */
package net.lliira.vpn.site.action;

import java.util.List;

import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.user.Server;
import net.lliira.vpn.model.user.User;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author jerric
 * 
 */
public class DefaultAction extends ActionSupport {

  /**
   * 
   */
  private static final long serialVersionUID = -2417477450436356887L;

  protected User user;
  protected DaoSession session;

  protected String pageTitle;
  protected String pageMessage;
  protected List<Server> servers;

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user
   *          the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @param session
   *          the session to set
   */
  public void setSession(DaoSession session) {
    this.session = session;
  }

  /**
   * @return the servers
   */
  public List<Server> getServers() {
    return servers;
  }

  /**
   * @param servers
   *          the servers to set
   */
  public void setServers(List<Server> servers) {
    this.servers = servers;
  }

  /**
   * @return the pageTitle
   */
  public String getPageTitle() {
    return pageTitle;
  }

  /**
   * @return the pageMessage
   */
  public String getPageMessage() {
    return pageMessage;
  }

}
