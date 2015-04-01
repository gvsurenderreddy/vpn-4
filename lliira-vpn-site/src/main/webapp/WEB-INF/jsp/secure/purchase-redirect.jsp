<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="vpn" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta HTTP-EQUIV="REFRESH" content="0; url=${paymentUrl}">
<title>前往支付宝</title>

<s:head />
<vpn:css section="${section}" />

</head>
<body>
  <div class="screen-center">
   <h1>即将转至支付宝，请稍等。</h1>
  </div>

</body>
</html>
