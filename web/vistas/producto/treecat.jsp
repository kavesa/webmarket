<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <link rel="stylesheet" href="../../static/jstree/themes/default/style.min.css" />
	<link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.min.css" />
        <script src="../../static/jstree/jquery-1.10.2.min.js"></script>
        <script src="../../static/jstree/jstree.js"></script>

        
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>

        <div id="treecat"  class="demo">
        </div>
        <script>
            $(function() {
                //var cats =   [{"id": "Celulares", "text": "Celulares", "parent": "#"}, {"id": "Sistemas Operativos", "text": "Sistemas Operativos", "parent": "Celulares"}, {"id": "iOS", "text": "iOS", "parent": "Sistemas Operativos"}, {"id": "Android", "text": "Android", "parent": "Sistemas Operativos"}, {"id": "Windows Phone", "text": "Windows Phone", "parent": "Sistemas Operativos"}, {"id": "Symbian", "text": "Symbian", "parent": "Sistemas Operativos"}, {"id": "Blackberry OS", "text": "Blackberry OS", "parent": "Sistemas Operativos"}, {"id": "Equipos", "text": "Equipos", "parent": "Celulares"}, {"id": "iPhone", "text": "iPhone", "parent": "Equipos"}, {"id": "Nexus", "text": "Nexus", "parent": "Equipos"}, {"id": "Samsung", "text": "Samsung", "parent": "Equipos"}, {"id": "Galaxy S3", "text": "Galaxy S3", "parent": "Samsung"}, {"id": "Galaxy S4", "text": "Galaxy S4", "parent": "Samsung"}, {"id": "Galaxy Ace", "text": "Galaxy Ace", "parent": "Samsung"}, {"id": "Blackberry", "text": "Blackberry", "parent": "Equipos"}, {"id": "Nokia", "text": "Nokia", "parent": "Equipos"}, {"id": "Accesorios", "text": "Accesorios", "parent": "Celulares"}, {"id": "Protectores", "text": "Protectores", "parent": "Accesorios"}, {"id": "Baterías", "text": "Baterías", "parent": "Accesorios"}, {"id": "Apple", "text": "Apple", "parent": "#"}, {"id": "Videojuegos", "text": "Videojuegos", "parent": "#"}, {"id": "Playstation", "text": "Playstation", "parent": "Videojuegos"}, {"id": "Xbox", "text": "Xbox", "parent": "Videojuegos"}];
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
                    "plugins": ["contextmenu", "dnd", "search", "state", "types", "wholerow"]
                });
            });</script>

        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>

    </body>
</html>