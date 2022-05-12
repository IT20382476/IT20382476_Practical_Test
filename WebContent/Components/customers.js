/**
 * 
 */
 
$(document).ready(function () {
  $("#alertSuccess").hide();
  $("#alertError").hide();
});
$(document).on("click", "#btnSave", function (event) {
  // Clear alerts---------------------
  $("#alertSuccess").text("");
  $("#alertSuccess").hide();
  $("#alertError").text("");
  $("#alertError").hide();
  // Form validation-------------------
  var status = validateCusForm();
  if (status != true) {
    $("#alertError").text(status);
    $("#alertError").show();
    return;
  }
 
  // If valid------------------------
  var type = $("#hidcustomerIdSave").val() == "" ? "POST" : "PUT";
  $.ajax({
    url: "CustomersAPI",
    type: type,
    data: $("#formCus").serialize(),
    dataType: "text",
    complete: function (response, status) {
      onCustomerSaveComplete(response.responseText, status);
    },
  });
});
function onCustomerSaveComplete(response, status) {
  if (status == "success") {
    var resultSet = JSON.parse(response);
    if (resultSet.status.trim() == "success") {
      $("#alertSuccess").text("Successfully saved.");
      $("#alertSuccess").show();
      $("#divCustomersGrid").html(resultSet.data);
    } else if (resultSet.status.trim() == "error") {
      $("#alertError").text(resultSet.data);
      $("#alertError").show();
    }
  } else if (status == "error") {
    $("#alertError").text("Error while saving.");
    $("#alertError").show();
  } else {
    $("#alertError").text("Unknown error while saving..");
    $("#alertError").show();
  }
  $("#hidcustomerIdSave").val("");
  $("#formCus")[0].reset();
}