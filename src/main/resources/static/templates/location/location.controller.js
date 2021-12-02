(function() {
	'use strict';

	angular.module('myApp.location').controller('LocationController', LocationController);

	LocationController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function LocationController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var locationUrl = ApiEndpoint.url+"location";
		
		var vm = angular.extend(this, {
			perPage : 10,
			total_count:0,
			pageno:1,
		});
	
		(function activate() {
			loadLocation();
		
		})();
		
		// current page
		$scope.pagination = {
		        current: 1
		    };
		
		// page changed 
		$scope.pageChanged = function(pageNo){
			vm.pageno=pageNo;
			loadLocation();
			
		}
		$scope.searchByPagination=function (search){
			loadLocation();
			console.log("I AM Seraching ")
			
		}
		
		$scope.add=function (){
			$scope.addTab=true;
		}
		 
		$scope.cancel=function (){
			$scope.addTab=false;
			$scope.location={}
		}	
		
		$scope.editLocation=function(location){
			loadLocation();
			$scope.location=location;
			$scope.addTab=true;
			
		}
		
		$scope.deleteLocation=function(location){
			  var msg=""
					 var url =locationUrl+"/deleteLocation";
						genericFactory.add(msg,url,location).then(function(response) {
							var responceObj=response.data
							loadLocation();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		
		$scope.changeStatus=function(location){
			  var msg=""
					 var url =locationUrl+"/changeStatus";
						genericFactory.add(msg,url,location).then(function(response) {
							var responceObj=response.data
							loadLocation();
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
			
		}
		
		//************************* EXPORT *****************************//
		$scope.filename="Locations"
			vm.labels={'locationName': 'Location Name','locationCode':'Location Code','status':'Status'}
		
		$scope.newExcel= function(){
	    	  getAllLocationForExport();
				 //$rootScope.loader=true;
	    	  //document.getElementById('btnExport').click();
				 setTimeout(function(){
					 
					 document.getElementById('btnExport').click();
					 //$rootScope.loader=false;
					  $rootScope.$digest();
					},1000);		
				
			}
			function getAllLocationForExport(){
				 var msg=""
					 var url =locationUrl+"/getAllLocation"; 
				genericFactory.getAll(msg,url).then(function(response) {
					vm.allLocation = response.data;
					
					console.log("allLocation : "+JSON.stringify(vm.allLocation))
									
				});
				
			}
		
		
		
		//*************************************************************//
		
		function loadLocation(){
			loadLocationCount();			
			
			var url;
			var msg=""
			
				if($scope.search==""||$scope.search==undefined){
					url =locationUrl+"/getlistLocationByLimit/"+vm.pageno+'/'+vm.perPage;
				}
			else{
				url=locationUrl+"/getlistLocationByLimitAndSearch?searchText="+$scope.search+"&pageNo="+vm.pageno+'&perPage='+vm.perPage;
			}
				
				
				// var url =locationUrl+"/getlistMaterialsByLimit/"+vm.pageno+'/'+vm.perPage;
				genericFactory.getAll(msg,url).then(function(response) {
				vm.locations = response.data;
				
				console.log("Location: "+JSON.stringify(vm.locations))
								
			});
			
			
		}
		function loadLocationCount(){
			var msg=""
				 var url =""
				if($scope.search==""||$scope.search==undefined){
					url =locationUrl+"/getLocationCount"
				}
			else{
				url=locationUrl+"/getLocationCountAndSearch?searchText="+$scope.search;
			}
			 //var url =locationUrl+"/getMaterialsCount";
			genericFactory.getAll(msg,url).then(function(response) {
			vm.total_count = response.data;
			console.log("total_count: "+JSON.stringify(vm.total_count))
							
		});
		}
		
		$scope.save=function (location){
          
          
        if(location==undefined||location.locationName==undefined){
				$scope.locationNameError=true;
				return;
			}else{
				$scope.locationNameError=false;
			}
        if(location.locationCode==undefined||location.locationCode==""){
			$scope.locationCodeError=true;
			return;
		}else{
			$scope.locationCodeError=false;
		}
        
         $scope.loderview=true;
     
         
       	console.log("location "+JSON.stringify(location))

         var msg=""
			 var url =locationUrl+"/addLocation";
				genericFactory.add(msg,url,location).then(function(response) {
					var responceObj=response.data
					loadLocation();
					 $scope.addTab=false;
					$scope.location={}
					
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
