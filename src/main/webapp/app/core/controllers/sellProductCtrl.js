app.controller("SellProductCtrl", function($scope, $uibModalInstance, PackageService, toastr, product) {

    // TODO BACKEND

    // $scope.submit = (quantidade) => {
    //     PackageService.sellProducts(product, quantidade)
    //         .then(res => {
    //             if (res.status === 201) {
    //                 toastr.success(quantidade + "unidade(s) de " + product.nome + " vendidos.")
    //                 $uibModalInstance.close(201);
    //             }
    //         }).catch(err => {
    //             toastr.error("Ocorreu um erro")
    //             console.log(err);
    //         });
    // }

    $scope.cancel = () => {
        $uibModalInstance.dismiss('cancel');
    }

})