<%-- 
    Document   : InfoProducto
    Created on : 12-oct-2014, 19:04:41
    Author     : nightmare
--%>

<%@page import="direct.market.datatype.DataComentario"%>
<%@page import="java.util.List"%>
<%@page import="direct.market.datatype.DataEspecificacionProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="direct.market.datatype.DataProducto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Informacion de compra</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>

        <%
            DataProducto dProd = (DataProducto) request.getAttribute("datosProd");
            DataEspecificacionProducto dEsp = dProd.getDataEspecificacion();
        %>
        <div class="row">
            <%
                //QUE PASA SI LA LISTA ES VACIA???
                for (byte[] ima : dEsp.getImagenes()) {
            %>
            <div class="col-sm-5 col-sm-push-4 col-xs-3">
                <a href="#" class="thumbnail">
                    <img src="data:image/png;base64,<%=ima%> class="img-responsive">
                </a>
            </div>
            <%}%>
        </div>

        <div class="row">
            <div class="col-sm-5 col-sm-push-4 perfDiv">
                <ul style="margin-top: 1em" class="list-group">
                    <li class="list-group-item listTitle"><strong>Informacion de Producto</strong></li>
                    <li class="list-group-item listItem">Numero de referencia: <%=dProd.getReferencia()%></li>
                    <li class="list-group-item listItem">Nombre: <%=dProd.getNombre()%></li>
                    <li class="list-group-item listItem">Proveedor: <%=dProd.getDataProveedor().getNickname()%></li>
                    <li class="list-group-item listItem">Precio: <%=dProd.getDataEspecificacion().getPrecio()%></li>
                    <li class="list-group-item listItem">Categorias: 
                        <%
                            int tama = dProd.getDataCategorias().size();
                            for(int i = 0; i<tama; i++){
                        %>
                        <%=dProd.getDataCategorias().get(i).getNombre()%>
                        <%if(i+1!=tama){%>, <%}%><%}%>
                    </li>
                </ul>
            </div>
        </div>

        <div class="row">
            <ul style="margin-top: 1em" class="list-group">
                <li class="list-group-item listTitle"><strong>Descripcion</strong></li>
                <li class="list-group-item listItem"><%=dEsp.getDescripcion()%></li>
                <li class="list-group-item listTitle"><strong>Especificacion</strong></li>
                <li class="list-group-item listItem"><%=dEsp.getEspecificacion()%></li>
            </ul>
        </div>

            <div class="row">
                <div class="col-sm-5 col-sm-push-4 perfDiv">
                    <%for( DataComentario dComen : dProd.getListDataComentarios()){%>
                        
                    <%}%>
                </div>
            </div>
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
