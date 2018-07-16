// This class controls the copybook sidebar by attaching events on it
// Also controls any model related to copybook
function Copybook() {

	var _selectedCopybook = null;
	var _modalListOfCopybook = null;
	var _modalInput = null;
	var _addCopybook = null;
	var _searchCopyBook = null;
	var _copybookTables = null;
	var _ulXmlList = null;
	var _copybookSelected = null;

	/**
	 * Initializes the object. It will bind the buttons, list, searchs with a
	 * javascript element and it's events like the click of preview button, a
	 * public event, or openModal, a private event.
	 */
	function init() {

		_modalListOfCopybook = document.getElementById('listOfCopybooks');
		_modalInput = document.getElementById('inputCopybook');
		_modalXmlData = document.getElementById('xmlData');
		_addCopybook = document.getElementById('addCopybook');
		_searchCopyBook = document.getElementById('searchCopyBook');
		_copybookTables = document.getElementById('copybookTables');
		_ulXmlList = document.getElementById('ulXmlList');
		_copybookSelected = document.getElementById('copybookSelected');

		_modalInput.addEventListener('keyup', copybookFilter, false);
		
		_searchCopyBook.onclick = showModal;
		_addCopybook.onclick = addCopybook;
	}
	init();

	function showModal() {
		copyBookList();
		_modalInput.value = '';
		_copybookTables.innerHTML = "";
		_ulXmlList.innerHTML = "";
	}

	function placeCursorAtEnd() {
		if (this.setSelectionRange) {
			// Double the length because Opera is inconsistent about
			// whether a carriage return is one character or two.
			var len = this.value.length * 2;
			this.setSelectionRange(len, len);
		} else {
			// This might work for browsers without setSelectionRange support.
			this.value = this.value;
		}

		if (this.nodeName === "TEXTAREA") {
			// This will scroll a textarea to the bottom if needed
			this.scrollTop = 999999;
		}
	}

	function addCopybook() {
		var span = document.createElement("span");
		span.setAttribute('class', "glyphicon glyphicon-file");

		var node = document.createTextNode(_modalInput.value);
		span.appendChild(node);
		_copybookSelected.innerHTML = "";
		_copybookSelected.appendChild(span);

		var ul = document.getElementById('previewUL');
		ul.innerHTML = "";
		var li = _ulXmlList.getElementsByTagName('li');
		
		var liOriginalName = document.createElement('li');
		liOriginalName.appendChild(document.createTextNode(li[0].getElementsByTagName('a')[0].innerHTML));
		ul.appendChild(liOriginalName);
		
		var liSize = document.createElement('li');
		liSize.appendChild(document.createTextNode(li[1].getElementsByTagName('a')[0].innerHTML));
		ul.appendChild(liSize);
		
		var liFileName = document.createElement('li');
		liFileName.appendChild(document.createTextNode(li[2].getElementsByTagName('a')[0].innerHTML));
		ul.appendChild(liFileName);

	}

	/**
	 * EVENTS
	 */
	var _onPreviewClickEvent = null;
	this.onPreviewClick = function(onPreviewClickEvent) {
		if (onPreviewClickEvent != null
				&& typeof onPreviewClickEvent == "function") {
			_onPreviewClickEvent = onPreviewClickEvent;

		}
	};

	function fireOnPreviewClickEvent(self) {
		if (_onPreviewClickEvent != null
				&& typeof _onPreviewClickEvent == "function") {
			_onPreviewClickEvent(_self);

		}
	}

	function copybookFilter() {
		_ulXmlList.innerHTML = "";
		_copybookTables.innerHTML = "";

		var valThis = _modalInput.value.toLowerCase();
		var listItem = _modalListOfCopybook.getElementsByTagName("a");

		for (var i = 0; i < listItem.length; i++) {

			var text = listItem[i].text.toLowerCase();

			(text.indexOf(valThis) >= 0) ? listItem[i].setAttribute('class',
					'visible') : listItem[i].setAttribute('class', 'hidden');
		}
	}

	function copyBookList() {
		$.ajax({
			url : "/copybook",
			dataType : "json",
			success : function(data) {
				if (data != null) {

					for (var i = 0; i < data.length; i++) {
						var li = document.createElement('li');
						var a = document.createElement('a');

						a.appendChild(document.createTextNode(data[i].Name));
						a.xmlName = data[i].Name;
						a.href = "#";
						a.onclick = listItemClick;

						li.appendChild(a);
						_modalListOfCopybook.appendChild(li);
					}
				}
			}

		})
	}

	function copybookValues(name, value) {
		var li = document.createElement('li');
		var a = document.createElement('a');

		var text = name;
		if (value === "") {
			text = text + ": ----------";
		} else {
			text = text + ": " + value;
		}

		a.appendChild(document.createTextNode(text));
		a.setAttribute("href", "#");
		a.setAttribute("class", "list-group-item");

		li.appendChild(a);

		_ulXmlList.appendChild(li);
	}
	
	function listItemClick(event) {
		_ulXmlList.innerHTML = "";
		_copybookTables.innerHTML = "";
		_modalInput.value = this.xmlName;
		document.getElementById('smallCbName').innerHTML = this.xmlName;

		$
				.ajax({
					url : "/copybook/" + this.xmlName + "/",
					dataType : "json",
					success : function(data) {

						if (data != null) {
							copybookValues("Original Name", data.originalName);
							copybookValues("Size", data.size);
							copybookValues("File Name", data.fileName);

							var table = document.createElement("table");
							table.setAttribute('id', "fieldList");
							table.setAttribute('class', "table table-hover");

							for (var i = 0; i < data.fieldList.length; i++) {

								var row0 = table.insertRow(0 + 7 * i);
								row0.setAttribute('class', "rowblue");

								var cellName0 = row0.insertCell(0);
								cellName0.innerHTML = "Field " + i;
								cellName0.setAttribute('colspan', "2");
								cellName0.setAttribute('class', "centerCell");

								var row1 = table.insertRow(1 + 7 * i);

								var cellName0 = row1.insertCell(0);
								cellName0.innerHTML = "Name";

								var cellName1 = row1.insertCell(1);
								cellName1.innerHTML = data.fieldList[i].name;

								var row2 = table.insertRow(2 + 7 * i);

								var cellFieldType0 = row2.insertCell(0);
								cellFieldType0.innerHTML = "Field Type";

								var cellFieldType1 = row2.insertCell(1);
								cellFieldType1.innerHTML = data.fieldList[i].fieldType;

								var row3 = table.insertRow(3 + 7 * i);

								var cellMode0 = row3.insertCell(0);
								cellMode0.innerHTML = "Mode";

								var cellMode1 = row3.insertCell(1);
								cellMode1.innerHTML = data.fieldList[i].mode;

								var row4 = table.insertRow(4 + 7 * i);

								var cellOriginalName0 = row4.insertCell(0);
								cellOriginalName0.innerHTML = "Original Name";

								var cellOriginalName1 = row4.insertCell(1);
								cellOriginalName1.innerHTML = data.fieldList[i].originalName;

								var row5 = table.insertRow(5 + 7 * i);

								var cellDisplay0 = row5.insertCell(0);
								cellDisplay0.innerHTML = "Display";

								var cellDisplay1 = row5.insertCell(1);
								cellDisplay1.innerHTML = data.fieldList[i].display;

								var row6 = table.insertRow(6 + 7 * i);

								var cellStorage0 = row6.insertCell(0);
								cellStorage0.innerHTML = "Storage";

								var cellStorage1 = row6.insertCell(1);
								cellStorage1.innerHTML = data.fieldList[i].storage;
							}
							var div = document.getElementById("copybookTables");
							div.appendChild(table);
						}
					}
				})
	}
	
	/** PUBLIC CALLS
	 */
	// clear all input, locks the preview button and clear the copybook
	// selected.
	this.reset = function() {

	};
	// lock the button and the copybook's selection
	this.lock = function() {

	};
	// Unlock the button and the copybook's selection
	this.unlock = function() {

	};

	/**
	 * GETTER & SETTERS
	 */
	// LLicursi : Public getter
	this.getSelectedCopybook = function() {
		return _selectedCopybook;
	};
}
