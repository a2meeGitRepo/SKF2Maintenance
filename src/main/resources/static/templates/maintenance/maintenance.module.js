(function(){
	'use strict';

	angular.module('myApp.maintenance',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.maintenance', {
            url: "/maintenance",
            views: {
                "sub": {templateUrl: "templates/maintenance/maintenance.html",
                		controller : "MaintenanceController as vm"}
            }
        })
       
        
	});

})();