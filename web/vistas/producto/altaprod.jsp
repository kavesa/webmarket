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

        <link rel="stylesheet" href="../../static/jstree/themes/default/style.css" />
        <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.css" />

        <link rel="stylesheet" href="../../static/jasny-bootstrap/css/jasny-bootstrap.min.css" />

        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>

    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>

        <div class="container-fluid">
            <form role="form" method="POST" action="/producto" enctype="multipart/form-data">

                <h2>Alta de Producto</h2>
                <div id="rootwizard">
                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container">
                                <!-- 1. Create the tabs themselves  -->
                                <!-- data-toggle required. -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li><a href="#step1" role="tab" data-toggle="tab">Datos Basicos</a></li>
                                    <li><a href="#step2" role="tab" data-toggle="tab">Categorias</a></li>
                                    <li><a href="#step3" role="tab" data-toggle="tab">Imagenes</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- 2. Create progress bar -->
                    <!-- div class="progress" required. -->
                    <!-- on div id="progressBar" class="progress" required. -->
                    <div class="progress">
                        <div id="progressBar" class="progress-bar progress-bar-striped active"  >
                            <div class="bar">
                                <span></span>
                            </div>
                        </div>
                    </div>

                    <!-- 3. Create a matching tab pane for each tab. Content goes within these panes -->
                    <div class="tab-content">
                        <div class="tab-pane active" id="step1">
                            <h3>Ingrese Datos Basicos del Producto</h3>

                            <!-- Text input-->
                            <div class="form-group" style="margin-bottom: 1em;">
                                <label for="nomprod">Nombre del Producto</label>
                                <input id="nomprod" name="nomprod" type="text" placeholder="Ingrese el Nombre del Producto" class="input-xxlarge form-control" required="">

                            </div>

                            <!-- Text input-->
                            <div class="control-group" style="margin-bottom: 1em;">
                                <label for="refprod">Referencia del Producto</label>
                                <input id="refprod" name="refprod" type="text" placeholder="Ingrese la Referencia" class="input-xxlarge form-control" required="">
                            </div>

                            <!-- Text input-->
                            <div class="control-group" style="margin-bottom: 1em;">
                                <label for="descprod">Descripcion del Producto</label>
                                <input id="descprod" name="descprod" type="text" placeholder="Ingrese Descripcion del Producto" class="input-xxlarge form-control" required="">
                            </div>

                            <!-- Text input-->
                            <div class="control-group" style="margin-bottom: 1em;">
                                <label for="precprod">Precio del Producto</label>
                                <input id="precprod" name="precprod" type="text" placeholder="0.00" class="input-xxlarge form-control" required="">
                            </div>

                            <!-- Textarea -->
                            <div class="control-group" style="margin-bottom: 1em;">
                                <label for="textarea">Especificaciones</label>
                                <textarea id="espprod" name="espprod" required="" class="form-control"></textarea>
                            </div>


                        </div>
                        <div class="tab-pane" id="step2">
                            <h3>Seleccione las Categorias del Producto</h3>

                            <!--
                            <p style="float:left; margin-right:5px;">Categorias Seleccionadas: </p>

                            <div id="seleccionados" class="treeSelectedDiv">
                            </div>
                            -->
                            
                            <input type="hidden" name="catsprod" id="catsprod" value="" />

                            <div id="treecat">
                            </div>

                            <script src="../../static/bootstrap/js/vendor/jquery-1.11.0.min.js"></script>
                            <script src="../../static/jstree/jstree.js"></script>
                            <!-- javascript del JSTREE -->
                            <script>
                                $(function() {
                                    $('#treecat')
                                            // listen for event
                                        .on('changed.jstree', function(e, data) {
                                        var i, j, r = [];
                                        for (i = 0, j = data.selected.length; i < j; i++) {
                                            r.push(data.instance.get_node(data.selected[i]).text);
                                        }
                                        document.getElementById('catsprod').value = r.join(',');

                                        //$('#seleccionados').html(r.join(', '));
                                        });

                                    $('#treecat').jstree({
                                        'core': {
                                            'data': {
                                                type: "POST",
                                                cache: false,
                                                url: "/GetJSON",
                                                dataType: "json"
                                            },
                                            "animation": 0,
                                            "check_callback": true,
                                            "themes": {
                                                "theme": "default",
                                                "dots": true,
                                                "icons": true
                                            }
                                        },
                                        "plugins": ["wholerow", "checkbox", "types"],
                                        "checkbox": {
                                            "two_state": true
                                        },
                                        "types": {
                                            "types": {
                                                "disabled": {
                                                    "check_node": false,
                                                    "uncheck_node": false
                                                },
                                                "default": {
                                                    "check_node": function (node) {
                                                        $(node).children('ul').children('li').children('a').children('.jstree-checkbox').click();
                                                        return true;
                                                    },
                                                    "unckeck_node": function (node) {
                                                        $(node).children('ul').children('li').children('a').children('.jstree-checkbox').click();
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    });
                                });
                            </script>
                        </div>
                        <div class="tab-pane" id="step3">
                            <h3>Seleccione las Imagenes del Producto</h3>

                            <script src="../../static/jasny-bootstrap/js/jasny-bootstrap.min.js"></script>     

                            <div class="center-block">

                                <div class="fileinput fileinput-new" data-provides="fileinput">
                                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
                                    <div>
                                        <span class="btn btn-default btn-file"><span class="fileinput-new">Seleccionar Imagen</span><span class="fileinput-exists">Cambiar</span><input type="file" name="fotoprod1" id="fotoprod1" multiple="multiple" /></span>
                                        <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Quitar</a>
                                    </div>
                                </div>

                                <div class="fileinput fileinput-new" data-provides="fileinput">
                                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
                                    <div>
                                        <span class="btn btn-default btn-file"><span class="fileinput-new">Seleccionar Imagen</span><span class="fileinput-exists">Cambiar</span><input type="file" name="fotoprod2" id="fotoprod2" multiple="multiple" /></span>
                                        <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Quitar</a>
                                    </div>
                                </div>

                                <div class="fileinput fileinput-new" data-provides="fileinput">
                                    <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 200px; height: 150px;"></div>
                                    <div>
                                        <span class="btn btn-default btn-file"><span class="fileinput-new">Seleccionar Imagen</span><span class="fileinput-exists">Cambiar</span><input type="file" name="fotoprod3" id="fotoprod3" multiple="multiple" /></span>
                                        <a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Quitar</a>
                                    </div>
                                </div>

                            </div>
                            <p>Maximo: 3 imagenes</p>
                        </div>

                        <!-- 4. Declare buttons used by the wizard. -->
                        <!-- "pager wizard" required. -->
                        <ul class="pager wizard">
                            <!-- These show as disabled on first tab. Add style="display:none;" to make the First button disappear when first tab.      -->
                            <li class="previous" style="display:none;" ><a href="#" accesskey="p">Anterior</a></li>
                            <li class="last" style="display:none;" >
                                <label class="control-label" for="altaproducto"></label>
                                <div class="last controls">
                                    <button id="altaproducto" name="altaproducto" class="btn btn-primary btn-success" type="submit" style="margin-top: 1em">Crear Producto</button>
                                </div>
                            </li>

                            <li class="next"><a href="#" accesskey="n">Siguiente</a></li>
                        </ul>    
                    </div><!-- ./tab-content -->

                </div><!-- ./rootwizard -->

            </form>
        </div><!-- ./container-fluid -->

        <script src="../../static/bootstrap/js/vendor/jquery-1.11.0.min.js"></script>
        <script src="../../static/bootstrap/js/vendor/bootstrap.min.js"></script>
        <script src="../../static/bootstrap/js/vendor/jquery.bootstrap.wizard.js"></script>
        <!-- Load javascript at bottom of the file to avoid delays loading other resources -->
        <!-- javascript del WIZARD -->
        <script>
            $(document).ready(function() {
                $('#rootwizard').bootstrapWizard({
                    onTabShow: function(tab, navigation, index) {

                        // Dynamically change percentage completion on progress bar
                        var tabCount = navigation.find('li').length;
                        var current = index + 1;
                        var percentDone = (current / tabCount) * 100;
                        $('#rootwizard').find('#progressBar').css({width: percentDone + '%'});

                        // Optional: Show Done button when on last tab; 
                        // It is invisible by default.
                        $('#rootwizard').find('.last').toggle(current >= tabCount);

                        // Optional: Hide Next button if on last tab; 
                        // otherwise it shows but is disabled
                        $('#rootwizard').find('.next').toggle(current < tabCount);
                        $('#rootwizard').find('.previous').toggle(current > 1);
                    },
                    onTabClick: function(tab, navigation, index) {
                        return false;
                    },
                    onNext: function(tab, navigation, index) {
                        if (index === 1) {
                            if (!$('#nomprod').val()) {
                                alert('Debe ingresar un Nombre del Producto');
                                $('#nomprod').focus();
                                return false;
                            }
                            if (!$('#refprod').val()) {
                                alert('Debe ingresar la Referencia');
                                $('#refprod').focus();
                                return false;
                            }
                            if (!$('#descprod').val()) {
                                alert('Debe ingresar una Descripcion');
                                $('#descprod').focus();
                                return false;
                            }
                            if (!$('#precprod').val()) {
                                alert('Debe ingresar el Precio');
                                $('#precprod').focus();
                                return false;
                            }
                            if (!$('#espprod').val()) {
                                alert('Debe ingresar las Especificaciones');
                                $('#espprod').focus();
                                return false;
                            }
                        }
                        if (index === 2) {
                            if(!$('#catsprod').val()) {
                                alert('Debe seleccionar al menos una categoria');
                                return false;
                            }
                        }
                    }
                });
            });
        </script>

       
        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>
        <script src="../../static/jstree/jstree.js"></script>
    </body>
</html>
