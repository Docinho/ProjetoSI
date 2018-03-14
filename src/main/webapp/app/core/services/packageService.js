app.service("PackageService", function($http, BASE_SERVER_URL) {

    this.getAllPackages = () => {
        return $http.get("/api/pack/");
    }

    this.createPackage = (id, pack) => {
        return $http.post("/api/product/" + id + "/pack/", JSON.stringify(pack))
    }
});