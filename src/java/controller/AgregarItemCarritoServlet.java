/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.datatype.DataEspecificacionProducto;
import direct.market.datatype.DataLineaOC;
import direct.market.datatype.DataProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "AddToShoppingCart", urlPatterns = {"/addToShoppingCart"})
public class AgregarItemCarritoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            out.println("<title>Servlet AddToShoppingCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToShoppingCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        if(lineas==null){
            lineas = new ArrayList<DataLineaOC>();
        //Falta crear datalineaoc
        }
        DataLineaOC dataLinea = new DataLineaOC();
        DataProducto prod= (DataProducto) request.getAttribute("datosProd");
        DataEspecificacionProducto esp = prod.getDataEspecificacion();
        esp.setDescripcion(esp.getDescripcion());
        esp.setPrecio(esp.getPrecio());
        esp.setId(esp.getId());
        prod.setDataEspecificacion(esp);
        prod.setReferencia(prod.getReferencia());
        dataLinea.setCantidad(2); //falta
        dataLinea.setProducto(prod);
        lineas.add(dataLinea);
        sesion.setAttribute("lineasOrden", lineas);
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/carrito/carrito.jsp");
        dispatcher.forward(request, response);
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
