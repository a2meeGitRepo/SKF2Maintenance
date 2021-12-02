(function() {
	'use strict';

	angular.module('myApp.spare').controller('SpareController', SpareController);

	SpareController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function SpareController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var spareUrl = ApiEndpoint.url+"spares";
		var locationUrl = ApiEndpoint.url+"location";
		var userDetail = localStorageService.get(ApiEndpoint.userKey);
		var vm = angular.extend(this, {
			perPage : 10,
			total_count:0,
			pageno:1,
//			getAllLocation:getAllLocation,
		});
	
		(function activate() {
			loadSpares();
		console.log("User :: "+JSON.stringify(userDetail))
		})();
		
		// current page
		$scope.pagination = {
		        current: 1
		    };
		
		// page changed 
		$scope.pageChanged = function(pageNo){
			vm.pageno=pageNo;
			loadSpares();
			
		}
		$scope.searchByPagination=function (search){
			loadSpares();
			console.log("I AM Seraching ")
			
		}
		
		$scope.add=function (){
			$scope.addTab=true;
			//loadSpares();
			getAllLocation();
		}
		
		$scope.cancel=function (){
			$scope.addTab=false;
			$scope.spare={}
		}	
		
		function getAllLocation(){
		var msg="";
		var url=locationUrl+"/getAllLocation";
		genericFactory.getAll(msg,url).then(function(response) {
				vm.locations = response.data;
								
			});
		}
		
		$scope.editSpare=function(spare){
			getAllLocation();
			$scope.spare=spare;
			$scope.addTab=true;
			
		}
		
		$scope.deleteSpare=function(spare){
			  var msg=""
					 var url =spareUrl+"/deleteSpares";
						genericFactory.add(msg,url,spare).then(function(response) {
							var responceObj=response.data
							loadSpares();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
		}
		
		$scope.changeStatus=function(spare){
			  var msg=""
					 var url =spareUrl+"/changeStatus";
						genericFactory.add(msg,url,spare).then(function(response) {
							var responceObj=response.data
							loadSpares();
							
							
							if(responceObj.code==200){
								toastr.success(responceObj.msg);	
							}else{
								toastr.error(responceObj.msg);
							}
							
					});
			
		}
		
		//************************* EXPORT *****************************//
		$scope.filename="Spares"
			vm.labels={'partName': 'Part Name','stockCodeDetails':'Stock Code Details','stockCode':'Stock Code','location.locationName':'Location Name','cost':'Cost','status':'Status'}
		
		$scope.newExcel= function(){
	    	  getAllSparesForExport();
				 //$rootScope.loader=true;
	    	  //document.getElementById('btnExport').click();
				 setTimeout(function(){
					 
					 document.getElementById('btnExport').click();
					 //$rootScope.loader=false;
					  $rootScope.$digest();
					},1000);		
				
			}
			function getAllSparesForExport(){
				 var msg=""
					 var url =spareUrl+"/getAllSpares"; 
				genericFactory.getAll(msg,url).then(function(response) {
					vm.allSpares = response.data;
					
					console.log("allSpares: "+JSON.stringify(vm.allSpares))
									
				});
				
			}
		
		
		
		//*************************************************************//
		function loadSpares(){
			loadSpareCount();			
			
			var url;
			var msg=""
			
				if($scope.search==""||$scope.search==undefined){
					url =spareUrl+"/getlistSparesByLimit/"+vm.pageno+'/'+vm.perPage;
				}
			else{
				url=spareUrl+"/getlistSparesByLimitAndSearch?searchText="+$scope.search+"&pageNo="+vm.pageno+'&perPage='+vm.perPage;
			}
				
				
				// var url =spareUrl+"/getlistSparesByLimit/"+vm.pageno+'/'+vm.perPage;
				genericFactory.getAll(msg,url).then(function(response) {
				vm.spares = response.data;
				
				console.log("spares: "+JSON.stringify(vm.spares))
								
			});
			
			
		}
		function loadSpareCount(){
			var msg=""
				 var url =""
				if($scope.search==""||$scope.search==undefined){
					url =spareUrl+"/getSparesCount"
				}
			else{
				url=spareUrl+"/getSparesCountAndSearch?searchText="+$scope.search;
			}
			genericFactory.getAll(msg,url).then(function(response) {
			vm.total_count = response.data;
			console.log("total_count: "+JSON.stringify(vm.total_count))
							
		});
		}
		
		$scope.save=function (spare){
		 	console.log("spare "+JSON.stringify(spare))
          
      
        if(spare==undefined|| spare.spareName==undefined||spare.spareName==""){
			$scope.spareNameErr=true;
			return;
		}else{
			$scope.spareNameErr=false;
		}
		
		if(spare.detials==undefined||spare.detials==""){
			$scope.detialsErr=true;
			return;
		}else{
			$scope.detialsErr=false;
		}
        
       if(spare.stockCode==undefined||spare.stockCode==""){
			$scope.stockCodeErr=true;
			return;
		}else{
			$scope.stockCodeErr=false;
		}
       
       if(spare.location==undefined||spare.location==""){
			$scope.locationErr=true;
			return;
		}else{
			$scope.locationErr=false;
		}
      
       if(spare.cost==undefined||spare.cost==""){
			$scope.costErr=true;
			return;
		}else{
			$scope.costErr=false;
		}
      
     
          
         $scope.loderview=true;
         spare.status=1
         spare.addedDate=new Date();
         spare.addedBy=userDetail.user_id
         
       	console.log("spare "+JSON.stringify(spare))

         var msg=""
			 var url =spareUrl+"/addSpares";
				genericFactory.add(msg,url,spare).then(function(response) {
					var responceObj=response.data
					loadSpares();
					 $scope.addTab=false;
					$scope.spare={}
					
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
