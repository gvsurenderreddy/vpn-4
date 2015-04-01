<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>


<%@ attribute name="title" description="The title of the page."
	required="false"%>

<%@ attribute name="section"
	description="The section on the menu to be highlighted. If not provided, will highlight home."
	required="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="智联科技（IQLINK）提供免费私人VPN服务，翻越防火墙，自幼畅游互联网">
<meta name="keywords"
	content="VPN, 免费, 翻墙, 中国防火墙, 私人, 虚拟局域网, 美国, Facebook, Wordpress, Twitter, Youtube, 国际新闻, 美国网游服务器, 海外博客, 艾园, 文学城, 未名空间">
<c:if test="${title != null}">
	<c:set var="title" value=" - ${title}" />
</c:if>
<title>智联科技 IQLink${title}</title>

<s:head />
<vpn:css section="${section}" />
<vpn:javascript section="${section}" />

</head>
<body>
	<div id="site-content">
		<%-- this div will be closed in the footer --%>

		<div id="site-header">

			<vpn:userControl />

			<a id="site-title" href="<s:url action='home' />">智联科技 IQLink</a>
			<div id="menu">
				<ul id="site-menu" class="menu">
					<li><a href="<s:url action='home' />">首 页</a></li>
					<li><a href="<s:url action='account' />">帐 户</a></li>
					<li><a href="<s:url action='products' />">套 餐</a></li>
					<li><a href="<s:url action='download' />">下 载</a></li>
					<li><a href="<s:url action='help' />">帮 助</a></li>
				</ul>
			</div>
		</div>