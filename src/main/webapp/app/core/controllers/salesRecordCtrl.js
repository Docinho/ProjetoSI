app.controller("SalesRecordCtrl", function($scope, toastr, ProductService) {

    var initialLoad = () => {
        $scope.productsList = [];
        $scope.produtos = [];
        loadProductsList();
    }

    var loadProductsList = () => {
        ProductService.getAllProducts()
            .then(response => {
                $scope.productsList = response.data;
                console.log($scope.productsList);
            }).catch(error => {
                console.log(error);
            });
    };

    


    initialLoad();
})