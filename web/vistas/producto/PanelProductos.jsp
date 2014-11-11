<%@page import="controller.util"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>
    <body>
        <div id="productos" class="row">
            <%
                List<controller.WScategoria.DataProducto> dpList = (List<controller.WScategoria.DataProducto>) session.getAttribute("listaPr");
                if (dpList != null) {
                    int tam = dpList.size();
                    if (tam > 0) {
                        for (controller.WScategoria.DataProducto dp : dpList) {
                            List<byte[]> imagenesbyte = dp.getDataEspecificacion().getImagenes();
                            String imagen;
                            byte[] imagenbyte = imagenesbyte.get(0);
                            imagen = util.byteImgToBase64(imagenbyte);
                            
            %>

            <div class="col-sm-5 col-md-2" style="margin: 1em">
                <div class="thumbnail prod-panel-thumb" style="height: 170px; width: 170px;" >
                    <a href="InfoProducto?nocid=<%=dp.getReferencia()%>">
                        <img style="margin-top: 1em" class="img-responsive" src="<%=imagen%>" alt="Miniatura" width="100px" height="100px" >
                    </a>
                </div>
                <div class="caption">

                    <h5> <strong><%=dp.getNombre()%></strong></h5>
                    <h6><strong>Precio: <%=dp.getDataEspecificacion().getPrecio()%></strong></h6>
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
    </body>
</html>