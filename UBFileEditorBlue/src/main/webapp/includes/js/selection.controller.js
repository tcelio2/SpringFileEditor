// This class controls the selection page actions
function SelectionController() {

	// Objects binded
	var _btnProceed = null;
	var _btnSelect1 = null;
	var _fileList = null;
	var _copybookList = null;
	var _targetCopybookList = null;
	var _fileSelected = null;
	var _modalInput = null;
	var _noFiles = null;
	var _noFilesCopybook = null;
	var _noFilesTarget = null;
	var _fileSelectedName = null;

	/**
	 * Initializes the used components and binds with each other.
	 */
	function init() {

		_btnProceed = document.getElementById("btnProceed");
		_btnSelect1 = document.getElementById("btnSelect1");
		_fileList = document.getElementById('listFiles');
		_copybookList = document.getElementById('listCopybook');
		_targetCopybookList = document.getElementById('listTargetCopybook');
		_fileSelected = document.getElementById('fileSelected');
		_fileSelectedName = document.getElementById('fileSelectedName');
		_modalInput = document.getElementById('inputFileName');
		_noFiles = document.getElementById('noFiles');
		_noFilesCopybook = document.getElementById('noFilesCopybook');
		_noFilesTarget = document.getElementById('noFilesTarget');
		_fileSelected.innerHTML = "<i>none</i>"
		_btnProceed.disabled = true;
		_btnSelect1.disable = true;
		_noFiles.style.display = 'none';
		_noFilesCopybook.style.display = 'none';
		_noFilesTarget.style.display = 'none';
		document.addEventListener('keyup', fileNameFilter, false);
		_modalInput.disabled = false;

		_btnProceed.onclick = function() {
			callFileHandler()
		};

		getPath(true, false);
		getPath(false, true);
		getPath(false, false);

		listFiles("", true, false);
		listFiles("", false, true);
		listFiles("", false, false);

		loadDirectories(true, false);
		loadDirectories(false, true);
		loadDirectories(false, false);

		$("#dropdownFile").on('click', 'li a', function() {

			$('#listFileDropdown').html("&nbsp" + $(this).html());
			listFiles($(this).html(), true, false);

		});

		$("#dropdownCopybook").on('click', 'li a', function() {

			$('#listCopybookDropdown').html("&nbsp" + $(this).html());
			listFiles($(this).html(), false, false);

		});

		$("#dropdownTargetCopybook").on('click', 'li a', function() {

			$('#targetCopybookDropdown').html("&nbsp" + $(this).html());
			listFiles($(this).html(), false, true);

		});

	}
	init();

	function loadDirectories(isFileDir, isTargetCopybook) {

		var ul = null;
		if (isFileDir === true) {
			ul = document.getElementById("dropdownFile");
		} else if (isTargetCopybook === true) {
			ul = document.getElementById("dropdownTargetCopybook");
		} else {
			ul = document.getElementById("dropdownCopybook");
		}

		$.ajax({
			url : "/loadDir",
			dataType : "json",
			data : {
				isFileDir : isFileDir,
				isTargetCopybook : isTargetCopybook
			},
			success : function(data) {

				for (var i = 0; i < data.length; i++) {
					var li = document.createElement("li");
					var a = document.createElement("a");
					a.href = "#";
					a.innerHTML = data[i].name;
					li.appendChild(a);
					ul.appendChild(li);
				}
			}
		});

	}

	function getPath(isFilePath, isTargetCopybook) {

		$.ajax({
			url : "/getPath",
			data : {
				isFilePath : isFilePath,
				isTargetCopybook : isTargetCopybook
			},
			success : function(data) {
				var span = null;
				if (isFilePath === true) {
					span = document.getElementById("listFileDropdown");
				} else if (isTargetCopybook === true) {
					span = document.getElementById("targetCopybookDropdown");
				} else {
					span = document.getElementById("listCopybookDropdown");
				}

				span.innerHTML = "&nbsp" + data;
			}
		});

	}

	function listFiles(path, isFileList, isTargetCopybook) {
		$.ajax({
			url : "/search",
			dataType : "json",
			data : {
				path : path,
			},
			success : function(data) {

				var div = null;
				if (isFileList === true) {
					div = _fileList;
				} else if (isTargetCopybook === true) {
					div = _targetCopybookList;

				} else {

					div = _copybookList;
				}
				div.innerHTML = "";

				if (data != null && data != "") {

					for (var i = 0; i < data.length; i++) {

						var a = document.createElement('a');

						a.innerHTML = data[i].name;
						a.data = data[i];
						a.setAttribute('href', "#");
						a.setAttribute('class', "list-group-item small");
						a.onclick = showFileName;

						div.appendChild(a);

						if (isFileList === true) {
							_noFiles.style.display = "none";

						} else if (isTargetCopybook === true) {
							_noFilesTarget.style.display = "none";

						} else {
							_noFilesCopybook.style.display = "none";
						}

						_modalInput.disabled = false;

					}
				} else {

					_noFiles.style.display = '';
					_noFilesCopybook.style.display = '';
					_noFilesTarget.style.display = '';
					_modalInput.disabled = true;

				}
			}

		});
	}

	function callFileHandler() {

		var fileName = _fileSelectedName.value;

		$.ajax({
			type : 'post',
			url : "/setFileName",
			data : {
				file : fileName
			},
			success : function(data) {

				if (data != null && data != "" && data == true) {

					window.location.href = '/';

				} else if (data != null && data != "") {

					window.location.href = '/login';

				}
			}
		});

	}

	function fileNameFilter() {

		var valThis = _modalInput.value.toLowerCase();
		var listItem = _fileList.getElementsByTagName("a");

		for (var i = 0; i < listItem.length; i++) {

			var text = listItem[i].text.toLowerCase();

			(text.indexOf(valThis) >= 0) ? listItem[i].setAttribute('class', "list-group-item small") : listItem[i].setAttribute('class', 'hidden');

		}

	}

	function showFileName(event) {

		_fileSelected.innerHTML = this.data.name;
		_fileSelectedName.value = this.data.name;
		_btnProceed.disabled = false;

	}

}

$(document).ready(function() {
	var selection = new SelectionController();
});