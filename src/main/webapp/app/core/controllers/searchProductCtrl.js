app.controller("SearchProductCtrl", function ($scope, $uibModal, ProductService, toastr, BASE_TEMPLATE_PATH) {

    // $scope.title = "Search Product";

    var initialLoad = () => {
        $scope.productsList = [];
        $scope.produtos = [];
        loadProductsList();
    }

    var loadProductsList = () => {
        ProductService.getAllProducts()
            .then(response => {
                $scope.productsList = response.data;
            }).catch(error => {
                console.log(error);
            });
    };

    $scope.openCreateProductDialog = () => {
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Adicionar Produto',
            ariaDescribedBy: 'Formulario para adição de um novo produto',
            templateUrl: BASE_TEMPLATE_PATH + 'createProductView.html',
            controller: 'CreateProductCtrl',
            controllerAs: 'cpCtrl'
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

    $scope.openAtribuirPrecoParaProdutoDialog = function(product) {
        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Atribuir preço ao Produto',
            ariaDescribedBy: 'Formulario para Atribuir preço ao Produto',
            templateUrl: BASE_TEMPLATE_PATH + 'updateProductPriceView.html',
            controller: 'UpdateProductPriceCtrl',
            resolve: {
                produto: function () {
                    return angular.copy(product);
                }
            }
        });

        modalInstance.result.then(result => {
            console.log(result)
            if (result.status === 200) {
                loadProductsList();
            }
        });
    };

    $scope.openCriarLoteDialog = (product) => {

        var modalInstance = $uibModal.open({
            ariaLabelledBy: 'Criar lote',
            ariaDescribedBy: 'Formulario para criar lote',
            templateUrl: BASE_TEMPLATE_PATH + 'createPackageView.html',
            controller: 'CreatePackageCtrl',
            resolve: {
                produto: function () {
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

    // $scope.createLot = function(produto) {
    //     console.log(produto)
    // };
    //
    // $scope.atribuirPrice = function(product) {
    //     console.log(product)
    // };

    initialLoad();
    initialLoad();
});