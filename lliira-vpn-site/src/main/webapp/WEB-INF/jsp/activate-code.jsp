<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="获取激信息" section="home" />

<h1>获取激活信息</h1>

<div class="form-panel ui-widget-content ui-corner-all">
	<s:form action="sendActivationCode">
		<p>请输入您的注册邮件，我们会将您的激活信息发至您的信箱中。</p>
		<s:textfield name="email" cssClass="text-input" label="邮件地址" />
		<div align="center">
			<a class="submit button">获取激信息</a>
		</div>
		<p>
			如果您已经激活过您的帐户，请直接登录即可，无须再次激活。如果您还未注册过帐户，请返回 <a
				href="<s:url action='home' />">首页</a> 注册新用户。
		</p>
	</s:form>
</div>



<vpn:footer />
