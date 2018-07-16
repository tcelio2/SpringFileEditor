<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Bootstrap core CSS -->
<link href="includes/css/tools/bootstrap.min.css" media="all"
	type="text/css" rel="stylesheet" />
<link href="includes/css/selection.css" media="all" type="text/css"
	rel="stylesheet" />
<link href="includes/css/filters.css" media="all" type="text/css"
	rel="stylesheet" />

<title>Insert title here</title>
</head>
<body>

	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<label id="fileName">Item.dat</label>
		<div class="container panel panel-default">
			<div id="headingOne" class="file-selection" role="tab">
				<div class="row">
					<div class="col-sm-11">
						<h4 id="filter">
							Filters <small>/path/to... </small>
						</h4>
					</div>
				</div>
			</div>
			<div id="filterList">
				<div class="row">
					<div class="col-sm-4">
						<span id="records">TBD</span>
					</div>
					<div class="col-sm-2">
						<button class="btn btn-default dropdown-toggle dropLarge"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="true">
							<span> select</span> <span class="caret"></span>
						</button>
					</div>
					<div class="col-sm-6">
						<div class="input-group">

							<input id="input" type="text" class="form-control"
								aria-describedby="basic-addon1">

						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-4">
						<span id="records">TBD</span>
					</div>
					<div class="col-sm-2">
						<button class="btn btn-default dropdown-toggle dropLarge"
							type="button" id="dropdownMenu1" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="true">
							<span> select</span> <span class="caret"></span>
						</button>
					</div>
					<div class="col-sm-6">
						<div class="input-group">

							<input id="input" type="text" class="form-control"
								aria-describedby="basic-addon1">

						</div>
					</div>
				</div>
			</div>
			
				<a href="#" class="btn btn-primary" id="proceed">Proceed</a>
			
		</div>
	</div>


</body>

<script src="includes/js/tools/jquery-1.12.0.min.js"></script>
<script src="includes/js/tools/bootstrap.min.js"></script>

</html>