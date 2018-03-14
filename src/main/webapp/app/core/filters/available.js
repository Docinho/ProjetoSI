app.filter("availableFilter", function() {
    return function(situation) {
        if (situation == 1) {
            return "Disponível"
        } else {
            return "Em falta"
        }
    }
})