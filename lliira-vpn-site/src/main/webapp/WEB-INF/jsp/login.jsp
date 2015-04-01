<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="登录" section="account" />

<div class="section">
	<div class="login ui-widget-content ui-corner-all">
		<h3>登录网站</h3>
		<s:form action="login">
			<s:textfield name="email" cssClass="text-input" label="邮件地址" />
			<s:password name="password" cssClass="text-input" label="密码" />
			<div class="buttons">
			  <a class="button" href="<s:url action='forgetPassword' />">忘记密码</a>
				<a class="submit button">登　　录</a>
			</div>
		</s:form>
		<div class="more-option">
		  <div class="tooltip">如果您还没有帐户的话，可以点此免费注册：</div>
			 <a
				class="button" href="<s:url action='home' />">免费注册</a>
		</div>
	</div>
	<div class="help">
		<p>请使用您的注册Email与密码登录网站。</p>
		<p>成功登录后，您可以：</p>
		<ul>
			<li>修改用户信息及密码；</li>
			<li>查看VPN使用记录及流量余额；</li>
			<li>进行在线充值；</li>
		</ul>
	</div>
</div>



<vpn:footer />
