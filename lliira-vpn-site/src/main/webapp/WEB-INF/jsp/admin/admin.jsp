<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<vpn:header title="管理员工具" section="admin/admin" />

<h3>管理员工具</h3>

<div class="form-panel ui-widget-content ui-corner-all" align="center">
  <div class="admin-tool">
    <a class="button" href="<s:url action='showPurchase' />">购买管理</a>
  </div>
  <div class="admin-tool">
    <a class="button" href="<s:url action='manageProducts' />">修改套餐</a>
  </div>
  <div class="admin-tool">
    <a class="button" href="<s:url action='anounce!input' />">群发公告</a>
  </div>

</div>

<vpn:footer section="admin/anounce" />
