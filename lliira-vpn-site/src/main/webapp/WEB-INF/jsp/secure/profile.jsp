<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="个人信息" section="account" />

<vpn:servers />

<h1>个人信息</h1>

<div class="user-info form-panel ui-widget-content ui-corner-all">
	<s:form action="updateProfile">
		<s:textfield name="email" cssClass="text-input" label="邮件地址" />
		<s:submit cssClass="button" value="保存邮件" />
	</s:form>
</div>

<h2>密码设置</h2>

<div class="password form-panel ui-widget-content ui-corner-all">
	<s:form action="updatePassword">
		<s:password name="oldPassword" cssClass="text-input" label="现有密码" />
		<s:password name="password" cssClass="text-input" label="新密码" />
		<s:password name="passwordConfirm" cssClass="text-input" label="密码确认" />
		<s:submit cssClass="button" value="修改密码" />
	</s:form>
</div>

<vpn:footer />
