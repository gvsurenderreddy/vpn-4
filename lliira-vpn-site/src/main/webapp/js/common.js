$(function() {
  $(".tabs").tabs();
  $(".button").button();

  // register submit buttons in form
  $("form a.submit").click(function() {
    $(this).parents("form").submit();
  });
});
