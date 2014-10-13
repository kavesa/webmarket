<%-- 
    Document   : Perfil
    Created on : 21-sep-2014, 17:48:04
    Author     : nightmare
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="direct.market.datatype.DataProducto"%>
<%@page import="java.util.List"%>
<%@page import="direct.market.datatype.DataUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Perfil</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>

        <%
            DataUsuario usu = (DataUsuario) request.getAttribute("usuario");
            List<DataProducto> dpList = (List<DataProducto>) request.getAttribute("listaPr");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        %>
        <div class="row">
            <!--IMG PERFIL -->
            <div class="col-sm-3 col-sm-push-4 perfDiv">
                <img style="margin-top: 1em" class="img-responsive imgMarco" src="data:image/png;base64,<%=usu.getImagen()%>" alt="Imagen de Perfil" width="350px">
                <ul style="margin-top: 1em" class="list-group">                    
                    <li class="list-group-item listTitle"><strong>Informacion Personal</strong></li>
                    <li class="list-group-item listItem">Nickname: <%= usu.getNickname()%></li>
                    <li class="list-group-item listItem">Nombre: <%= usu.getNombre()%></li>
                    <li class="list-group-item listItem">Apellido: <%= usu.getApellido()%></li>
                    <li class="list-group-item listItem">Fecha de nacimiento: <%= sdf.format(usu.getFechaNacimiento())%></li>
                    <li class="list-group-item listItem">eMail: <%= usu.getEmail()%></li>
                    <li class="list-group-item listItem">Tipo de usuario: <%= usu.getTipoUsu()%></li>
                        <% if (usu.getTipoUsu().equalsIgnoreCase("Proveedor")) {%>
                    <li class="list-group-item listItem">Empresa: <%= usu.getCompania()%></li>
                    <li class="list-group-item listItem">Direccion web: <%= usu.getWebLink()%></li>
                        <% }%>
                </ul>
            </div>
            <!--IMG PERFIL -->
            <div class="col-sm-2 col-sm-push-4 perfDiv">
                <%
                    int lc;
                    if (usu.getTipoUsu().equalsIgnoreCase("Proveedor")) {
                %>
                <ul style="margin-top: 1em" class="list-group">
                    <li class="list-group-item listTitle"><strong>Productos</strong></li>
                        <%
                            lc = dpList.size();
                            for (int i = 0; i < lc; i++) {
                        %>
                    <li class="list-group-item listItem"><%= dpList.get(i).getNombre()%></li>
                        <%}%>
                </ul>
                <% } else {%>
                <ul style="margin-top: 1em" class="list-group">
                    <li class="list-group-item listTitle"><strong>Compras</strong></li>
                        <%
                            lc = usu.getListaCompras().size();
                            int Compra;
                            for (int i = 0; i < lc; i++) {
                                Compra = usu.getListaCompras().get(i).getNumero();
                        %>
                    <li class="list-group-item listItem"><a href="InfoCompra?nocid=<%=Compra%>"><%=Compra%></a></li>
    
                        <%}%>
                </ul>
                <%}%>
            </div>
        </div>

        <div class="bottom">
            <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
        </div>
    </body>
</html>
