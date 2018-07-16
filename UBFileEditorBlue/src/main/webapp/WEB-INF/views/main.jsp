<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content=""/>
	<meta name="author" content="" />
	<link rel="icon" href="favicon.ico" />
	
	<title>UB File Editor</title>
	
	<!-- Bootstrap core CSS -->
	<link href="includes/css/tools/bootstrap.min.css" rel="stylesheet">
	
	<!-- Custom styles for this template -->
	<link href="includes/css/base.css" rel="stylesheet">

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="navbar-file">${file}</div>
			<span class="navbar-path">at ${pathfile}</span>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/selection">Change File</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="col-sm-1	col-md-10 main">
			<div class="row file-handler">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#file">File</a>
					</li>
					<li><a data-toggle="tab" href="#preview">Preview</a></li>
				</ul>
				<div class="tab-content ">
					<div id="file" class="tab-pane fade in active">
						<div class="row bufferContent">
							<div class="col-lg-5">
								<div class="input-group bufferInput">
									<span class="input-group-addon" id="basic-addon1"><b>Navigator Number</b></span>
									<input type="text" class="form-control" maxlength="10" onkeypress='return permitNumbers(event)' id="bufferValue" placeholder="Insert the navigator number" aria-describedby="basic-addon1">
								</div>
							</div>
							<div class="col-lg-1 btnGet">
								<a href="#" class="btn btn-primary btn-block" id="getButton">Get</a>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-body" id="panelArea">
								<div class="panel-body"></div>
							</div>
						</div>
					</div>
					<div id="preview" class="tab-pane fade">
						<h3>Preview</h3>
						<p>This tab will show the preview code</p>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-md-2 sidebar">
				<h3>Copybook</h3>
				<button class="btn btn-primary" type="button" id="searchCopyBook"
					data-toggle="modal" data-target="#myModal">Search</button>
				<br> <br>
				<div id="copybookSelected"></div>
				<hr />
				<div>
					<ul class="nav nav-sidebar" id="previewUL">
					</ul>
				</div>
			</div>
		</div>
	<!-- Modal -->
	<jsp:include page="modalCopyBookSearch.jsp" />
	

	<!-- Bootstrap core JavaScript
		================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="includes/js/tools/jquery-1.12.0.min.js"></script>
	<script src="includes/js/tools/bootstrap.min.js"></script>
	<script src="includes/js/components/copybook.js"></script>
	<script src="includes/js/components/filehandler.js"></script>
	<script src="includes/js/editor.controller.js"></script>
</body>
</html>
