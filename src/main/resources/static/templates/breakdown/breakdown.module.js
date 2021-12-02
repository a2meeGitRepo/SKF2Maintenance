(function(){
	'use strict';

	angular.module('myApp.breakdown',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.breakdown', {
            url: "/breakdown",
            views: {
                "sub": {templateUrl: "templates/breakdown/breakdown.html",
                		controller : "BreakdownController as vm"}
            }
        })
       
        
	});

})();