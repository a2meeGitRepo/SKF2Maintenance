(function(){
	'use strict';

	angular.module('myApp.auditStagesAndItems',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.auditStagesAndItems', {
            url: "/auditStagesAndItems",
            views: {
                "sub": {templateUrl: "templates/auditStagesAndItems/auditStagesAndItems.html",
                		controller : "AuditStagesAndItemsController as vm"}
            }
        })
       
        
	});

})();