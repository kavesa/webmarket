/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import direct.market.exceptions.ProductoException;
//import direct.market.factory.Factory;
import controller.WSproducto.DataProducto;
import controller.WSproducto.ProductoException;
import controller.WSproducto.ProductoException_Exception;
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
import org.json.simple.JSONObject;

/**
 *
 * @author clobes
 */
public class cheqProdAjax extends HttpServlet {

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
            out.println("<title>Servlet cheqProdAjax</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cheqProdAjax at " + request.getContextPath() + "</h1>");
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
        JSONObject jSONObject = new JSONObject();
        PrintWriter out = response.getWriter();
        String op, nomRef;
        op = request.getParameter("op");
        nomRef = request.getParameter("nomRef");
        boolean resp = false;
        if (op.equals("NomProd")) {
            try {
                resp = (buscarProductoPorName(nomRef) != null);
            } catch (ProductoException_Exception ex) {
                Logger.getLogger(cheqProdAjax.class.getName()).log(Level.SEVERE, null, ex);
            }

            jSONObject.put("resp", resp);
            out.print(jSONObject);
            //out.print(op+"+"+resp);
        } else if (op.equals("RefProd")) {
            resp = (buscarProductoPorRef(nomRef) != null);
           // PrintWriter out = response.getWriter();
            // out.print(op+",");
            //out.print(op + "+" + resp);
            jSONObject.put("resp", resp);
            out.print(jSONObject);
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

    private static DataProducto buscarProductoPorName(java.lang.String arg0) throws ProductoException_Exception, MalformedURLException {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service(new URL(Configuracion.getProperty("wsdlProducto")));
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.buscarProductoPorName(arg0);
    }

    private static DataProducto buscarProductoPorRef(java.lang.String arg0) throws MalformedURLException {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service(new URL(Configuracion.getProperty("wsdlProducto")));
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.buscarProductoPorRef(arg0);
    }
}
