/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author clobes
 */
public class cheqUserAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cheqUserAjax</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cheqUserAjax at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op, usermail;
        op = request.getParameter("op");
        usermail=request.getParameter("usermail");
        boolean resp;
        if (op.equals("nickname")) {
            resp = existeCliente(usermail);
            PrintWriter out = response.getWriter();
            out.print(op+"+"+resp);
        } else if (op.equals("mail")){
            resp = existeEmail(usermail);
            PrintWriter out = response.getWriter();
           // out.print(op+",");
            out.print(op+"+"+resp);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static boolean existeCliente(java.lang.String arg0) throws MalformedURLException {
        controller.WSusuario.UsuarioWS_Service service = new controller.WSusuario.UsuarioWS_Service(new URL(Configuracion.getProperty("wsdlUsuario")));
        controller.WSusuario.UsuarioWS port = service.getUsuarioWSPort();
        return port.existeCliente(arg0);
    }

    private static boolean existeEmail(java.lang.String arg0) throws MalformedURLException {
        controller.WSusuario.UsuarioWS_Service service = new controller.WSusuario.UsuarioWS_Service(new URL(Configuracion.getProperty("wsdlUsuario")));
        controller.WSusuario.UsuarioWS port = service.getUsuarioWSPort();
        return port.existeEmail(arg0);
    }
}
