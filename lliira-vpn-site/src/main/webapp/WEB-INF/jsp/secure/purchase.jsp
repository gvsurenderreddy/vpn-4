<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="购买套餐" section="secure/purchase" />

<h1>购买套餐</h1>

<div class="purchase large-panel ui-widget-content ui-corner-all">
	<p>您确定要购买以下套餐吗？</p>
	<div class="product">
		<span class="bandwidth">月流量：${product.bandwidth}GB</span>， <span
			class="duration">有效期：${product.duration}个月</span>， <span
			class="price">价格：￥${product.price}元</span>
	</div>
	<p>如果您确认购买该套餐，请点击以下链接前往支付宝付款，我们会在收到您付款的12小时内为您开通付费的VPN服务。</p>
	<p class="warning">请注意：使用支付宝付款时，请按照指定金额付款，并且请勿修改付款说明及备注中的内容，否则我们将无法确认您的付款记录。</p>
	<div class="alipay">

		<div class="referrer">
		  <s:url var="url" action="purchaseRedirect">
		    <s:param name="productId" value="product.id" />
		  </s:url>
			<a class="button" target="_blank" href="${url}">
				<img alt="支付宝付款" border="0" src="<s:url value='/images/alipay.png' />">
			</a>
		</div>
		<div class="noreferrer">
      <a class="button" target="_blank" rel="noreferrer" href="${paymentUrl}">
        <img alt="支付宝付款" border="0" src="<s:url value='/images/alipay.png' />">
      </a>
		</div>
		<div class="warning">
		  <p> 如果在转到支付宝以后，您发现收款人一栏为空，请复制以下链接，并在新窗口中打开；</p> 
		  <p> 如果您没有支付宝账户，可将以下链接发给好友，让其代付即可。</p>
		  <div class="ui-widget-content ui-corner-all">${paymentUrl}</div>
		</div>
	</div>
</div>
<vpn:servers />

<vpn:footer />
