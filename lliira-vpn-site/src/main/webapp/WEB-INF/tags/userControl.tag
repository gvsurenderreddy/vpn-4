<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="user-control ui-widget-content ui-corner-all">

	<s:if test="user != null">
		<%-- user logged in --%>
		<span class="name">${user.email}</span>
    <a class="button" href="<s:url action='profile' />">个人信息</a>
    <a class="button" href="<s:url action='account' />">帐户</a>
    
    <s:if test="%{'admin' in user.roles}">
      <a class="button" href="<s:url action='admin' />">管理员</a>
    </s:if>
    
    <a class="button" href="<s:url action='logout' />">退出</a>
	</s:if>

	<s:else>
		<%-- user not logged in --%>
		<s:form id="quick-login" action="login" theme="simple">
				邮件：
				<s:textfield name="email" label="Email" cssClass="email text-input" />
				密码：
				<s:password name="password" label="Password"
					cssClass="password text-input" />
				<a class="submit button">登　　录</a>
				<a class="forget-password button" 
				   href="<s:url action='forgetPassword!input' />">忘记密码</a>
		</s:form>
	</s:else>

</div>