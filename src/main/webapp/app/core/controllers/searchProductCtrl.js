app.controller("SearchProductCtrl", function ($scope, $http, $uibModal, ProductService, toastr, BASE_TEMPLATE_PATH) {

    // $scope.title = "Search Product";

    $scope.order = (field) => {
      $scope.field = field;
    }

    var initialLoad = () => {
        $scope.productsList = [];
        $scope.produtos = [];
        loadProductsList();
    }

    var loadProductsList = () => {
        token = localStorage.getItem("userToken");
        $http.defaults.headers.common.Authorization = 'Bearer ' + token;
        ProductService.getAllProducts()
            .then(response => {
                $scope.productsList = response.data;
                console.log($scope.productsList);
            }).catch(error => {
                console.log(error);
            });
    };

    $scope.openCreateProductDialog = () => {
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Adicionar Produto',
            ariaDescribedBy: 'Formulario para adição de um novo produto',
            templateUrl: BASE_TEMPLATE_PATH + 'createProductView.html',
            controller: 'CreateProductCtrl'
        });

        modalInstance.result.then(result => {
            if (result === 201) {
                loadProductsList();
            }
        }).catch(err => {
            if (err != "cancel" && err != "backdrop click") {
                toastr.error(err);
            }
        });
    };

    $scope.openUpdateProductDialog = function(product) {
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Atribuir preço ao Produto',
            ariaDescribedBy: 'Formulario para Atribuir preço ao Produto',
            templateUrl: BASE_TEMPLATE_PATH + 'updateProductView.html',
            controller: 'UpdateProductCtrl',
            resolve: {
                product: function () {
                    return angular.copy(product);
                }
            }
        });

        modalInstance.result.then(result => {
            if (result.status === 201) {
                loadProductsList();
            }
        });
    };

    $scope.openSellProductDialog = function(product) {
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Vender Produto',
            ariaDescribedBy: 'Formulario para Vender Produto',
            templateUrl: BASE_TEMPLATE_PATH + 'sellProductView.html',
            controller: 'SellProductCtrl',
            resolve: {
                product: function () {
                    return angular.copy(product);
                }
            }
        });

        modalInstance.result.then(result => {
            console.log(result)
            if (result.status === 201) {
                loadProductsList();
            }
        });
    };

    $scope.openCreatePackDialog = (product) => {

        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Criar lote',
            ariaDescribedBy: 'Formulario para criar lote',
            templateUrl: BASE_TEMPLATE_PATH + 'createPackageView.html',
            controller: 'CreatePackageCtrl',
            resolve: {
                product: function () {
                    return angular.copy(product);
                }
            }
        });

        modalInstance.result.then(res => {
            if (res === 201) {
                loadProductsList();
            }
        });
    };

    initialLoad();
    initialLoad();
});