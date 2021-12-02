(function() {
	'use strict';

	angular.module('myApp.changeManagement').controller('ChangeManagementController', ChangeManagementController);

	ChangeManagementController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function ChangeManagementController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var changeMangementUrl = ApiEndpoint.url+"changeMangement";
		
		var vm = angular.extend(this, {
			edit:edit
		});
	
		(function activate() {
		loadChangeManagement();
		})();
	function loadChangeManagement(){
		var msg=""
			var url=changeMangementUrl+"/getAllChangeManagement"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.changeMangements= response.data;
					console.log("changeMangements: "+JSON.stringify(vm.changeMangements))
					
					
				});
	}
	
	function edit(changeManagement){
		$scope.changeManagement=changeManagement
		$scope.addTab=true
		getSpare(changeManagement.id)
		getImage(changeManagement.id)
	}
	function getSpare(id){
		var msg=""
			var url=changeMangementUrl+"/getSpareByChangeManagement?changemanagementId="+id
			genericFactory.getAll(msg,url).then(function(response) {
					vm.spares= response.data;
					console.log("spares: "+JSON.stringify(vm.spares))
					
					
				});
	}
	$scope.cancel=function(){
		$scope.addTab=false
	}
	function getImage(id){
		var msg=""
			var url=changeMangementUrl+"/getImageByChangeManagement?changemanagementId="+id
			genericFactory.getAll(msg,url).then(function(response) {
					vm.images= response.data;
					console.log("images: "+JSON.stringify(vm.images))
					
					
				});
	}
		
}
	
	
	
	
})();
