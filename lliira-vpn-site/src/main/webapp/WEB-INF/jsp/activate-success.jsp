<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="激活帐户" section="home" />

<h1>您的帐户成功激活</h1>
<p>感谢使用智连网络的VPN服务。</p>
<p>
	请至<a href="<s:url action='download' />">下载专区</a>免费下载VPN客户端，并使用您的注册邮件与密码登录VPN网络。
</p>
<p>
	如有疑问，请查看<a href="<s:url action='help' />">帮助专区</a>的相关安装说明与问题解答。
</p>

<vpn:footer />
