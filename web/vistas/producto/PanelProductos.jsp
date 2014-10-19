
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
        <link rel="stylesheet" href="../../static/jstree/themes/default/style.min.css" />
        <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.min.css" />        
    </head>
    <body>
              <div id="productos" class="row">
                    <%
//                    String lp = (String) session.getAttribute("listaPr");
//                    if (!lp.equals("")){
                    List<DataProducto> dpList = (List<DataProducto>) session.getAttribute("listaPr");
                    if (dpList != null){
                        int tam = dpList.size();
                    if (tam > 0){
                    for (DataProducto dp : dpList){
                        List<byte[]> imagenesbyte = dp.getDataEspecificacion().getImagenes();
                        String imagen;
                        byte[] imagenbyte = imagenesbyte.get(0);
                        imagen = util.byteImgToBase64(imagenbyte);

                    
                    
//                        HttpSession sesion = request.getSession();
//                        String prueba = (String) session.getAttribute("prueba");
                        
                        %>
                    <div class="col-sm-6 col-md-3">
                        <div class="thumbnail" style="height: 200px; width: 200px;" >
                            <img style="margin-top: 1em" class="img-responsive" src="<%=imagen%>" alt="Miniatura" width="100px" height="100px" >
                        </div>
                        <div class="caption">
                           
                           <h5><%=dp.getNombre()%></h5>
                           <h6><%=dp.getDataEspecificacion().getPrecio()%></h6>
                           <p>
                              <a href="#" class="btn btn-primary" role="button">
                                 Añadir al carrito
                              </a> 
                              <a href="#" class="btn btn-default" role="button">
                                 Detalles
                              </a>
                           </p>
                        </div>
                    </div>
                    <% }}
                    else{%>
                    <div class="col-md-4 small" style="margin-left: 2em; margin-top: 2em; margin-bottom: 2em">
                        <div class="panel panel-info">
                            <div class="panel-heading" >
                                <h5>No existen productos para la categoría seleccionada</h5>
                            </div>
                        </div>
                    </div>    
                    <%}}%>
                </div>
    </body>
</html>