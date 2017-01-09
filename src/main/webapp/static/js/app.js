$(document).ready(function() {
	var home = "${home}";
	console.log(home);
	$('.addAppCheckBox').change(function() {
		console.log(this.checked);
		var app_id = $(this).attr("id").split("_")[1];
		if (this.checked) {
			$("#addApp_appId").val(app_id);
			$("#addAppModal").modal('show');
			$(this).prop('checked',false);
		}
	});
	$("#addAppSubmit").click(function(e) {
		var	data = {
				app_id : $("#addApp_appId").val(),
				app_username : $("#addApp_username").val(),
				app_password : $("#addApp_password").val()
			}
		var jsonfile={json:JSON.stringify(data)};
		
//		$.ajax({
//		     type: "POST",
//		     url: CONTEXT_PATH+"submit",
//		     data: { name: "John", location: "Boston" } // parameters
//		});
		
		$.ajax({
			type : "POST",
			url : CONTEXT_PATH+"authApp",
			data : data,
			success : function(data) {
				console.log("SUCCESS: ", data);
				//display(data);
				if(data.verified){
					$("#app_"+$("#addApp_appId").val()).prop('checked',true);
					$('#addAppModal').modal('hide');
				}
				else{
					$("#app_"+$("#addApp_appId").val()).prop('checked',false);
				}
				
			},
			error : function(e) {
				console.log("ERROR: ", e);
				//display(e);
			}
		});
		return false;
		//$('#appAuthModal').modal('hide'); 
	});
});