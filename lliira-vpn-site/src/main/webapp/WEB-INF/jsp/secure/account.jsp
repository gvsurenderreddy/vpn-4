<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="帐户管理" section="account" />

<vpn:servers />

<h1>您的帐户信息</h1>


<vpn:referral />


<div class="usages ui-widget-content ui-corner-all">
	<div class="usage">
		<h4>使用记录</h4>
		<table>
			<tr>
				<th>年月</th>
				<th>使用带宽</th>
				<th>使用计时</th>
			</tr>

			<c:set var="usage" value="${account.currentUsage}" />
			<tr class="row">
				<td class="period">${usage.period}</td>
				<td class="used-bandwidth">${usage.usedBandwidthFormat}</td>
				<td class="session-time">${usage.sessionTimeFormat}</td>
			</tr>

			<c:forEach items="${account.previousUsages}" var="usage">
				<tr class="row">
					<td class="period">${usage.period}</td>
					<td class="used-bandwidth">${usage.usedBandwidthFormat}</td>

					<td class="session-time">${usage.sessionTimeFormat}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<c:set var="referralLevel" value="${preferences['referral-level']}" />
<c:set var="referralBonus" value="${preferences['referral-bonus']}" />
<c:set var="referralLimit" value="${preferences['referral-max']}" />
<c:set var="freeBandwidth" value="${preferences['free-bandwidth']}" />

<div class="account-info ui-widget-content ui-corner-all">
	<div class="buttons">
		<a class="button" href="<s:url action='home' />">修改登录信息</a>
	</div>
	<div class="row login-name">登录名称：${user.email}</div>
	<div class="row total-referral">总计推荐：${account.totalReferrals} 人</div>
	<div class="row effective-referral">有效推荐：${account.effectiveReferrals}
		人</div>
	<div class="tooltip">当您每推荐 ${referralLevel}
		个新用户，并且他们成功激活帐户以后，您将在一年内获得
		${referralBonus}MB/月的带宽奖励。推荐人奖励可以累加，但有效推荐人数最多不会超过 ${referralLimit} 人。</div>
	<div class="row remain-bandwidth">剩余带宽：${account.remainBandwidthFormat}</div>
	<div class="row total-bandwidth">总计带宽：${account.effectiveBandwidthFormat}</div>
	<div class="tooltip">
		总计带宽包括以下三部分累加而成：
		<ul>
			<li><b>免费带宽</b>：每位注册用户每月都将自动获得固定的免费带宽，当前的免费带宽额度为：500 MB/月<!-- ${freeBandwidth}MB/月 。--></li>
			<li><b>付费带宽</b>：您付费购买的带宽。我们将陆续推出不同的付费带宽套餐。</li>
			<li><b>推荐奖励带宽</b>：使用您特定的推荐链接，您每推荐 ${referralLevel}
				个新用户并成功激活他们的帐户， 您将自动获得
				${referralBonus}M/月的奖励带宽，此奖励带宽可以累加，但有效推荐人数最多不会超过 ${referralLimit} 人。</li>
		</ul>
	</div>
	<div align="right">
		<span class="button disabled">购买带宽</span>
	</div>

</div>

<div class="purchases ui-widget-content ui-corner-all">
	<h3>购买记录</h3>
	<c:choose>
		<c:when test="${fn:length(purchases) > 0}">
			<table>
				<tr>
					<th>购买时间</th>
					<th>过期时间</th>
					<th>带宽</th>
					<th>金额</th>
				</tr>
				<c:forEach items="${purchases}" var="purchase">
					<tr class="row">
						<td>${purchase.purchaseTimeFormat}</td>
						<td>${purchase.expirationTimeFormat}</td>
						<td>${purchase.bandwidth}GB/月</td>
						<td>${purchase.priceFormat}</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<p>无购买记录</p>
		</c:otherwise>
	</c:choose>

</div>

<vpn:footer />
