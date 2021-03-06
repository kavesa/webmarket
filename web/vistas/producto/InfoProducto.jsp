<%-- 
    Document   : InfoProducto
    Created on : 12-oct-2014, 19:04:41
    Author     : nightmare
--%>

<%@page import="controller.WSproducto.DataEspecificacionProducto"%>
<%@page import="controller.WSproducto.DataProducto"%>
<%@page import="controller.WScategoria.DataCategoria"%>
<%@page import="controller.util"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <%
            DataProducto dProd = (DataProducto) request.getAttribute("datosProd");
            List<DataCategoria> catList = (List<DataCategoria>) request.getAttribute("pCat");
            DataEspecificacionProducto dEsp = dProd.getDataEspecificacion();

            String error = (String) session.getAttribute("error");
            session.setAttribute("error", null);
            String success = (String) session.getAttribute("success");
            session.setAttribute("success", null);
            session.setAttribute("ref", dProd.getReferencia());
        %>
        <title><%=dProd.getNombre()%> | Informacion de Producto</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="../../static/jstree/themes/default/style.css" />
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
        <link href="../../static/rating/css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />
        <script src="../../static/rating/js/star-rating.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>

        <div style="margin-bottom: 1em" class="col-sm-11 col-centered perfDiv">
            <%if (error != null) {%>
            <div class="alert alert-danger"><%=error%></div>
            <%} else if (success != null) {%>
            <div class="alert alert-success"><%=success%></div>
            <%}%>
            <div style="margin-top: 1em" class="row">

                <h3 class="prod-title"><%=dProd.getNombre()%></h3>

                <div style="margin-top: 1em" class="row col-sm-5 col-centered">
                    <%if (request.getAttribute("tipoU").equals("y")) {%>
                    <div class="col-sm-11 col-centered">

                        <form id="formCar" action="<%= request.getContextPath()%>/addToShoppingCart?nocid=<%=dProd.getReferencia()%>" method="post">
                            <input class="btn-xs btn-primary" style="float: right; margin-right: 1em"type="submit" name="add" value="Agregar al carro"/>
                            <input style="float: right; margin-right: 1em; width: 50px" type="number" id="cant" name="cant"/>

                        </form>
                    </div>
                    <%}%>
                </div>

                <style type="text/css">
                    .item {
                        background: #fff;
                    }
                </style>

                <div class="col-sm-5 col-centered">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <%Integer cont = 0;
                                for (byte[] ima : dEsp.getImagenes()) {
                                    if (cont == 0) {%>
                            <li data-target="#carousel-example-generic" data-slide-to="<%=cont.toString()%>" class="active"></li>
                                <%} else {%>
                            <li data-target="#carousel-example-generic" data-slide-to="<%=cont.toString()%>"></li>
                                <%}
                                        cont++;
                                    }%>
                        </ol>

                        <!-- Wrapper for slides -->
                        <% cont = 0;
                            for (byte[] ima : dEsp.getImagenes()) {
                                if (cont == 0) {%>
                        <div class="carousel-inner">
                            <div class="item active">
                                <img style="max-width:100%;max-height:100%;float: none; margin: auto"src="<%=util.byteImgToBase64(ima)%>" alt="Imagen de Producto">
                                <!--                                <div class="carousel-caption">
                                                                    ...
                                                                </div>-->
                            </div>
                            <%} else {%>
                            <div class="item">
                                <img style="max-width:100%;max-height:100%;float: none; margin: auto"src="<%=util.byteImgToBase64(ima)%>" alt="Imagen de Producto">
                                <!--                                <div class="carousel-caption">
                                                                    ...
                                                                </div>-->
                            </div>
                            <%}
                                    cont++;
                                }%>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </a>
                    </div>
                </div>
            </div>

            <!-- Mostrar Puntaje Actual                        -->
            <div style="margin-bottom: 1em" class="row col-sm-11 col-centered">
                <div id="puntaje-disabled">
                    <input name="numRefProd" type="text" value="<%=dProd.getReferencia()%>" hidden="true"/>
                    <input name="user" type="text" value="<%=request.getSession().getAttribute("usuario")%>" hidden="true"/>
                    <input id="ratingOculto" name="ratingOculto" type="text" value="<%=request.getAttribute("ratingDM")%>" hidden="true"/>
                    <p>Promedio de <%=request.getAttribute("totalVotos")%> Votos</p>
                    <input id="ver-puntaje" name="puntos" type="text" class="rating" 
                           min=1 max=5 step=1 
                           data-size="sm" data-rtl="false" data-readonly="true" data-show-clear="false" 
                           data-show-caption="true">
                </div>
            </div>

            <script>
                $(document).ready(function() {
                    var ratingUpd = $("#ratingOculto").val();
                    $("#ver-puntaje").rating("update", ratingUpd);
                });
            </script>


            <div class="row col-sm-11 col-centered">
                <div style="margin-top: 1em" class="col-sm-11 col-centered">
                    <ul class="list-group">
                        <li style="text-align: center" class="list-group-item listTitle"><strong>Informacion de Producto</strong></li>
                        <li class="list-group-item listItem">Numero de referencia: <%=dProd.getReferencia()%></li>
                        <li class="list-group-item listItem">Nombre: <%=dProd.getNombre()%></li>
                        <li class="list-group-item listItem">Proveedor: <%=dProd.getDataProveedor().getCompania()%></li>
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

            <div class="row col-sm-11 col-centered">
                <div style="margin-top: 1em" class="col-sm-11 col-centered">
                    <ul class="list-group">
                        <li style="text-align: center"class="list-group-item listTitle"><strong>Especificacion</strong></li>
                        <li class="list-group-item listItem"><%=dEsp.getEspecificacion().replace("\n", "<br/>\n")%></li>
                    </ul>
                </div>
            </div>


            <%if (((Boolean) request.getAttribute("usuarioCompro")) && !((Boolean) request.getAttribute("usuarioPuntuo"))) {%>
            <!-- Agregar Puntaje                        -->
            <div class="row col-sm-11 col-centered">
                <div style="margin-bottom: 1em" class="row col-sm-11 col-centered">
                    <ul class="list-group">
                        <li style="text-align: center"class="list-group-item listTitle"><strong>Agregar Puntaje</strong></li>
                        <li class="list-group-item listItem">
                            <form role="form" id="formPuntaje" action="<%=request.getContextPath()%>/AgregarPuntaje" method="POST">
                                <input name="numRefProd" type="text" value="<%=dProd.getReferencia()%>" hidden="true"/>
                                <input name="user" type="text" value="<%=request.getSession().getAttribute("usuario")%>" hidden="true"/>
                                <input id="input-puntaje" name="puntos" type="text" class="rating" min=1 max=5 step=1 data-size="sm" data-rtl="false" data-show-clear="false" >
                                <button type="submit" id="btnAgregarPuntaje" class="btn btn-primary">Guardar Puntaje</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
            <%}%>

            <div class="row col-sm-11 col-centered">
                <div style="margin-top: 1em; margin-bottom: 1em" class="col-sm-11 col-centered">
                    <form role="form" id="formComentario" action="<%=request.getContextPath()%>/comentario" method="POST">
                        <ul class="list-group">
                            <li style="text-align: center" class="list-group-item listTitle"><span class="glyphicon glyphicon-thumbs-up"></span><strong> Comentarios </strong><span class="glyphicon glyphicon-thumbs-down"></span></li>
                                    <%if ((Boolean) request.getAttribute("usuarioCompro")) {%>
                            <li style="text-align: center" class="list-group-item listTitle">Haga clic en un comentario para responderlo</li>
                                <%} else {%>
                            <li style="text-align: center" class="list-group-item listTitle">Debe comprar el producto para poder comentar</li>
                                <%}%>
                            <div id="treecom" class="col-sm-11 col-centered"></div>
                        </ul>
                        <input name="numRefProd" type="text" value="<%=dProd.getReferencia()%>" hidden="true"/>
                        <input name="user" type="text" value="<%=request.getSession().getAttribute("usuario")%>" hidden="true"/>
                        <%if ((Boolean) request.getAttribute("usuarioCompro")) {%>
                        <div class="form-group">
                            <input type="hidden" class="form-control" name="idCom" id="idCom" placeholder="Ingrese id del comentario a responder (vacio es un comentario nuevo)." />
                        </div>
                        <div class="form-group newcomment">
                            <label for="Comentario">Comentario</label>
                            <textarea class="form-control" name="com" id="com" placeholder="Ingrese comentario."></textarea>
                            <button type="submit" id="btnGuardar" class="btn btn-success">Ingresar Comentario</button>
                        </div>
                        <%}%>
                    </form>
                </div>
            </div>
            <!--Ingreso de Reclamo-->
            <%if ((Boolean) request.getAttribute("usuarioCompro")) {%>
            <div class="row col-sm-11 col-centered">
                <div style="margin-top: 1em; margin-bottom: 1em" class="col-sm-11 col-centered">
                    <ul class="list-group" style="margin-bottom:0;">
                        <li style="text-align: center"class="list-group-item listTitle"><strong>Reclamos</strong></li>
                        <li class="list-group-item listItem">
                            <form role="form" id="formReclamo" action="<%=request.getContextPath()%>/IngresarReclamo" method="POST">
                                <input name="numRefProd" type="text" value="<%=dProd.getReferencia()%>" hidden="true"/>
                                <input name="user" type="text" value="<%=request.getSession().getAttribute("usuario")%>" hidden="true"/>
                                <div class="form-group newcomment">
                                    <textarea class="form-control" name="reclamo" id="reclamo" placeholder="Ingrese reclamo."></textarea>
                                    <button type="submit" id="btnGuardar" class="btn btn-danger">Ingresar Reclamo</button>
                                </div>
                            </form>
                        </li>
                    </ul>

                </div>
            </div>
            <%}%>
            <!--////////////////////////////////-->

            <!--            <script src="../../static/bootstrap/js/vendor/jquery-1.11.0.min.js"></script>-->
            <script src="../../static/jstree/jstree.js"></script>

            <script>
                    $(function() {
                        $('#treecom')
                                // listen for event
                                .on('changed.jstree', function(e, data) {
                            var i, j, r = [];
                            for (i = 0, j = data.selected.length; i < j; i++) {
                                r.push(data.instance.get_node(data.selected[i]).id);
                            }
                            document.getElementById('idCom').value = r.join(',');
                            $(function setFocusToTextBox() {
                                document.getElementById("com").focus();
                            });
                        });

                        $('#treecom').jstree({
                            'core': {
                                'data': {
                                    type: "POST",
                                    cache: false,
                                    url: "/GetJSONcomentario",
                                    dataType: "json"
                                },
                                "animation": 0,
                                "check_callback": true,
                                "themes": {
                                    "theme": "default",
                                    "dots": false,
                                    "icons": false
                                }
                            },
                            "plugins": ["wholerow", "types"]
                        }).bind("loaded.jstree", function(event, data) {
                            $(this).jstree("open_all");
                        });
                    });
            </script>

        </div>
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
