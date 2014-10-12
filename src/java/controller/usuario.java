/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.enums.UsuarioType;
import direct.market.exceptions.UsuarioException;
import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jasper.tagplugins.jstl.core.Redirect;

/**
 *
 * @author clobes
 */
public class usuario extends HttpServlet {

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
            out.println("<title>Servlet addUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet usuario at " + request.getContextPath() + "</h1>");
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
        String pagina = "";
        if (op.equals("create")) {
            pagina = "/vistas/usuario/usuario.jsp";

        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
             Date fechaDate = null;
            try {
                fechaDate = formato.parse(request.getParameter("fec-nac"));
            } catch (ParseException ex) {
                Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            String tipo;
            if (request.getParameter("tipo").equals("Cliente")) {
                tipo = UsuarioType.CLIENTE.name();
            } else {
                tipo = UsuarioType.PROVEEDOR.name();
            }
            //Date fech = new Date();
            Factory.getInstance().getUsuarioController().altaUsuario(request.getParameter("nickname"), request.getParameter("pass"), request.getParameter("nom"), request.getParameter("ape"), fechaDate, request.getParameter("mail"), "ruta-foto", tipo, request.getParameter("nom-comp"), request.getParameter("url"));
            request.getSession().setAttribute("success", "Usuario Creado Correctamente.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuario/usuario.jsp");
            dispatcher.forward(request, response);
        } catch (UsuarioException ex) {
            // Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
            //String encodeRedirectURL = response.encodeRedirectURL("/vistas/usuario/usuario.jsp");
            //ex.getMessage();
            request.getSession().setAttribute("error", ex.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuario/usuario.jsp");
            dispatcher.forward(request, response);
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
}
