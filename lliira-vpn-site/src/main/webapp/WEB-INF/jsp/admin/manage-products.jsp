<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="套餐管理" section="admin/products" />

<h3>套餐管理</h3>

<div class="products large-panel ui-widget-content ui-corner-all"
	align="center">
	<a class="button" href="<s:url action='showProduct' />">新套餐</a>

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
							<s:url var="productUrl" action="showProduct">
								<s:param name="productId">${product.id}</s:param>
							</s:url>
							<td class="product" title="${product.description}"><a
								class="button" href="${productUrl}">￥${product.price}元</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>

</div>

<vpn:footer section="admin/anounce" />
