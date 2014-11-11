/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import direct.market.datatype.DataReclamo;
//import direct.market.exceptions.ProductoException;
//import direct.market.factory.Factory;
import controller.WSproducto.DataReclamo;
import controller.WSproducto.ProductoException;
import controller.WSproducto.ProductoException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author clobes
 */
public class IngresarReclamo extends HttpServlet {

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
        Date fechaReclamo = new Date();
        String reclamante = null, texto = null, idProd = null;
        reclamante = request.getParameter("user");
        idProd = request.getParameter("numRefProd");
        texto = request.getParameter("reclamo");
        if (texto == null || texto.isEmpty()) {
            request.getSession().setAttribute("error", "Debe ingresar el texto del reclamo.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InfoProducto?nocid=" + idProd);
            dispatcher.forward(request, response);
        } else {
            DataReclamo dr = new DataReclamo();
            try {
                dr.setFechaReclamo(util.dateTOgregorian(fechaReclamo));
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(IngresarReclamo.class.getName()).log(Level.SEVERE, null, ex);
            }
            dr.setNickCliente(reclamante);
            dr.setTextoRec(texto);
            //DataReclamo dr = new DataReclamo(fechaReclamo, reclamante, texto);
            try {
                ingresarReclamo(idProd, dr);
                request.getSession().setAttribute("success", "Reclamo ingresado con éxito.");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InfoProducto?nocid=" + idProd);
                dispatcher.forward(request, response);
                /*} catch (ProductoException ex) {
                 Logger.getLogger(IngresarReclamo.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (ProductoException_Exception ex) {
                 Logger.getLogger(IngresarReclamo.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
            } catch (ProductoException_Exception ex) {
                Logger.getLogger(IngresarReclamo.class.getName()).log(Level.SEVERE, null, ex);
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

    private static void ingresarReclamo(java.lang.String arg0, controller.WSproducto.DataReclamo arg1) throws ProductoException_Exception {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service();
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        port.ingresarReclamo(arg0, arg1);
    }
}
