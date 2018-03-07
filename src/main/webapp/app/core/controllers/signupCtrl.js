app.controller("SignUpCtrl", function($scope, $uibModalInstance, toastr, UserService) {

    $scope.createNewUser = (userParams) => {
        UserService.createNewUser(userParams)
            .then(res => {
                $uibModalInstance.close(201);
            }).catch(err => {
                toastr.error("Ocorreu um erro ao criar este usuário, tente novamente!");
            })
    }

    // $scope.loginUser = (userParams) => {
    //     UserService.loginUser(userParams)
    //         .then(res => {
    //             $uibModalInstance.close(201);
    //             $scope.token = response.data;
    //         }).catch(err => {
    //             console.log(err);
    //         });
    // }

    $scope.cancel = () => {
        $uibModalInstance.dismiss('cancel');
    }
});