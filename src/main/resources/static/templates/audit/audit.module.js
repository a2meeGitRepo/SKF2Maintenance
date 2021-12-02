(function(){
	'use strict';

	angular.module('myApp.audit',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.audit', {
            url: "/audit",
            views: {
                "sub": {templateUrl: "templates/audit/audit.html",
                		controller : "AuditController as vm"}
            }
        })
       
        
	});

})();