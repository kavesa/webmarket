<%-- 
    Document   : VerReclamos
    Created on : 12-oct-2014, 19:04:41
    Author     : K
--%>

<%@page import="controller.util"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="description" content="">
        <title> Ver Reclamos </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../../static/jstree/themes/default/style.css" />
        <%@include file="../../WEB-INF/jspf/jscss.jspf" %>
    </head>
    <body>
        <%@include file="../../WEB-INF/jspf/top.jspf" %>

        <div style="margin-bottom: 1em" class="col-sm-11 col-centered perfDiv">
            <div style="margin-top: 1em" class="row">
                <h3 class="prod-title">Reclamos</h3>
            </div>

            <div class="row col-sm-11 col-centered">
                <div style="margin-top: 1em; margin-bottom: 1em" class="col-sm-11 col-centered">
                    <form role="form" id="formComentario" action="<%=request.getContextPath()%>/comentario" method="POST">
                        <ul class="list-group">
                            <div id="treecom" class="col-sm-7 col-centered treeReclamos"></div>
                        </ul>
                    </form>
                </div>
            </div>

            <script src="../../static/bootstrap/js/vendor/jquery-1.11.0.min.js"></script>
            <script src="../../static/jstree/jstree.js"></script>

            <script>
                $(function() {
                    $('#treecom').jstree({
                        'core': {
                            'data': {
                                type: "POST",
                                cache: false,
                                url: "/GetJSONreclamos",
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
