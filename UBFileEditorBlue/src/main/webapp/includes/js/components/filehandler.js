// This class controls the area displaying the content of the file starting
// at the buffer position until the buffersize is reached.
// May also try to handle the RDW and include 
function Filehandler(){
	
	// Objects binded
	var _displayDataArea = null;
	var _inputBuffer = null;
	var _buttonUpdate = null;
	var _isDataReady = null;

	var _fileByteContent = null;
	/**
	 * Initializes the object.
	 * It will bind the buttons, inputs and areas with a javascript element and it's events
	 * like the click of preview button, a public event, or openModal, a private event.
	 */
	function init(){
		_displayDataArea = document.getElementById("panelArea");
		_inputBuffer = document.getElementById("bufferValue");
		_buttonUpdate = document.getElementById("getButton");
		_isDataReady = false;
				
		setDefaultValues();
						
		_buttonUpdate.onclick = function (){			
			
			if (validateBuffersInputs()){
				updateDisplayArea();
			}
		};
		
		_inputBuffer.onkeypress = keyPressValidations;
			
	} init();
	
	function keyPressValidations(e) {			
		var key=(window.event)?event.keyCode:e.which;   
	    if((key>47 && key<58)) return true;
	    else{
	    	if (key==8 || key==0) return true;
		else  return false;
	    }
	}
	
	function setDefaultValues(){
		_inputBuffer.value = '';	
	}
	
	function updateDisplayArea(){
		var intNavNumber = _inputBuffer.value;
		
		//TODO: This part will be a navigator. The first page will start from the first byte (0). 
		if(intNavNumber != 0){
			intNavNumber = intNavNumber - 1;
		}
		
		$.ajax({
			  type: 'post',
			  url: '/filehandler',
			  data : {navNumber : intNavNumber},
			  success: function( resp ) {
				  
				if (resp != null){	
					
					alert(resp);
					
					//for (var i = 0; i < resp.length; i++) {
						_displayDataArea.innerHTML=resp.replace(/\n/g, "<br/>");	
					
						_isDataReady = true;
					//}
					 
				} else {
					
					alert("It was not possible to return the snippet code.");
				}
			    
			  },
			  error: function( req, status, err ) {
				 alert("Something went wrong: " + status + " - " + err);
			    console.log( 'something went wrong', status, err );
			  }
		});
		
	}
	
	function validateBuffersInputs(){
		var intBuf = _inputBuffer.value;
		
		if(intBuf.trim() == ''){
			
			alert("The navigator number is required.");
			return false;
			
		}else if(isNaN(intBuf.trim())){
			
			alert("Please, insert numbers only."); 
			return false; 
			
		}
		
		return true;
		
	}
	
	/**
	 * EVENTS
	 */
	
	/**
	 * PUBLIC CALLS
	 */
	// clear all input and the display area	
	this.reset = function(){
		
		_displayDataArea.value = '';
		_inputBuffer.value = '';
		_displayDataArea.innerHTML = '';
		_isDataReady = false;
		_buttonUpdate.className ="btn btn-primary btn-block";
	
	};
	// lock the button and all inputs	
	this.lock = function(){
		
		_inputBuffer.disabled = true;
		_buttonUpdate.className ="btn btn-primary btn-block disabled";
		
	};
	// Unlock the button and all inputs	
	this.unlock = function(){
		
		_inputBuffer.disabled = false;
		_buttonUpdate.className ="btn btn-primary btn-block";
		
	};
	
	this.isdataready = function(){
		
		return _isDataReady;
		
	};
	
	
	/**
	 * GETTER & SETTERS
	 */
	// LLicursi : Public getter
	this.getFileContent = function(){
		return _fileByteContent;
	};
	
}

$(document).ready(function() {
	var controller = new Filehandler();
});
