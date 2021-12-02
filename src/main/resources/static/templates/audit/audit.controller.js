(function() {
	'use strict';

	angular.module('myApp.audit').controller('AuditController', AuditController);

	AuditController.$inject = [ '$state', '$log',
			'$scope', 'toastr','localStorageService','ApiEndpoint','loginFactory','genericFactory','$document','$rootScope' ];

	/* @ngInject */
	function AuditController($state, $log, $scope, toastr,localStorageService,ApiEndpoint,loginFactory,genericFactory,$document,$rootScope) {
		var auditUrl = ApiEndpoint.url+"audit";
		
		var vm = angular.extend(this, {
			edit:edit
		});
	
		(function activate() {
		loadAudit();
		})();
		function loadAudit(){
		var msg=""
			var url=auditUrl+"/getAudits"
			genericFactory.getAll(msg,url).then(function(response) {
					vm.audits= response.data;
					console.log("audits: "+JSON.stringify(vm.audits))
					
					
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
