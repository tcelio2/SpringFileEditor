function FiltersController() {

	function init() {

		listCopybookFields();

	}
	init();

	function listCopybookFields(data) {
		alert("oi");
		$.ajax({
			type : "POST",
			url : "/filters",
			data : {
				fileName : fileName
			},
			success : function(data) {
				if(data === true) {
					var d = document.getElementById('list-of-files');
					var olddiv = document.getElementById(panelId);
	
					d.removeChild(olddiv);
				}
				else {
					$('#notFound').modal('show');
				}
			}
		});
	}

}

$(document).ready(function() {
});