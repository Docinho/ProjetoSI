app.controller("SearchPackageCtrl", function ($scope, ProductService, BASE_TEMPLATE_PATH) {

    var initialLoad = () => {
        $scope.productList = [];
        loadProductList();
    }

    $scope.order = (field) => {
        $scope.field = field;
    }

    var loadProductList = () => {
        ProductService.getAllProducts()
            .then(response => {
                $scope.productList = response.data;
                $scope.productListShow = $scope.productList.map(x => false);
            }).catch(error => {
                console.log(error);
            });
    };

    $scope.message = (alert) => {
        if (alert == 1) {
            return "Restam poucos produtos!";
        } else if (alert == 2) {
            return "Existem lotes próximos da data de vencimento.";
        } else {
            return "Restam poucos produtos e existem lotes próximos da data de vencimento";
        }
    }

    $scope.selectProduct = function(product) {
        console.log(product);
        if ($scope.selectedProduct == undefined) {
            $scope.selectedProduct = product
        } else if ($scope.selectedProduct.id == product.id) {
                $scope.selectedProduct = undefined
        } else {
            $scope.selectedProduct = product;
        }
    };

    $scope.openPackAbout = (product) => {
        console.log($scope.productListShow);
        $scope.productListShow[product.id -1] = !$scope.productListShow[product.id -1];
        console.log($scope.productListShow);
    }

    $scope.shouldShow = (product) => {
        if ($scope.productListShow == undefined || $scope.productListShow == null || product == undefined)
            return false;
        console.log($scope.productListShow[product.id-1])
        return $scope.productListShow[product.id-1];
    }

    initialLoad();
    initialLoad();
});