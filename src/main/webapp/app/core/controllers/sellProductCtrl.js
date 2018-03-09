app.controller("SellProductCtrl", function($scope, $uibModalInstance, ProductService, toastr) {

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

    $scope.sellProduct = (product, quantity) => {
        ProductService.sellProducts(product.id, quantity)
            .then(res => {
                if (res.status === 201) {
                    toastr.success(quantity + "unidade(s) de " + product.productName + " vendidos.")
                    $uibModalInstance.close(201);
                }
            }).catch(err => {
                toastr.error("Ocorreu um erro")
                console.log(err);
            });
    }

    $scope.cancel = () => {
        $uibModalInstance.dismiss('cancel');
    }

})