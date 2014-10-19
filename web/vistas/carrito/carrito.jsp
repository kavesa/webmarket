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
            String error = (String) session.getAttribute("error");
            session.setAttribute("error", null);
            String success = (String) session.getAttribute("success");
            session.setAttribute("success", null);

        %>
        <%if (error != null) {%>
        <div class="alert alert-danger"><%=error%></div>
        <%} else if (success != null) {%>
        <div class="alert alert-success"><%=success%></div>
        <%}%>
        <div id="carrito" style="margin-top: 2em; margin-left: 4em; margin-right: 4em" class=" panel-info"> 
            <div class=" panel-heading"> Carrito </div>
            <div class=" panel-body">

                <form action="<%=request.getContextPath()%>/GenerarOrdenCompraServlet" method="POST">

                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <th> Nombre</th>
                                <th> Cantidad</th>
                                <th> Precio Unitario</th>
                                <th> Total </th>
                            </tr>
                        </thead>
                        <tbody>

                            <%
                                if (lineas != null && !lineas.isEmpty()) {
                                    for (int i = 0; i < lineas.size(); i++) {

                            %>

                            <tr>
                                <td><%=lineas.get(i).getProducto().getNombre()%></td>
                                <td><%=lineas.get(i).getCantidad()%></td>
                                <td><%=lineas.get(i).getProducto().getDataEspecificacion().getPrecio()%></td>
                                <td><%=lineas.get(i).getProducto().getDataEspecificacion().getPrecio() * lineas.get(i).getCantidad()%></td>
                            </tr>

                            <%}%>

                        </tbody>

                    </table>
                    <input class="btn btn-primary" type="submit" id="comprar" value="Comprar"/>
                    <%} else {%>
                    </tbody>                    
                    </table><%}%>



                </form>
            </div>
        </div>
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>


