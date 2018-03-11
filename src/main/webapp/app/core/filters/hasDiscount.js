app.filter("hasDiscount", function(){
    return function(array) {
        return array.filter(hasDiscount());
    }
})

function hasDiscount() {
    return function(object) {
        return object.discount != 0;
    }
}