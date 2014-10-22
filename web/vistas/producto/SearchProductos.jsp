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
        <title>Direct Market</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">                   
        <link rel="stylesheet" href="../../static/jstree/themes/default/style.min.css" />
        <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.min.css" />        
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>
        <div id="productosEncontrados" class="row">
            <div class="panel panel-default">
                <div class="panel-heading small">Resultados de Búsqueda </div>
                <div class="panel-body small">
                    <%
                        List<DataProducto> resultList = (List<DataProducto>) request.getAttribute("result_search");

                        if (resultList != null) {
                            int tam = resultList.size();
                            if (tam > 0) {
                                for (DataProducto dpsearch : resultList) {
                                    List<byte[]> imagenesbyte = dpsearch.getDataEspecificacion().getImagenes();
                                    String imagen;
                                    byte[] imagenbyte = imagenesbyte.get(0);
                                    imagen = util.byteImgToBase64(imagenbyte);
                    %>
                    <div class="col-sm-5 col-md-2" style="margin: 1em">
                        <div class="thumbnail prod-panel-thumb" style="height: 170px; width: 170px;" >
                            <a href="InfoProducto?nocid=<%=dpsearch.getReferencia()%>">
                                <img style="margin-top: 1em" class="img-responsive" src="<%=imagen%>" alt="Miniatura" width="100px" height="100px" >
                            </a>
                        </div>
                        <div class="caption">

                            <h5> <strong><%=dpsearch.getNombre()%></strong></h5>
                            <h6><strong>Precio: <%=dpsearch.getDataEspecificacion().getPrecio()%></strong></h6>
                        </div>
                    </div>
                    <% }
                } else {%>
                    <div class="col-md-4 small" style="margin-left: 2em; margin-top: 2em; margin-bottom: 2em">
                        <div class="panel panel-info">
                            <div class="panel-heading" >
                                <h5>No existen productos para la categoría seleccionada</h5>
                            </div>
                        </div>
                    </div>    
                    <%}
                    }%>
                </div>
            </div>
        </div>
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>