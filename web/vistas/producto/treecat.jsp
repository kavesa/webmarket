<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <link rel="stylesheet" href="../../static/jstree/themes/default/style.min.css" />
        <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.min.css" />
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>

        <div id="treecat" class="treeDiv" style="margin-top:4em;">
        </div>
        
        <div id="seleccionados">
        </div>

        <script src="../../static/bootstrap/js/vendor/jquery-1.11.0.min.js"></script>
        <script src="../../static/jstree/jstree.js"></script>
        <script>
            $(function() {
                $('#treecat')
                        // listen for event
                        .on('changed.jstree', function(e, data) {
                    var i, j, r = [];
                    for (i = 0, j = data.selected.length; i < j; i++) {
                        r.push(data.instance.get_node(data.selected[i]).text);
                    }
                    $('#seleccionados').html('Selected: ' + r.join(', '));
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
                    "plugins": ["checkbox"]
                });
            });
        </script>
        <script>

            arr = $("#treecat").jstree('get_checked');
            for (var i = 0; i < arr.length; ++i) {
                console.log(arr[i]);
            }

        </script>


        <%@include file="../../WEB-INF/jspf/bottom.jspf" %>

    </body>
</html>