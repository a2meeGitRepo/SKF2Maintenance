angular.module('myApp', [
	'ui.router',
	'LocalStorageModule',
	'angularUtils.directives.dirPagination',
	'ngCookies',
	'toastr',
	'ngJsonExportExcel',
	
	'myApp.main',
	'myApp.home',
	'myApp.login',
	'myApp.generic',
	'myApp.machine',
	'myApp.spare',
	'myApp.spindle',
	'myApp.location',
	'myApp.checkPointAm',
	'myApp.machineOwner',
	'myApp.maintanceCheckList',
	'myApp.scheduleMaintenance',
	'myApp.machineMapping',
	'myApp.maintenance',
	'myApp.breakdown',
	'myApp.maintenanceDone',
	'myApp.breakdownUpdate',
	'myApp.upload',
	'myApp.changeManagement',
	'myApp.report',
	'myApp.assetInfo',
	'myApp.auditStagesAndItems',
	'myApp.audit',
	'myApp.user',

,
])

.value('_', window._)

.constant('ApiEndpoint', {
	
	//url: 'http://localhost:8080/',
	//url: 'http://163.157.31.156:8080/',
	url: 'http://skf2.adp.ind.in:8080/',
userKey : 'allState',
	headerKey : ''
})
.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
  
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }else if($location.path() == '/login' && $rootScope.globals.currentUser){
            	$location.path('/main/home');
            }
        });
    }])

/*.config(function($urlRouterProvider,$locationProvider) {
	 if none of the above states are matched, use this as the fallback
	$urlRouterProvider.otherwise('/main/home');
	 // use the HTML5 History API
	
	 $locationProvider.hashPrefix('');
}); */