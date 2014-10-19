/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.datatype.DataLineaOC;
import direct.market.datatype.DataOC;
import direct.market.datatype.DataUsuario;
import direct.market.exceptions.OCException;
import direct.market.exceptions.UsuarioException;
import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mary
 */
@WebServlet(name = "GenerarOrdenCompraServlet", urlPatterns = {"/GenerarOrdenCompraServlet"})
public class GenerarOrdenCompraServlet extends HttpServlet {

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
            out.println("<title>Servlet GenerarOrdenCompraServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerarOrdenCompraServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession sesion = request.getSession();
        List<DataLineaOC> lineas = (ArrayList<DataLineaOC>) sesion.getAttribute("lineasOrden");
        String usuario = (String) sesion.getAttribute("usuario");
        if (lineas != null && !lineas.isEmpty()) {
            try {
                DataUsuario dataUsuario = Factory.getInstance().getUsuarioController().getDataCliente(usuario);
                DataOC ordenCompra = new DataOC();
                ordenCompra.setLineas(lineas);
                int numeroOrden;
                numeroOrden = Factory.getInstance().getOrdenCompraController().altaOrdenCompra(ordenCompra);
                Factory.getInstance().getUsuarioController().modificarCliente(dataUsuario, numeroOrden);
                sesion.setAttribute("lineasOrden", new ArrayList<DataLineaOC>());
                request.getSession().setAttribute("success", "Gracias por su compra. Orden de compra Nro "+numeroOrden);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/carrito/carrito.jsp");
                dispatcher.forward(request, response);

            } catch (UsuarioException ex) {
                Logger.getLogger(GenerarOrdenCompraServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (OCException ex) {
                Logger.getLogger(GenerarOrdenCompraServlet.class.getName()).log(Level.SEVERE, null, ex);
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
}
