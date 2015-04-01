<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="section"
	description="The section on the menu to be highlighted. If not provided, will highlight home."
	required="false"%>


<!-- google analytics -->
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-34236764-1']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>


<script type="text/javascript"
	src="<c:url value='/js/lib/jquery-1.7.2.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/lib/jquery-ui-1.8.21.custom.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/js/lib/apy-menu.js' />"></script>

<script type="text/javascript" src="<c:url value='/js/common.js' />"></script>

<c:choose>
	<c:when test="${section eq 'admin/anounce'}">
		<script type="text/javascript"
			src="<c:url value='/js/admin/anounce.js' />"></script>
	</c:when>
	<c:when test="${section eq 'secure/purchase'}">
    <script type="text/javascript"
      src="<c:url value='/js/secure/purchase.js' />"></script>
	</c:when>
</c:choose>

