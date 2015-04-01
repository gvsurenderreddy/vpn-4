<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<p>欢迎注册成为智联网络会员, 立刻享受会员网络加速服务！</p>

<s:form action="register">
	<s:textfield name="email" cssClass="text-input" label="邮件地址" />
	<div class="tooltip">请使用有效邮件地址注册，注册完成后，我们会将您帐户的激活信息发送到该邮件地址。 <font color="navy"> 请勿使用qq.com或sina.com注册，否则你收不到我们的邮件</font></div>
	<s:password name="password" cssClass="text-input" label="密码" />
	<s:password name="passwordConfirm" cssClass="text-input" label="密码确认" />
	<s:textfield name="referral" cssClass="text-input" label="推荐代码"
		readonly="true" />
	<div class="buttons">
		<a class="submit button">注 册</a>
	</div>
</s:form>

<p>
  如果您已注册，但还未激活您的帐户，可以点击下面的按钮以获取激活信息。
</p>
<div align="center">
    <a class="button" href="<s:url action='sendActivationCode!input' />">获取激活信息</a>
</div>