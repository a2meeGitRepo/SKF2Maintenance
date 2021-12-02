(function(){
	'use strict';

	angular.module('myApp.changeManagement',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.changeManagement', {
            url: "/changeManagement",
            views: {
                "sub": {templateUrl: "templates/changeManagement/changeManagement.html",
                		controller : "ChangeManagementController as vm"}
            }
        })
       
        
	});

})();