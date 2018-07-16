<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="myModalLabel">Copybook Search</h3>
			</div>
			<div class="modal-body">

				<div class="row">

					<div class="col-sm-4">
						<br>
						<div class="input-group">
							<input type="text" class="form-control" id="inputCopybook"
								placeholder="&nbsp;Copybook"> <br> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								</button>
							</span>
						</div>
						<br>
						<div class="scroll-y" id="list">
							<ul id="listOfCopybooks" class="list-unstyled">
							</ul>
						</div>
					</div>

					<div class="col-sm-8" id="xmlData">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									Copybook Info <small id=smallCbName>Name</small></small>
								</h4>
							</div>
							<div class="panel-body"></div>
							<ul id="ulXmlList" class="list-group">
							</ul>
							<div id="copybookTables"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="addCopybook"
					data-dismiss="modal">Add</button>
			</div>
		</div>
	</div>
</div>