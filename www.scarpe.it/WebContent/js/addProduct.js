$(document).ready(function () {
    $("#btnInserisci").bind("click", function () {
        var imgVal = $('[type=file]').val();
        if (imgVal == '') {
            alert("carica tutte le immagini");
            return false;
        }
    });
});