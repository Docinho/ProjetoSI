app.filter("filterByID", function() {
    return function filterByID(array, codBarras) {
        return array.filter(filterID(codBarras));
    }
});

function filterID(cod) {
    return function(obj) {
        if (cod) {
            return obj.codigoBarra.indexOf(cod) > -1;
        }
        return true;
    }
}