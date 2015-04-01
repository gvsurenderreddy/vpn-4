<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="群发公告" section="admin/anounce" />

<h3>群发公告</h3>

<div class="form-panel ui-widget-content ui-corner-all">
	<s:form id="anounce" action="anounce">
		<s:textfield name="anouncement" cssClass="text-input" label="公告" />
		<div>发送给：</div>
		<s:checkbox name="activatedUsers" label="已激活用户" labelposition="right" />
		<s:checkbox name="unactivatedUsers" label="未激活用户" labelposition="right" />
		<s:checkbox name="activeUsers" label="活跃用户" labelposition="right" />
		<s:checkbox name="newUsers" label="新用户" labelposition="right" />
    <s:checkbox name="customUsers" label="特定用户" labelposition="right" />
		<s:submit cssClass="button" value="发  送" />
	</s:form>
</div>

<vpn:footer section="admin/anounce" />
