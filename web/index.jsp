<!DOCTYPE html>
<html>
    <body>
        <section>

            <div class="container">
                <%
                    String error = (String) session.getAttribute("error");
                    session.setAttribute("error", null);
                    String success = (String) session.getAttribute("success");
                    session.setAttribute("success", null);
                %>
                <%if (error != null) {%>
                <div class="alert alert-danger"><%=error%></div>
                <%} else if (success != null) {%>
                <div class="alert alert-success"><%=success%></div>
                <%}%>
            </div>
            
            <%@include file="vistas/producto/ProductNavigation.jsp" %>  

        </section>
    </body>
</html>