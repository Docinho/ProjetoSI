app.service("UserService", function($http, BASE_SERVER_URL) {

    this.login = (userParams) => {
        console.log("oi");
        return $http.put("/api/user/", JSON.stringify(userParams));
    }

    this.createNewUser = (userParams) => {
        return $http.post("/api/user/", JSON.stringify(userParams))
    }

})