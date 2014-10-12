$(document).ready(function() {

    $("#divNom-comp").hide();
    $("#divDir-web").hide();
    $("#nom-comp").prop("required", false);
    $("#url").prop("required", false);

    $("#tipo").change(function() {
        if ($(this).val() == "Cliente") {
            $("#divNom-comp").hide();
            $("#divDir-web").hide();
            $("#nom-comp").prop("required", false);
            $("#url").prop("required", false);
            $("#nom-comp").val("");
            $("#url").val("");
        } else {
            $("#divNom-comp").show();
            $("#divDir-web").show();
            $("#nom-comp").prop("required", true);
            $("#url").prop("required", true);
            $("#nom-comp").val("").
                    $("#url").val("");
        }
    });
    $("#formUsuario").submit(function(event) {
        if ($("#pass").val() != $("#repass").val()) {
            $("#pass").focus();
            window.alert("Las contrase\u00F1as no coinciden.");
            event.preventDefault();
        }
        
    });


});



