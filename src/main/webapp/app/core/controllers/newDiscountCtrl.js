app.controller("NewDiscountCtrl", function($scope, $uibModalInstance) {

    $scope.createDiscount = (newDiscount) => {
        
    }

    $scope.cancel = () => {
        $uibModalInstance.dismiss("cancel");
    }
})