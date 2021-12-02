(function() {
	'use strict';

	angular.module('myApp.spindle').controller('SpindleController', SpindleController);

	SpindleController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function SpindleController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var spindleUrl = ApiEndpoint.url+"spindleDetails";
		
		var vm = angular.extend(this, {
			perPage : 10,
			total_count:0,
			pageno:1,
		});
	
		(function activate() {
			loadSpindle();
		
		})();
		
		// current page
		$scope.pagination = {
		        current: 1
		    };
		
		// page changed 
		$scope.pageChanged = function(pageNo){
			vm.pageno=pageNo;
			loadSpindle();
			
		}
		$scope.searchByPagination=function (search){
			loadSpindle();
			console.log("I AM Seraching ")
			
		}
		
		$scope.add=function (){
			$scope.addTab=true;
		}
		
		$scope.cancel=function (){
			$scope.addTab=false;
			$scope.spindle={};
		}	
		
		$scope.editSpindle=function(spindle){
			loadSpindle();
			$scope.spindle=spindle;
			$scope.addTab=true;
			
		}
		
		$scope.deleteSpindle=function(spindle){
			  var msg=""
					 var url =spindleUrl+"/deleteSpindle";
						genericFactory.add(msg,url,spindle).then(function(response) {
							var responceObj=response.data
							loadSpindle();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		
		$scope.changeStatus=function(spindle){
			  var msg=""
					 var url =spindleUrl+"/changeStatus";
						genericFactory.add(msg,url,spindle).then(function(response) {
							var responceObj=response.data
							loadSpindle();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
			
		}
		
		//************************* EXPORT *****************************//
		$scope.filename="Spindles"
			vm.labels={'name': 'Spindle Name','type':'Spindle Type','details':'Spindle Details','sparesInfo':'Spares Information','status':'Status'}
		
		$scope.newExcel= function(){
	    	  getAllSpindleForExport();
				 //$rootScope.loader=true;
	    	  //document.getElementById('btnExport').click();
				 setTimeout(function(){
					 
					 document.getElementById('btnExport').click();
					 //$rootScope.loader=false;
					  $rootScope.$digest();
					},1000);		
				
			}
			function getAllSpindleForExport(){
				 var msg=""
					 var url =spindleUrl+"/getAllSpindle"; 
				genericFactory.getAll(msg,url).then(function(response) {
					vm.allSpindle = response.data;
					
					console.log("allSpindle : "+JSON.stringify(vm.allSpindle))
									
				});
				
			}
		
		
		
		//*************************************************************//
		
		function loadSpindle(){
			loadSpindleCount();			
			
			var url;
			var msg=""
			
				if($scope.search==""||$scope.search==undefined){
					url =spindleUrl+"/getlistSpindleByLimit/"+vm.pageno+'/'+vm.perPage;
				}
			else{
				url=spindleUrl+"/getlistSpindleByLimitAndSearch?searchText="+$scope.search+"&pageNo="+vm.pageno+'&perPage='+vm.perPage;
			}
				
				
				// var url =spindleUrl+"/getlistMaterialsByLimit/"+vm.pageno+'/'+vm.perPage;
				genericFactory.getAll(msg,url).then(function(response) {
				vm.spindles = response.data;
				
				console.log("Spindle: "+JSON.stringify(vm.spindles))
								
			});
			
			
		}
		function loadSpindleCount(){
			var msg=""
				 var url =""
				if($scope.search==""||$scope.search==undefined){
					url =spindleUrl+"/getSpindleCount"
				}
			else{
				url=spindleUrl+"/getSpindleCountAndSearch?searchText="+$scope.search;
			}
			 //var url =spindleUrl+"/getMaterialsCount";
			genericFactory.getAll(msg,url).then(function(response) {
			vm.total_count = response.data;
			console.log("total_count: "+JSON.stringify(vm.total_count))
							
		});
		}
		
		$scope.save=function (spindle){
          
		   	console.log("Spindle "+JSON.stringify(spindle))
        if(spindle==undefined||spindle.spindleName==undefined||spindle.spindleName==""){
				$scope.SpindleNameError=true;
				return;
			}else{
				$scope.SpindleNameError=false;
			}
        if(spindle.spindleType==undefined||spindle.spindleType==""){
			$scope.SpindleTypeError=true;
			return;
		}else{
			$scope.SpindleTypeError=false;
		}
		
		if(spindle.spindleDetails==undefined||spindle.spindleDetails==""){
			$scope.spindleDetailsErr=true;
			return;
		}else{
			$scope.spindleDetailsErr=false;
		}
        
       if(spindle.sparesInfo==undefined||spindle.sparesInfo==""){
			$scope.SparesInfoError=true;
			return;
		}else{
			$scope.SparesInfoError=false;
		}
		
		
          
         $scope.loderview=true;
     
         
       	console.log("Spindle "+JSON.stringify(spindle))

         var msg=""
			 var url =spindleUrl+"/addSpindle";
				genericFactory.add(msg,url,spindle).then(function(response) {
					var responceObj=response.data
					loadSpindle();
					$scope.addTab=false;
					$scope.spindle={}
					
					if(responceObj.code==200){
						toastr.success(responceObj.msg);	
					}else{
						toastr.error(responceObj.msg);
					}
					
			});
       /* toastr.success('Submitted successfully!!')
       $scope.addTab=false;
        return;*/
    }
		
		
}
	
	
	
	
})();
