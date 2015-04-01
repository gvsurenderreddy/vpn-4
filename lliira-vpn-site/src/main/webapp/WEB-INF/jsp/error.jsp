<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="出错" section="download" />

<h1>出错了</h1>

<s:actionerror />
<p>
	<s:property value="%{exception.message}" />
</p>

<p>
	请检查您上一次操作的输入信息，并重试。如果问题依然存在，请<a href="sendto:iqlinkvpn@yahoo.com">联系我们</a>。
</p>

<vpn:footer />
