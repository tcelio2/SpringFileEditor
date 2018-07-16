<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>File Selection</title>

<!-- Bootstrap core CSS -->
<link href="includes/css/tools/bootstrap.min.css" media="all"
	type="text/css" rel="stylesheet" />
<link href="includes/css/selection.css" media="all" type="text/css"
	rel="stylesheet" />

</head>
<body onload="inputFileName.focus();">

	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="container panel panel-default">
			<div id="headingOne" class="file-selection" role="tab">
				<div class="row">
					<div class="col-sm-11">
						<h4 class="panel-title">File Selection</h4>
					</div>
					<div class="col-sm-1">
						<a id="triangleButton" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne"> <span
							class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></a>
					</div>
				</div>
			</div>

			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne">
				<div class="panel-body">
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle dropLarge"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="true">
							<span id="listFileDropdown"
								class="glyphicon glyphicon-folder-open spanLarge"> </span> <span
								class="caret caretStyle"></span>
						</button>
						<ul id="dropdownFile" class="dropdown-menu scrollable dropLarge"
							aria-labelledby="dropdownMenu1">
						</ul>
					</div>
					<br />
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="Selection" class="active"><a href="#list"
							aria-controls="list" role="tab" data-toggle="tab">List of
								files</a></li>
						<li role="Selection"><a href="#history"
							aria-controls="history" role="tab" data-toggle="tab">History</a></li>
					</ul>



					<!-- Tab panes -->
					<div class="tab-content">

						<br /> <input type="text" id="inputFileName"
							class="form-control inputFileName" placeholder="&nbsp;File Name">
						<br />
						<div role="tabpanel" class="tab-pane active" id="list">

							<div class="noFiles" id="noFiles" style="display: none">
								<p>
									<i><b>There are no files.</b></i>
								</p>
							</div>
							<div class="listSpace">
								<div class="list-group listFiles" id="listFiles"></div>
							</div>

						</div>

						<div role="tabpanel" class="tab-pane" id="history">..TBD..</div>
						<br />
						<button type="button" class="btn btn-primary btn-block"
							id="btnProceed" disabled="disabled">Proceed</button>
					</div>


				</div>

			</div>
		</div>

		<!-- Second collapse -->

		<div class="container panel panel-default">
			<div id="headingTwo" class="file-selection" role="tab">
				<div class="row">
					<div class="col-sm-11">
						<h4 class="panel-title">Copybook Source</h4>
					</div>
					<div class="col-sm-1">
						<a id="triangleButton" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo" aria-expanded="true"
							aria-controls="collapseTwo"> <span
							class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></a>
					</div>
				</div>
			</div>

			<div id="collapseTwo" class="panel-collapse collapse out"
				role="tabpanel" aria-labelledby="headingTwo">
				<div class="panel-body">
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle dropLarge"
							type="button" id="dropdownMenu2" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="true">
							<span id="listCopybookDropdown"
								class="glyphicon glyphicon-folder-open spanLarge"> </span> <span
								class="caret caretStyle"></span>
						</button>
						<ul id="dropdownCopybook"
							class="dropdown-menu scrollable dropLarge"
							aria-labelledby="dropdownMenu1">
						</ul>
					</div>
					<div>
						<span id="fileSelected" style="display: none"></span> <input
							type="hidden" id="fileSelectedName" value="">
					</div>
					<br />
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="Selection" class="active"><a href="#list"
							aria-controls="list" role="tab" data-toggle="tab">List of
								files</a></li>
						<li role="Selection"><a href="#history"
							aria-controls="history" role="tab" data-toggle="tab">History</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<input type="text" id="inputFileName"
							class="form-control inputFileName" placeholder="&nbsp;File Name">
						<br />
						<div role="tabpanel" class="tab-pane active" id="list">

							<div class="noFiles" id="noFilesCopybook" style="display: none">
								<p>
									<i><b>There are no files.</b></i>
								</p>
							</div>
							<div class="listSpace">
								<div class="list-group listCopybook" id="listCopybook"></div>
							</div>

						</div>
						<div role="tabpanel" class="tab-pane" id="history">..TBD..</div>
						<br />
						<button type="button" class="btn btn-primary btn-block"
							id="btnSelect1" disabled="disabled">Select</button>
					</div>

				</div>
			</div>
		</div>
		
		
				<!-- Third collapse -->

		<div class="container panel panel-default">
			<div id="headingThree" class="file-selection" role="tab">
				<div class="row">
					<div class="col-sm-11">
						<h4 class="panel-title">Copybook Target</h4>
					</div>
					<div class="col-sm-1">
						<a id="triangleButton" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree" aria-expanded="true"
							aria-controls="collapseThree"> <span
							class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span></a>
					</div>
				</div>
			</div>

			<div id="collapseThree" class="panel-collapse collapse out"
				role="tabpanel" aria-labelledby="headingThree">
				<div class="panel-body">
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle dropLarge"
							type="button" id="dropdownMenu3" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="true">
							<span id="targetCopybookDropdown"
								class="glyphicon glyphicon-folder-open spanLarge"> </span> <span
								class="caret caretStyle"></span>
						</button>
						<ul id="dropdownTargetCopybook"
							class="dropdown-menu scrollable dropLarge"
							aria-labelledby="dropdownMenu1">
						</ul>
					</div>
					<div>
						<span id="fileSelected" style="display: none"></span> <input
							type="hidden" id="fileSelectedName" value="">
					</div>
					<br />
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="Selection" class="active"><a href="#list"
							aria-controls="list" role="tab" data-toggle="tab">List of
								files</a></li>
						<li role="Selection"><a href="#history"
							aria-controls="history" role="tab" data-toggle="tab">History</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<input type="text" id="inputFileName"
							class="form-control inputFileName" placeholder="&nbsp;File Name">
						<br />
						<div role="tabpanel" class="tab-pane active" id="list">

							<div class="noFiles" id="noFilesTarget" style="display: none">
								<p>
									<i><b>There are no files.</b></i>
								</p>
							</div>
							<div class="listSpace">
								<div class="list-group listCopybook" id="listTargetCopybook"></div>
							</div>

						</div>
						<div role="tabpanel" class="tab-pane" id="history">..TBD..</div>
						<br />
						<button type="button" class="btn btn-primary btn-block"
							id="btnProceed" disabled="disabled">Select</button>
					</div>

				</div>
			</div>
		</div>

	</div>

</body>
<script src="includes/js/tools/jquery-1.12.0.min.js"></script>
<script src="includes/js/tools/bootstrap.min.js"></script>

<script src="includes/js/selection.controller.js"></script>
</html>