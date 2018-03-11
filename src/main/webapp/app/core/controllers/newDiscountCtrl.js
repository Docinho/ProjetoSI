app.controller("NewDiscountCtrl", function($scope, $uibModalInstance, DiscountsService, categories) {

    $scope.categories = categories;

    $scope.discounts = [{
            message:"Sem Desconto (0%)", value:0
        }, {
            message:"Bom Desconto (10%)", value:10
        }, {
            message:"Ótimo Desconto (25%)", value:25
        }, {
            message:"Super Desconto (50%)", value:50
    }];

    $scope.createDiscount = (category, discount) => {
        console.log(category);
        console.log(discount);
        DiscountsService.createDiscount(category, discount.value)
            .then(res => {
                $uibModalInstance.close(201);
            }).catch(err => {
                console.log(err);
            })
    }
    
    
    $scope.cancel = () => {
        $uibModalInstance.dismiss("cancel");
    }
    
})