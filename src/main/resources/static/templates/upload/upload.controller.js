(function() {
	'use strict';

	angular.module('myApp.upload').controller('UploadController', UploadController);

	UploadController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope','$http'];

	/* @ngInject */
	function UploadController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope,$http) {
		var uploadsUrl = ApiEndpoint.url+"upload";
		
		var userDetail = localStorageService.get(ApiEndpoint.userKey);
		console.log("User :: "+JSON.stringify(userDetail))

		var vm = angular.extend(this, {
			uploadMachine:uploadMachine
		});
	
		(function activate() {
		
		})();
		$scope.uploadTypeFn=function(uploadType){
			console.log("uploadType"+uploadType)
		}
		function uploadMachine(){
			console.log("UPLOAD"+$scope.uploadType)
			var file ="";
			var url ="";
			if($scope.uploadType=='Machine'){
				 file = document.getElementById('uploadMachine').files[0];
				 url = uploadsUrl + "/uploadMachine";
			}
			if($scope.uploadType=='Spare'){
				 file = document.getElementById('uploadSpare').files[0];
				 url = uploadsUrl + "/uploadSpares";
			}
			if($scope.uploadType=='Spindle'){
				 file = document.getElementById('uploadSpindle').files[0];
				 url = uploadsUrl + "/uploadSpindle";
			}
			
			if($scope.uploadType=='Location'){
				 file = document.getElementById('uploadLocation').files[0];
				 url = uploadsUrl + "/uploadLocation";
			}
			if($scope.uploadType=='MM Maintemance'){
				 file = document.getElementById('uploadMMMaintemance').files[0];
				 url = uploadsUrl + "/uploadMMMaintemance";
			}
			if($scope.uploadType=='TBM Maintemance'){
				 file = document.getElementById('uploadTBMMaintemance').files[0];
				 url = uploadsUrl + "/uploadTBMMaintemance";
			}
			if($scope.uploadType=='Drawing'){
				
				 if($scope.drawingName==undefined||$scope.drawingName==""){
					 $scope.drawingNameErr=true
					 return;
				 }else{
					 $scope.drawingNameErr=false
				 }
				 file = document.getElementById('uploadDrawing').files[0];
				 url = uploadsUrl + "/uploadTest?name="+$scope.drawingName;
			}
			if($scope.uploadType=='MMTR'){
				 file = document.getElementById('uploadMMTR').files[0];
				 url = uploadsUrl + "/uploadMMTR";
			}
			if($scope.uploadType=='MTBF'){
				 file = document.getElementById('uploadMTBF').files[0];
				 url = uploadsUrl + "/uploadMTBF";
			}
			if($scope.uploadType=='Downtime'){
				 file = document.getElementById('uploadDowntime').files[0];
				 url = uploadsUrl + "/uploadDowntime";
			}
			if($scope.uploadType=='Breakdown'){
				 file = document.getElementById('uploadBreakdown').files[0];
				 url = uploadsUrl + "/uploadBreakdown";
			}
			/*if($scope.uploadType=='SetupChart'){
				 file = document.getElementById('uploadSetupChart').files[0];
				 url = uploadsUrl + "/uploadSetupChart";
			}
			*/
			if($scope.uploadType=='SetupChart'){
				
				 if($scope.SetupChartName==undefined||$scope.SetupChartName==""){
					 $scope.SetupChartNameErr=true
					 return;
				 }else{
					 $scope.SetupChartNameErr=false
				 }
				 file = document.getElementById('uploadSetUpChart').files[0];
				 url = uploadsUrl + "/uploadSetUpChart?name="+$scope.drawingName;
			}
		if (file == undefined) {
			toastr.error('Please Select a xlsx File');
			return;
		}

		var fileName = file.name;
		var extension = ".xlsx";
		var extension1 = ".xls";
		if($scope.uploadType!='Drawing'&& $scope.uploadType!='SetupChart'){
			if(!fileName.includes(extension1)){
				toastr.error('Selected File is not a xlsx or xls');
				return;
			}
		}
				

		$('.loading').show();
		var fd = new FormData();
		fd.append('file', file);
	
		console.log("URL :: "+url)
		$http.post(url, fd, {
			transformRequest: angular.identity,
			headers: {
				'Content-Type': undefined
			}
		})
		.then(function successCallback(response) {
			
			$('.loading').hide();
			//window.alert("File uploaded successfully!");
		
			toastr.success('Uploaded....', 'Succesful !!',{ timeOut: 10000 });					
			loadDesks();
		}, function errorCallback(response) {
	    	$('.loading').hide();
			//window.alert("File upload - unsuccessfull!");
			//init();
			toastr.error('Upload....', 'UnSuccesful !!');
			//loadDesks();
				    });

		angular.element("input[type='file']").val(null);
	}
		
	
}
	
})();
