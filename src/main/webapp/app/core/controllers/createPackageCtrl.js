app.controller("CreatePackageCtrl", function ($scope, $uibModalInstance, toastr, produto, PackageService) {

    $scope.produto = produto;
    $scope.dateformat = 'dd/MM/yyyy';
    $scope.datePicker = {
        opened : false
    };

    $scope.dataDeValidade = new Date();
    $scope.numeroDeItens = 0;

    $scope.dateOptions = {
        formatYear: 'yy',
        minDate: new Date(),
        startingDay: 1
    };

    $scope.submit = (numeroDeItens,dataDeValidade) => {
        
        var lote = {
            dataDeValidade: dataDeValidade.getDay() + "/" + (dataDeValidade.getMonth() + 1) + dataDeValidade.getFullYear(),
            numeroDeItens: numeroDeItens
        }

        PackageService.createPackage(produto, lote)
            .then(function success(response) {
                console.log(response)
                if (response.status === 201) {
                    console.log("Lote criado com sucesso!");
                    toastr.success("Lote criado com sucesso!");
                    $uibModalInstance.close({
                        status: 201
                    });
                }
            }, function error(error) {
                console.log(error);
                toastr.error("Problemas ao tentar adicionar produto.");
            });
    };
    
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.openDatePicker = function () {
        $scope.datePicker.opened = true;
    }
});
