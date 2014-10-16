<%-- 
    Document   : InfoProducto
    Created on : 12-oct-2014, 19:04:41
    Author     : nightmare
--%>

<%@page import="direct.market.datatype.DataCategoria"%>
<%@page import="controller.util"%>
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
            List<DataCategoria> catList = (List<DataCategoria>) request.getAttribute("pCat");
            DataEspecificacionProducto dEsp = dProd.getDataEspecificacion();

            String error = (String) session.getAttribute("error");
            session.setAttribute("error", null);
            String success = (String) session.getAttribute("success");
            session.setAttribute("success", null);
        %>
        <div style="margin-bottom: 1em" class="col-sm-5 col-centered perfDiv">
            <%if (error != null) {%>
            <div class="alert alert-danger"><%=error%></div>
            <%} else if (success != null) {%>
            <div class="alert alert-success"><%=success%></div>
            <%}%>
            <div style="margin-top: 1em" class="row">

                <div style="margin-top: 1em" class="row">
                    <div class="col-sm-12 col-centered">                        
                        <form action="<%= request.getContextPath()%>/AgregarItemCarritoServlet" method="post">
                            <input style="float: right; margin-right: 1em"type="submit" name="add" value="Agregar al carro"/>
                        </form>
                    </div>
                </div>

                <div class="col-sm-12 col-centered">
                    <%
                        for (byte[] ima : dEsp.getImagenes()) {
                    %>
                    <img style="margin-bottom: 1em; margin-top: 1em" class="img-responsive imgMarco" src="<%=util.byteImgToBase64(ima)%>" alt="Imagen de Producto" width="365px">
                    <%}%>
                </div>
            </div>

            <div class="row">
                <div style="margin-top: 1em" class="col-sm-12 col-centered">
                    <ul class="list-group">
                        <li style="text-align: center" class="list-group-item listTitle"><strong>Informacion de Producto</strong></li>
                        <li class="list-group-item listItem">Numero de referencia: <%=dProd.getReferencia()%></li>
                        <li class="list-group-item listItem">Nombre: <%=dProd.getNombre()%></li>
                        <li class="list-group-item listItem">Proveedor: <%=dProd.getDataProveedor().getNickname()%></li>
                        <li class="list-group-item listItem">Precio: <%=dProd.getDataEspecificacion().getPrecio()%></li>
                        <li class="list-group-item listItem">Categorias: 
                            <%
                                int tama = catList.size();
                                for (int i = 0; i < tama; i++) {
                            %>
                            <%=catList.get(i).getNombre()%>
                            <%if (i + 1 != tama) {%>, <%}%><%}%>
                        </li>

                        <li class="list-group-item listItem">Descripcion: <%=dEsp.getDescripcion()%></li>
                    </ul>
                </div>
            </div>

            <div class="row">
                <div style="margin-top: 1em" class="col-sm-12 col-centered">
                    <ul class="list-group">
                        <li style="text-align: center"class="list-group-item listTitle"><strong>Especificacion</strong></li>
                        <li class="list-group-item listItem"><%=dEsp.getEspecificacion()%></li>
                    </ul>
                </div>
            </div>



            <div class="row">
                <form role="form" id="formComentario" action="<%=request.getContextPath()%>/comentario" method="POST">
                    <input name="numRefProd" type="text" value="<%=dProd.getReferencia()%>" hidden="true"/>
                    <div class="form-group">
                        <label for="idComentario">Padre</label>
                        <input type="number" class="form-control" name="idCom" id="idCom" placeholder="Ingrese id del comentario a responder (vacio es un comentario nuevo).">
                    </div>
                    <div class="form-group">
                        <label for="Comentario">Comentario</label>
                        <textarea class="form-control" name="com" id="com" placeholder="Ingrese comentario."></textarea>
                    </div>
                    <button type="submit" id="btnGuardar" class="btn btn-success">Ingresar Comentario</button>
                </form>

            </div>

        </div>
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
