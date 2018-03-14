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
        ProductService.cancelSale(product.id, sale.id).
            then(res => {
                toastr.success("Venda cancelada com sucesso");
            }).catch(err => {
                toastr.error("Não foi possível cancelar a venda");
            })
    }

    initialLoad();
})