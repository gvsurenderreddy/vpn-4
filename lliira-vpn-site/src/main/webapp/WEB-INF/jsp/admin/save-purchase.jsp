<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="保存购买记录" section="admin/purchase" />

<h3>保存购买记录</h3>

<div class="purchase large-panel ui-widget-content ui-corner-all">
	<s:form class="purchase" action="savePurchase">
		<s:hidden name="purchaseId" />
		<s:textfield name="email" label="用户邮件" size="80" labelposition="left" />

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
								<td><c:set var="checked">
										<c:if test="${product.id == productId}">checked="checked"</c:if>
									</c:set> <input type="radio" name="productId" value="${product.id}"
									${checked} /> ￥${product.price}元</td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<s:submit align="center" cssClass="button" value="保    存" />
	</s:form>
</div>

<vpn:footer section="admin/purchase" />
