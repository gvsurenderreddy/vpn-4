<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="激活帐户" section="home" />

<h1>帐户激活失败</h1>
<p>请检查您的激活链接，然后重试。如果依然失败，请重新注册，或<a href="mailto:iqlinkvpn@yahoo.com">联系我们</a>。</p>

<s:actionerror />
<p>
	<s:property value="%{exception.message}" />
</p>


<vpn:footer />
