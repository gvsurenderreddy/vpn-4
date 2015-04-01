<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>


<vpn:header title="找回密码" section="account" />

<h1>找回密码</h1>

<div class="form-panel ui-widget-content ui-corner-all">
	<s:form action="forgetPassword">
		<p>请输入您的注册邮件，我们会将您的密码发给您。 </p> 
		<s:textfield name="email" cssClass="text-input" label="邮件地址" />
				<p><font color="blue"> <b> 注： qq.com， sina.com， 和 foxmail.com  的用户，你很可能收不到这份密码电邮，这是因为它们把我们给你的电邮都拦截了！ 给我们发电邮索取你的密码吧。</b> </font>	</p>
		<p><font color="blue"> <b> 
		我们电邮地址是 iqlinkvpn@yahoo.com 
					</b> </font>	</p>
		
		<div align="right">
			<a class="submit button">取回密码</a>
		</div>
		
	</s:form>
</div>

<vpn:footer />
