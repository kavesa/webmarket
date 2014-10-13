//cuando la pag esta lista realiza las setea los atributos 
$(document).ready(function() {
    $("#LblNickError").hide();
    $("#SpNick").hide();
    $("#LblMailError").hide();
    $("#LblImgUserError").hide();
    $("#SpMail").hide();
    $("#SpImg").hide();

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
            $("#nom-comp").val("");
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
//peticion request mediante ajax
function getXmlHttpRequestObject()
{
    var xmlHttp = false;
    if (window.XMLHttpRequest)
    {
        return new XMLHttpRequest(); //To support the browsers IE7+, Firefox, Chrome, Opera, Safari
    }
    else if (window.ActiveXObject)
    {
        return new ActiveXObject("Microsoft.XMLHTTP"); // For the browsers IE6, IE5 
    }
    else
    {
        alert("Error due to old verion of browser upgrade your browser");
    }
}

var xmlhttp = new getXmlHttpRequestObject(); //xmlhttp holds the ajax object
//funcion en ajax para cheq dis de usuario
function existeUser() {
    if (xmlhttp) {
        var valor = document.getElementById("nickname");
        //var valor1=document.getElementById("mail");
        xmlhttp.open("POST", "cheqUserAjax", true);
        xmlhttp.onreadystatechange = handleServletPost;
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp.send("op=" + valor.name + "&usermail=" + valor.value);

    }
}
//funcion en ajax para cheq dis de mail
function existeMail() {
    if (xmlhttp) {
        var valor = document.getElementById("mail");
        xmlhttp.open("POST", "cheqUserAjax", true);
        xmlhttp.onreadystatechange = handleServletPost;
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp.send("op=" + valor.name + "&usermail=" + valor.value);

    }
}
//verifica si la peticion al server esta lista y si es correcta
function handleServletPost() {
    if (xmlhttp.readyState == 4) {
        if (xmlhttp.status == 200) {
            ;
            var resp = xmlhttp.responseText;
            var res = resp.split("+", 1);
            if (res == "nickname") {
                if (resp == "nickname+true") {
                    $("#divNick").prop("class", "form-group has-error has-feedback");
                    $("#LblNickError").show();
                    $("#SpNick").show();
                    $("#nickname").focus();
                    $("#btnGuardar").prop("disabled", true);
                } else {
                    $("#divNick").prop("class", "form-group");
                    $("#LblNickError").hide();
                    $("#SpNick").hide();
                    $("#btnGuardar").prop("disabled", false);
                }
            } else if (res == "mail") {
                if (resp == "mail+true") {
                    $("#divMail").prop("class", "form-group has-error has-feedback");
                    $("#LblMailError").show();
                    $("#SpMail").show();
                    $("#mail").focus();
                    $("#btnGuardar").prop("disabled", true);
                } else {
                    $("#divMail").prop("class", "form-group");
                    $("#LblMailError").hide();
                    $("#SpMail").hide();
                    $("#btnGuardar").prop("disabled", false);
                }
            }
        }
        else {
            alert("Ajax calling error");
        }
    }
}
//cheq q los arch a sen jpg jpeg png
function checkFile() {
    str = document.getElementById('imgUser').value;
    if (str != "") {
        str = str.toUpperCase();
        str = str.split(".", 2);
        str = str[1];
        tipo = "JPG";
        tipo1 = "JPEG";
        tipo2 = "PNG";
        if ((str != tipo) && (str != tipo1) && (str != tipo2)) {
            $("#divImgUser").prop("class", "form-group has-error has-feedback");
            $("#LblImgUserError").show();
            $("#SpImg").show();
            $("#imgUser").focus();
            $("#btnGuardar").prop("disabled", true);
        } else {
            $("#divImgUser").prop("class", "form-group");
            $("#LblImgUserError").hide();
            $("#SpImg").hide();
            $("#btnGuardar").prop("disabled", false);
        }
    }
}