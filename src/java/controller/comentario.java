/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.WSproducto.DataComentario;
import controller.WSproducto.ProductoException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author clobes
 */
public class comentario extends HttpServlet {

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
            out.println("<title>Servlet comentario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet comentario at " + request.getContextPath() + "</h1>");
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
        String texto = null, idProd = null;
        String padre = null;
        Date fecha = new Date();
        idProd = request.getParameter("numRefProd");
        padre = request.getParameter("idCom");
        int padre1;
        if (padre.equals("")) {
            padre1 = 0;
        } else {
            padre1 = Integer.parseInt(padre);
        }
        texto = request.getParameter("com");

        if (texto == null || texto.isEmpty()) {
            request.getSession().setAttribute("error", "Debe ingresar el texto del comentario.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InfoProducto?nocid=" + idProd);
            dispatcher.forward(request, response);
        } else {

            String user = request.getParameter("user"); //esta tirando un objeto para la base en lugar de string

            //DataComentario dc = new DataComentario(padre, "comentariode prueba", fecha);
            DataComentario dc = new DataComentario();
            dc.setNickname(user);
            dc.setParent(padre1);
            dc.setComentario(texto);
            try {
                dc.setFechaComentario(controller.util.dateTOgregorian(fecha));
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(comentario.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                agregarComentario(idProd, dc);
                request.getSession().setAttribute("success", "Comentario ingresado con éxito.");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InfoProducto?nocid=" + idProd);
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(comentario.class.getName()).log(Level.SEVERE, null, ex);
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

    private static void agregarComentario(java.lang.String arg0, controller.WSproducto.DataComentario arg1) throws ProductoException_Exception, MalformedURLException {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service(new URL(Configuracion.getProperty("wsdlProducto")));
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        port.agregarComentario(arg0, arg1);
    }
}
