<%-- 
    Document   : ProductNavigation
    Created on : 13/10/2014, 09:07:46 PM
    Author     : JPInspiron
--%>
<%@page import="controller.util"%>
<%@page import="java.util.List"%>
<%@page import="direct.market.datatype.DataEspecificacionProducto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="direct.market.datatype.DataProducto"%>
<%@page import="direct.market.factory.Factory"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Informacion de compra</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
        <% //           request.setAttribute("listaPr", "");%>
                   
        <link rel="stylesheet" href="../../static/jstree/themes/default/style.min.css" />
        <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.min.css" />        
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>
        
        <div class="col-md-3 col-sm-5" style="margin-top: 2em">
            <div class="panel panel-default">
                <div class="panel-heading small">
                    Categorias
                </div>
                <div class="panel-body small">
                   <%@include file="PanelCategorias.jsp" %>          
                </div>

            </div>
        </div>        
        
        <div class="col-md-9 col-sm-9" style="margin-top: 2em">
            <div class="panel panel-default">
                <div class="panel-heading small">
                    Productos
                </div>
                <div class="panel-body small">
                <%@include file="PanelProductos.jsp" %>      
                </div>                
            </div>
        </div>
                
        <div class="col-lg-12">
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
        </div>
    </body>
</html>