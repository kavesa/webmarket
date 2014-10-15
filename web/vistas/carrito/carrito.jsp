<%-- 
    Document   : carrito
    Created on : Sep 23, 2014, 9:40:32 PM
    Author     : mary
--%>

<%@page import="direct.market.datatype.DataLineaOC"%>
<%@page import="direct.market.datatype.DataProducto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html> 

    <head> 

        <title>Carrito</title> 
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
    </head> 
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>
        <%
            List<DataLineaOC> lineas = (ArrayList<DataLineaOC>) sesion.getAttribute("lineasOrden");
        %>
        <div id="carrito"> 
            <form action="<%=request.getContextPath()%>/GenerarOrdenCompraServlet" method="POST">

                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <th> Descripcion</th>
                            <th> Cantidad</th>
                            <th> Precio Unitario</th>
                            <th> Total </th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (lineas !=null) {
                                for (int i = 0; i < lineas.size(); i++) {
                                
                        %>

                        <tr>
                            <td><%=lineas.get(i).getProducto().getDataEspecificacion().getDescripcion()%></td>
                            <td><%=lineas.get(i).getCantidad()%></td>
                            <td><%=lineas.get(i).getProducto().getDataEspecificacion().getPrecio()%></td>
                            <td><%=lineas.get(i).getProducto().getDataEspecificacion().getPrecio() * lineas.get(i).getCantidad()%></td>
                        </tr>
                        <%}}%>
                    </tbody>

                </table>
                <input type="button" id="actualizar" value="Actualizar"/>
                <input type="submit" id="comprar" value="Comprar" />
            </form>
        </div>
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>


