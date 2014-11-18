<%-- 
    Document   : InfoCompra
    Created on : 05/10/2014, 06:18:43 PM
    Author     : K
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Inicio de Sesion</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="/WEB-INF/jspf/jscss.jspf" %>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/top.jspf" %>

        <div class="col-sm-9 col-centered">
            <div class="row">
                <form role="form" id="formLogin" action="<%=request.getContextPath()%>/login2" method="POST">
                    <div class="form-group">
                        <label for="nickLogin">Nickname</label>
                        <input type="text" class="form-control" name="nickLogin" id="nickLogin" placeholder="Ingrese Nickname" autofocus required>
                    </div>
                    <div class="form-group">
                        <label for="passLogin">Password</label>
                        <input type="password" class="form-control" name="passLogin" id="passLogin" placeholder="Ingrese Password" required>
                    </div>

                    <div class="modal-footer">
                        <button type="button" id="cancel"class="btn btn-default " data-dismiss="modal">Cancelar</button>
                        <button type="submit" id="aceptar" class="btn btn-success ">Aceptar</button>
                    </div>
                </form>
            </div>
        </div>
        <%@include file="/WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
