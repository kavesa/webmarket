/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import direct.market.datatype.DataUsuario;
//import direct.market.exceptions.UsuarioException;
//import direct.market.factory.Factory;
import controller.WSusuario.DataUsuario;
import controller.WSusuario.UsuarioException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author clobes
 */
public class login2 extends HttpServlet {

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
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
        String op = request.getParameter("op");
        //String action = (request.getPathInfo() != null ? request.getPathInfo() : "");
        HttpSession sesion = request.getSession();
        if (op.equals("out")) {
            sesion.invalidate();
            response.sendRedirect("index.jsp");
        }
    }

    /**
     * action Handles the HTTP
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
        HttpSession sesion = request.getSession();
        String usu, pass; 
        usu = request.getParameter("nickLogin");
        pass = request.getParameter("passLogin");
        if (sesion.getAttribute("usuario") == null) {
            try {
                DataUsuario us = login(usu, pass);
                sesion.setAttribute("usuario", us.getNickname());
                request.getSession().setAttribute("tipoUsuario", us.getTipoUsu());
                //request.getSession().setAttribute("success", "Usuario Logueado Correctamente.");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Perfil");
                dispatcher.forward(request, response);
            } catch (UsuarioException_Exception ex) {
                request.getSession().setAttribute("error", ex.getMessage());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                request.getSession().setAttribute("error", ex.getMessage());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }

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

    private static controller.WSusuario.DataUsuario login(java.lang.String arg0, java.lang.String arg1) throws UsuarioException_Exception, MalformedURLException {
        controller.WSusuario.UsuarioWS_Service service = new controller.WSusuario.UsuarioWS_Service(new URL(Configuracion.getProperty("wsdlUsuario")));
        controller.WSusuario.UsuarioWS port = service.getUsuarioWSPort();
        return port.login(arg0, arg1);
    }
}
