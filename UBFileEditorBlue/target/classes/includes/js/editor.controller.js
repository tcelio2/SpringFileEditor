// This class controls the copybook sidebar by attaching events on it
// Also controls any model related to copybook
function UBFileEditor(){
	
	var _copyBook = null;
	
	/**
	 * Initializes the used components and binds with each other.
	 */
	function init(){
		
		// TODO Update the file name
		
		// Initialize the components
		initFilehandler();
		initCopybook();
		
	} init();
	
	function initFilehandler(){
		
	}
	
	function initCopybook(){
		_copyBook = new Copybook();
		_copyBook.onPreviewClick(function (){
			// validate(_copyBook.getSelectedCopybook(), _filehandler.getFileContent());
			// _previewArea.preview(_copyBook.getSelectedCopybook(), _filehandler.getFileContent());
		})
	}
	
	
}


$(document).ready(function (){
	var controller = new UBFileEditor();
	
});