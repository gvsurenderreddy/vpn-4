<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="help" section="products" />

<vpn:servers />

<h1>客户端使用帮助</h1>

<div class="tabs">
  <ul>
    <li>
      <s:url var="url" action="help"><s:param name="os">windows</s:param></s:url>
      <a href="${url}">Windows</a>
    </li>
			 <li>
      <s:url var="url" action="help"><s:param name="os">Windows8</s:param></s:url>
      <a href="${url}">Window8</a>
    </li>
    <li>
      <s:url var="url" action="help"><s:param name="os">android</s:param></s:url>
      <a href="${url}">Android</a>
    </li>
    <li>
      <s:url var="url" action="help"><s:param name="os">iphone</s:param></s:url>
      <a href="${url}">IPhone/IPad</a>
    </li>
    <li>
      <s:url var="url" action="help"><s:param name="os">mac</s:param></s:url>
      <a href="${url}">Mac</a>
    </li>
    <li>
      <s:url var="url" action="help"><s:param name="os">ubuntu</s:param></s:url>
      <a href="${url}">Ubuntu</a>
    </li>
    <li>
      <s:url var="url" action="help"><s:param name="os">webos</s:param></s:url>
      <a href="${url}">WebOS</a>
    </li>
	 <li>
      <s:url var="url" action="help"><s:param name="os">blackberry</s:param></s:url>
      <a href="${url}">BlackBerry</a>
    </li>
	<li>
      <s:url var="url" action="help"><s:param name="os">Nokia</s:param></s:url>
      <a href="${url}">Nokia</a>
    </li>
  </ul>
</div>

<vpn:footer />
