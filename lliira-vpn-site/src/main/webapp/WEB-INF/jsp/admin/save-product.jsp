<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="保存套餐" section="admin/product" />

<h3>保存套餐</h3>

<div class="form-panel ui-widget-content ui-corner-all">
  <s:form class="product" action="saveProduct">
    <s:hidden name="productId" />
    <s:textfield name="bandwidth" label="带宽（GB/月）" />
    <s:textfield name="duration" label="时效（月数）" />
    <s:textfield name="price" label="价格：" />
    <s:checkbox name="available" label="有效套餐" labelposition="right" />
    <s:textarea name="description" label="套餐描述" cols="46" />
    <s:submit align="center" cssClass="button" value="保    存" />
  </s:form>
</div>

<vpn:footer section="admin/anounce" />
