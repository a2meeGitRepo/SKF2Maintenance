(function(){
	'use strict';

	angular.module('myApp.assetInfo',[

		])
	.config(function($stateProvider){
		$stateProvider
		.state('main.assetInfo', {
            url: "/assetInfo",
            views: {
                "sub": {templateUrl: "templates/assetInfo/assetInfo.html",
                		controller : "AssetInfoController as vm"}
            }
        })
       
        
	});

})();