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
        <h2 class="perfil-title">Mi Perfil</h2>

        <%
            DataUsuario usu = (DataUsuario) request.getAttribute("usuario");
            List<DataProducto> dpList = (List<DataProducto>) request.getAttribute("listaPr");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String imgBase64 = (String) request.getAttribute("img64");
        %>
        <div class="row">
            <!--IMG PERFIL -->
            <div class="col-sm-4 col-centered col-perfil">
                <div class="col-sm-8 perfDiv">
                    <span style="display:block;text-align:center;" >
                        <img style="margin-top: 1em" class="img-responsive imgMarco" src="<%=imgBase64%>" alt="Imagen de Perfil">
                    </span>
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
                            <% } else {%>
                        <li class="list-group-item listItem">Notificaciones: <%= usu.isMailing()?"SI":"NO"%></li>
                            <% }%>
                    </ul>
                </div>
                <!--IMG PERFIL -->

                <%
                    int lc;
                    if (usu.getTipoUsu().equalsIgnoreCase("Proveedor") && dpList.size() > 0) {
                %>
                <div class="col-sm-4 perfDiv">
                    <ul style="margin-top: 1em" class="list-group">
                        <li class="list-group-item listTitle"><strong>Productos</strong></li>
                            <%
                                lc = dpList.size();
                                for (int i = 0; i < lc; i++) {
                            %>
                        <li class="list-group-item listItem"><a href="InfoProducto?nocid=<%=dpList.get(i).getReferencia()%>"><%= dpList.get(i).getNombre()%></a></li>
                            <%}%>
                    </ul>
                </div>
                <% } else if (usu.getTipoUsu().equalsIgnoreCase("Cliente") && usu.getListaCompras().size() > 0) {%>
                <div class="col-sm-4 perfDiv">
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
                </div>
                <%}%>

            </div>
        </div>

        <div class="bottom">
            <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
        </div>
    </body>
</html>
