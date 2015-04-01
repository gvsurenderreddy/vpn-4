$(function() {
  if ($.browser.webkit) { // no need of custom redirect on chrome or safari
    $(".purchase .alipay .noreferrer").show();
    $(".purchase .alipay .referrer").hide();
  } else { // need custom redirect on other browsers
    $(".purchase .alipay .noreferrer").hide();
    $(".purchase .alipay .referrer").show();
  }
});