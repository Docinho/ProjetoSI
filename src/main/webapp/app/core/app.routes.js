app.config(function ($routeProvider, $httpProvider, BASE_TEMPLATE_PATH) {
    $routeProvider.when("/products",{
      templateUrl: BASE_TEMPLATE_PATH + "searchProductView.html",
      controller: "SearchProductCtrl"
    })
    .when("/sales",{
        templateUrl: BASE_TEMPLATE_PATH + "sellingProductView.html",
        controller: "SearchProductCtrl"
    }).when("/",{
        templateUrl: BASE_TEMPLATE_PATH + "loginView.html",
        controller: "LoginCtrl"
    }).when("/list",{
        templateUrl: BASE_TEMPLATE_PATH + "listView.html",
        controller: "SearchPackageCtrl"
    }).when("/categories", {
        templateUrl: BASE_TEMPLATE_PATH + "categories.html",
        controller: "categoriaCtrl"
    }).otherwise({
        redirectTo: '/'
    });

    $httpProvider.interceptors.push('tokenInterceptor');
});