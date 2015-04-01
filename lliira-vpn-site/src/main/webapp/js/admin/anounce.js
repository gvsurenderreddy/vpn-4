$(function() {
  // disable the submit button to avoid double submit
  $("form#anounce").submit(function() {
    $('input[type=submit]', this).each(function() {
      $(this).attr('disabled', 'disabled');
      $(this).after("Sending anouncement, please wait...");
    });
    return true;
  });
});