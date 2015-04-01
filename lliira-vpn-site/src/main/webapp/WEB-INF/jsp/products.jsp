<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="套餐介绍" section="products" />

<vpn:servers />

<h1>套餐介绍</h1>

<c:set var="referralLevel" value="${preferences['referral-level']}" />
<c:set var="referralBonus" value="${preferences['referral-bonus']}" />
<c:set var="referralLimit" value="${preferences['referral-max']}" />
<c:set var="freeBandwidth" value="${preferences['free-bandwidth']}" />

<div class="free-product large-panel ui-widget-content ui-corner-all">
	<h3>免费套餐</h3>
	<p>（１） 免费流量： 500MB/月 + （N x ${referralBonus}MB/月）
<!--	${freeBandwidth}MB/月 + （N x ${referralBonus}MB/月）， -->
		 N为你的有效推荐人等级。</p>
	<ul>
		<li>您每推荐 ${referralLevel} 个新用户并成功激活他们的帐户， 您将获得
			${referralBonus}MB/月的奖励带宽， 此奖励带宽可以累加，但有效推荐人数最多不会超过 ${referralLimit}
			人。</li>
		<li>连接时长为60分钟（可马上重连），同时连接设备数不超过2台</li>
		<li>我们有权更改： 免费用户的月流量， 连接时长，连接设备数，访问的服务器。</li>
	</ul>
</div>

<div align="center"
	class="purchase-product large-panel ui-widget-content ui-corner-all">
	<h3>付费套餐</h3>
	<c:set var="durations" value="${productMap.durations}" />
	<c:set var="bandwidths" value="${productMap.bandwidths}" />
	<c:set var="products" value="${productMap.array}" />

	<table class="products">
		<tr>
			<th>&nbsp;</th>
			<c:forEach items="${durations}" var="duration">
				<th>${duration} 月</th>
			</c:forEach>
		</tr>
		<c:forEach var="b" begin="1" end="${fn:length(bandwidths)}" step="1">
			<tr>
				<th>${bandwidths[b - 1]} GB/月</th>
				<c:forEach var="d" begin="1" end="${fn:length(durations)}" step="1">
					<c:set var="product" value="${products[b - 1][d - 1]}" />
					<c:choose>
						<c:when test="${product == null}">
							<td class="product">--${products[b - 1][d - 1]}</td>
						</c:when>
						<c:otherwise>
							<td class="product" title="${product.description}"><span
								class="price">￥${product.price}元</span>
								<div class="control">
									<s:if test="user == null">
										<span class="warning">（登录后购买）</span>
									</s:if>
									<s:else>
										<s:url var="purchaseUrl" action="purchase">
											<s:param name="productId">${product.id}</s:param>
										</s:url>
										<a class="button" href="${purchaseUrl}">购买</a>
									</s:else>
								</div></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

	<div align="left">
		<ul>
			<li>付费流量会<b> 累加 </b> 在你的免费流量上。</li>
			<li>多个付费套餐可以<b> 累加 </b>。 比如你同时买5GB-6月和2GB-12月， 那么前6月你有7GB，后6月你有2GB。</li>
			<li>连接时长为240分钟，同时连接设备数不超过4台。</li>
			<li>我们优先保证付费用户的连接时长，连接设备数，月流量，和访问的服务器。</li>
			<li>我们会在收到付费后12小时内更新数据库，更新后您会收到确认电邮。
			<li>如需更多月流量，请发电邮至 iqlinkvpn@yahoo.com 。</li>
		</ul>
	</div>

</div>

<!---
<div
	class="product-description large-panel ui-widget-content ui-corner-all">

	<h4 align="left">我们为什么要收费？</h4>
	<p>（１） 收费可以保护大多数用户的正常使用；
		可以减少某些免费用户通过我们的服务器使用P2P软件下载超量数据，甚至下载受版权保护的音像文件。</p>
	<p>（２） 收费是为了更好地服务： 收费让我们有更多的资金，购买更多的服务器和网络带宽，为你提供更可靠，更稳定，更高速的服务。</p>
	<p align="center">
		<font color="blue" size="4"> <b>"We don’t build services to make
				money; we make money to build better services....." </b></font>
	</p>

	<h4 align="left">你为什么要付费？</h4>
	<p>（１） 我们全部使用Cisco最先进的企业级ASA服务器； 并拥有500Mbps的专用带宽； 全部服务器都位于中美主干网枢纽。
		不可能有比我们更快和更稳定的服务了。</p>
	<p>（２） 付费使你有更多的流量；更长的连接；更多的服务器； 给你方便，节省时间； 让你获得更广泛的信息。</p>
	<p>（３） 你的付费能让我们打造更强大的服务，让更多的人方便上网， 为更多的人提供资讯。 利己利人！</p>
	<p align="center">
		<font color="blue" size="4"> <b> “Inspire Goodness;
				Promote Sharing. Together we make the difference. Yes We Can!” </b></font>
	</p>

</div>
--->
<div
	class="product-description large-panel ui-widget-content ui-corner-all">

	<p align="center">
		<font color="blue" size="4"> <b> 你为什么购买我们的服务？</b></font>
	</p>
	<p>（１）<b>我们保护每一位用户的隐私，我们不利用任何用户的电邮盈利或做广告。用户付费是我们唯一的收入。</b></p>
	<p>（２）<b>我们全部使用Cisco最先进的企业级ASA超大型服务器；　购买了大批AnyConnect牌照。我们租用了专用1GBps中美带宽；全部服务器都位于中美主干网枢纽；我们的服务器和中国电信，联通共享同一北美数据中心。我们的服务器位于加州硅谷和Google门对门； 速度比肩goagent； 稳定超过任何人。不可能有比我们更快，更稳定的服务了。</b></p>
	<p>（３）<b>与别家提供商不同，我们不使用VPS+OpenVPN技术。尽管使用VPS会貌似有很多线路，但是VPS带宽有限，人数多的话速度变慢。而且软件实现的VPN (OpenVPN)不稳定，流量不大。　</b>　</p>
	<p>（４）<b>我们每条线路都是一台企业级超大型服务器，放在高速的数据中心里；不是虚拟线路。　</b></p>
	<p>（５）<b>在网上，用户对我们的服务质量，稳定，和速度，有好评。 网友的反馈是： “服务器的速度让人印象深刻” “速度流畅且稳定” “真的很棒是我所用过最好的一种速度快流畅” “速度确实杠杠的” “速度一流” “速度比较给力” “速度太快，不知不觉流量很快用光”。。。</b></p>
</div>
<vpn:footer />
