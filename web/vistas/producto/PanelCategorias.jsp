<%-- 
    Document   : PanelCategorias
    Created on : 14/10/2014, 08:41:31 PM
    Author     : JPInspiron
--%>
<!DOCTYPE html>

    <head>
        <link rel="stylesheet" href="../../static/jstree/themes/default/style.min.css" />
        <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.min.css" />
    </head>
    <body>
        <div id="treecat" class="treeDiv">
        </div>
        
        <div id="seleccionados">
        </div>
<!--        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>-->
        <script src="../../static/bootstrap/js/vendor/jquery-1.11.0.min.js"></script>
        <script>
        function cargar(div, desde)
        {
             $(div).load(desde);
        }
        </script>
        <script src="../../static/bootstrap/js/vendor/jquery-1.11.0.min.js"></script>
        <script src="../../static/jstree/jstree.js"></script>
        <script>
            $(function() {

                $('#treecat').jstree({
                    'core': {
                        'data': {
                            type: "POST",
                            cache: false,
                            url: "/GetJSON",
                            dataType: "json"
                        },
                        "animation": 0,
//                        "check_callback": true,
                        "themes": {
                            "theme": "default",
                            "dots": false,
                            "icons": true
                        }
                    },
//                    "plugins": ["checkbox"]
                });
            });
        </script>
        <script>

            arr = $("#treecat").jstree('get_checked');
            for (var i = 0; i < arr.length; ++i) {
                console.log(arr[i]);
            }

        </script>


        <%--<%@include file="../../WEB-INF/jspf/bottom.jspf" %>--%>

    </body>
</html>