app.service("UserService", function($http, BASE_SERVER_URL) {

    this.login = (userParams) => {
        return $http.post("/autenticar", JSON.stringify(userParams));
    }

    this.createNewUser = (userParams) => {
        return $http.post("/user/", JSON.stringify(userParams))
    }

})