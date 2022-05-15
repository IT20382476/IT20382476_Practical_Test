 
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
  var type = $("#hidCustomerIDSave").val() == "" ? "POST" : "PUT";
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
  $("#hidCustomerIDSave").val("");
  $("#formCus")[0].reset();
}

//UPDATE
$(document).on("click", ".btnUpdate", function (event) {
  $("#hidCustomerIDSave").val($(this).data("customerid"));
  $("#firstName").val($(this).closest("tr").find("td:eq(0)").text());
  $("#lastName").val($(this).closest("tr").find("td:eq(1)").text());
  $("#nic").val($(this).closest("tr").find("td:eq(2)").text());
  $("#phone").val($(this).closest("tr").find("td:eq(3)").text());
  $("#email").val($(this).closest("tr").find("td:eq(4)").text());


});
$(document).on("click", ".btnRemove", function (event) {
  $.ajax({
    url: "CustomersAPI",
    type: "DELETE",
    data: "customerId=" + $(this).data("customerid"),
    dataType: "text",
    complete: function (response, status) {
      onCustomerDeleteComplete(response.responseText, status);
    },
  });
});

function onCustomerDeleteComplete(response, status) {
  if (status == "success") {
    var resultSet = JSON.parse(response);
    if (resultSet.status.trim() == "success") {
      $("#alertSuccess").text("Successfully deleted.");
      $("#alertSuccess").show();
      $("#divCustomersGrid").html(resultSet.data);
    } else if (resultSet.status.trim() == "error") {
      $("#alertError").text(resultSet.data);
      $("#alertError").show();
    }
  } else if (status == "error") {
    $("#alertError").text("Error while deleting.");
    $("#alertError").show();
  } else {
    $("#alertError").text("Unknown error while deleting..");
    $("#alertError").show();
  }
}

function validateCusForm() {
  // CODE
  if ($("#firstName").val().trim() == "") {
    return "Insert firstname.";
  }

  // NAME
  if ($("#lastName").val().trim() == "") {
    return "Insert lastname.";
  }

  // nic-------------------------------
  if ($("#nic").val().trim() == "") {
    return "Insert nic.";
  }

 

  // convert to decimal phone
   if ($("#phone").val().trim() == "") {
    return "Insert nic.";
  }

  // email------------------------
  if ($("#email").val().trim() == "") {
    return "Insert email.";
  }
  return true;
}

