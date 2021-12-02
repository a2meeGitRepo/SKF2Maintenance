(function(){
	'use strict';

	angular.module('myApp.maintanceCheckList',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.maintanceCheckList', {
            url: "/maintenceChecklist",
            views: {
                "sub": {templateUrl: "templates/maintenanceCheckList/maintenanceCheckList.html",
                		controller : "MaintenanceCheckListController as vm"}
            }
        })
       
        
	});

})();