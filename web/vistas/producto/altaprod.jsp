<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Alta Producto</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>
        <section >
            <div class="container">
                <div class="row col-sm-10" id="us">
                    <form role="form" method="POST" action="<%=request.getContextPath()%>/altaprod">

                        <!-- Form Name -->
                        <legend>Alta de Producto</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label for="nomprod">Nombre del Producto</label>
                            <input id="nomprod" name="nomprod" type="text" placeholder="Ingrese el Nombre del Producto" class="input-xxlarge form-control" required="">

                        </div>

                        <!-- Text input-->
                        <div class="control-group">
                            <label for="refprod">Referencia del Producto</label>
                            <input id="refprod" name="refprod" type="text" placeholder="Ingrese la Referencia" class="input-xxlarge form-control" required="">
                        </div>

                        <!-- Text input-->
                        <div class="control-group">
                            <label for="descprod">Descripcion del Producto</label>
                            <input id="descprod" name="descprod" type="text" placeholder="Ingrese Descripcion del Producto" class="input-xxlarge form-control" required="">
                        </div>

                        <!-- Text input-->
                        <div class="control-group">
                            <label for="precprod">Precio del Producto</label>
                            <input id="precprod" name="precprod" type="text" placeholder="0.00" class="input-xxlarge form-control" required="">
                        </div>

                        <!-- Textarea -->
                        <div class="control-group">
                            <label for="textarea">Especificaciones</label>
                                <textarea id="espprod" name="espprod" required="" class="form-control"></textarea>
                        </div>

                        <!-- File Button --> 
                        <div class="control-group">
                            <label for="imagen1prod">Seleccione una Imagen</label>
                                <input id="imagen1prod" name="imagen1prod" class="input-file form-control" type="file">
                        </div>

                        <!-- File Button --> 
                        <div class="control-group">
                            <label for="imagen2prod">Seleccione una Imagen</label>
                                <input id="imagen2prod" name="imagen2prod" class="input-file form-control" type="file">
                        </div>

                        <!-- File Button --> 
                        <div class="control-group">
                            <label for="imagen3prod">Seleccione una Imagen</label>
                                <input id="imagen3prod" name="imagen3prod" class="input-file form-control" type="file">
                        </div>

                        <!-- Button -->
                        <div class="control-group">
                            <label class="control-label" for="altaproducto"></label>
                            <div class="controls">
                                <button id="altaproducto" name="altaproducto" class="btn btn-primary" type="submit">Crear Producto</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
            <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
    </body>
</html>
