app.config(function ($routeProvider) {
    $routeProvider.when("/",{
      templateUrl: "app/core/main/searchProductView.html",
      controller: "SearchProductCtrl"
    }).when("/products",{
        templateUrl: "app/core/main/searchProductView.html",
        controller: "SearchProductCtrl"
    })
    //     .when("/products/create-product",{
    //     templateUrl: "view/createProductView.html",
    //     controller: "CreateProductCtrl"
    // })
    .when("/sales",{
        templateUrl: "app/core/main/sellingProductView.html",
        controller: "SearchProductCtrl"
    }).when("/login",{
        templateUrl: "app/core/main/loginView.html",
        controller: "SearchProductCtrl"
    })
        .otherwise({
        redirectTo: '/'
    });
});
