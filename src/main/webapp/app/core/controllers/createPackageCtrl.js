app.controller("CreatePackageCtrl", function ($scope, $uibModalInstance, toastr, product, PackageService) {

    $scope.product = product;
    $scope.dateformat = 'dd/MM/yyyy';
    $scope.datePicker = {
        opened : false
    };

    $scope.expirationDate = new Date();
    $scope.itemNumber = 0;

    $scope.dateOptions = {
        formatYear: 'yy',
        minDate: new Date(),
        startingDay: 1
    };

    $scope.submit = (itemNumber,expirationDate) => {
        
        var package = {
            expirationDate: expirationDate.getDate() + "/" + (expirationDate.getMonth() + 1) + "/" + expirationDate.getFullYear(),
            itemNumber: itemNumber
        }

        console.log(package.expirationDate);

        PackageService.createPackage($scope.product.id, package)
            .then(function success(response) {
                if (response.status === 201) {
                    toastr.success("Lote criado com sucesso!");
                    $uibModalInstance.close(201);
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
