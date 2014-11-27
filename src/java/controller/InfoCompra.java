/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import direct.market.datatype.DataOC;
//import direct.market.factory.Factory;
import controller.WSordenCompra.DataOC;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nightmare
 */
public class InfoCompra extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String noCompra = request.getParameter("nocid");
            DataOC doc = getDataOC(noCompra);
            
            if (doc.getEstados().size() == 2 && doc.getEstados().get(1).getEstado().equals("Orden Preparada")) {
                request.setAttribute("ocPreparada", "SI");
            } else {
                request.setAttribute("ocPreparada", "NO");
            }

            request.setAttribute("datosComp", doc);
            request.getRequestDispatcher("/vistas/usuario/InfoCompra.jsp").forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(InfoCompra.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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

    private static DataOC getDataOC(java.lang.String arg0) throws MalformedURLException {
        controller.WSordenCompra.OrdenCompraWS_Service service = new controller.WSordenCompra.OrdenCompraWS_Service(new URL(Configuracion.getProperty("wsdlOC")));
        controller.WSordenCompra.OrdenCompraWS port = service.getOrdenCompraWSPort();
        return port.getDataOC(arg0);
    }
}
