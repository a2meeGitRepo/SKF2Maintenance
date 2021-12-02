(function(){
	'use strict';

	angular.module('myApp.breakdownUpdate',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.breakdownUpdate', {
            url: "/breakdownUpdate",
            views: {
                "sub": {templateUrl: "templates/breakdownUpdate/breakdownUpdate.html",
                		controller : "BreakdownUpdateController as vm"}
            }
        })
       
        
	});

})();