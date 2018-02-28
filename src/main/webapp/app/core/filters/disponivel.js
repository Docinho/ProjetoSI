app.filter("disponivel", function() {
    return function(situation) {
        if (situation) {
            return "Disponível"
        } else {
            return "Em falta"
        }
    }
})