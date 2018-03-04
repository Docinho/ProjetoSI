app.config(function ($routeProvider, BASE_TEMPLATE_PATH) {
    $routeProvider.when("/",{
      templateUrl: BASE_TEMPLATE_PATH + "searchProductView.html",
      controller: "SearchProductCtrl"
    })
    .when("/sales",{
        templateUrl: BASE_TEMPLATE_PATH + "sellingProductView.html",
        controller: "SearchProductCtrl"
    }).when("/sign",{
        templateUrl: BASE_TEMPLATE_PATH + "loginView.html",
        controller: "LoginCtrl"
    }).when("/list",{
        templateUrl: BASE_TEMPLATE_PATH + "listView.html",
        controller: "SearchPackageCtrl"
     }).when("/relatorio",{
        templateUrl: BASE_TEMPLATE_PATH + "relatorioGeral.html",
        controller: "estoqueController"
    }).otherwise({
        redirectTo: '/'
    });
});