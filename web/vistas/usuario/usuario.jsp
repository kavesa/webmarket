<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Registro Usuario</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>
        <section>
            <div class="container">

                <div class="row col-sm-10" id="us">

                    <%
                        
                        String error = (String) session.getAttribute("error");
                        session.setAttribute("error", null);
                        String success = (String) session.getAttribute("success");
                        session.setAttribute("success", null);
                        

                    %>
                    <%if (error != null) {%>
                        <div class="alert alert-danger"><%=error%></div>
                    <%} else if(success!=null){%>
                        <div class="alert alert-success"><%=success%></div>
                    <%}%>
                    
                    <h3>Alta de Usuario</h3>
                    <form role="form" id="formUsuario" action="<%=request.getContextPath()%>/usuario" method="POST">
                        <div class="form-group">
                            <label for="tipo">Tipo de Usuario</label>
                            <select id="tipo" class="form-control" name="tipo" autofocus>
                                <option value="Cliente">Cliente</option>
                                <option value="Proveedor">Proveedor</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="nickname">Nickname</label>
                            <input type="text" class="form-control" name="nickname" id="nickname" placeholder="Ingrese Nickname" required>
                        </div>
                        <div class="form-group">
                            <label for="pass">Password</label>
                            <input type="password" class="form-control" name="pass" id="pass" placeholder="Ingrese Password" required>
                        </div>
                        <div class="form-group">
                            <label for="repass">Confirme Password</label>
                            <input type="password" class="form-control" name="repass" id="repass" placeholder="Confirme Password" required>
                        </div>
                        <div class="form-group">
                            <label for="nom">Nombre</label>
                            <input type="text" class="form-control" name="nom" id="nom" placeholder="Ingrese Nombre" required>
                        </div>
                        <div class="form-group">
                            <label for="ape">Apellido</label>
                            <input type="text" class="form-control" name="ape" id="ape" placeholder="Ingrese Apellido" required>
                        </div>
                        <div class="form-group">
                            <label for="mail">e-Mail</label>
                            <input type="email" class="form-control" name="mail" id="mail" placeholder="Ingrese e-Mail" required>
                        </div>
                        <div class="form-group">
                            <label for="fec-nac">Fecha Nacimiento</label>
                            <input type="date" class="form-control" name="fec-nac" id="fec-nac">
                        </div>
                        <div class="form-group" id="divNom-comp">
                            <label for="nom-comp">Empresa</label>
                            <input type="text" class="form-control" name="nom-comp" id="nom-comp" placeholder="Ingrese Nombre de la Empresa"required>
                        </div>
                        <div class="form-group" id="divDir-web">
                            <label for="url">Direcci�n Web</label>
                            <input type="url" class="form-control" name="url" id="url" placeholder="Ingrese Direccion Web" required>
                        </div>
                                             
                        <div class="form-group" id="img">
                            <label for="img"Imagen>Imagen</label>
                            <input type="file" class="form-control" name="img" id="img">
                        </div>
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </form>
                </div>
            </div>
            <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
