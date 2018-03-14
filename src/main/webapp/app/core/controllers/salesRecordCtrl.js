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

    $scope.cancelSale = (product, sale) => {
        ProductService.cancelSale(product.id, sale)
            .then(res => {
                toastr.success("Venda cancelada com sucesso");
                loadProductsList();
            }).catch(err => {
                toastr.error("Não foi possível cancelar a venda");
            })
    }

    initialLoad();
})