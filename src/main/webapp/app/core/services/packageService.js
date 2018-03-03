app.service("PackageService", function($http, BASE_SERVER_URL) {

    this.getAllPackages = () => {
        return $http.get("/api/pack/");
    }

    this.createPackage = (produto, lote) => {
        let id = produto.id;
        return $http.post("/api/product/" + id + "/lote/", JSON.stringify(lote))
    }
});