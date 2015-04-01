<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="referral ui-widget-content ui-corner-all">
  <div>请将以下链接推荐给您的朋友，当他们完成注册并激活以后，你将获得相应的推荐人奖励。</div>
  <input class="link" readonly="readonly" value="http://www.iqlinkus.com/home?referral=${user.signature}" />
</div>