<%-- 
    Document   : InfoCompra
    Created on : 05/10/2014, 06:18:43 PM
    Author     : nightmare
--%>

<%@page import="direct.market.datatype.DataLineaOC"%>
<%@page import="direct.market.datatype.DataOC"%>
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
        
        <% DataOC doc = (DataOC)request.getAttribute("datosComp"); %>
        
        <div class="row">
            <div class="col-sm-5 col-sm-push-4 perfDiv">
                <table style="margin-top: 1em" class="table">
        <thead class="listTitle">
            <tr><th colspan="4" class="text-center">Orden de compra numero: <%=doc.getNumero()%></th></tr>
            <tr>
                <th>Nombre</th>
                <th>Precio Unitario</th>
                <th>Cantidad</th>
                <th>Sub total</th>
            </tr>
        </thead>
        <tbody>
            <% String prodRef;
            for (DataLineaOC dlo : doc.getLineas()) {%>
            <tr class="listItem">
                <td><a href="InfoProducto?nocid=<%=dlo.getProducto().getReferencia()%>"><%=dlo.getProducto().getNombre()%></a></td>
                <td><%=dlo.getProducto().getDataEspecificacion().getPrecio()%></td>
                <td><%=dlo.getCantidad()%></td>
                <td><%=dlo.getTotalLinea()%></td>
            </tr>
            <%}%>
            <tr class="listTitle">
                <td></td>
                <td></td>
                <td>Total:</td>
                <td><%=Double.valueOf(doc.getPrecio_total()).toString()%></td>
            </tr>
        </tbody>
    </table>
            </div>
            </div>
            <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
