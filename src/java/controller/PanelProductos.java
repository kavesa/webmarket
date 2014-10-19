/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.datatype.DataProducto;
import direct.market.exceptions.CategoryException;
import direct.market.factory.Factory;
import java.io.IOException;
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
 * @author JPInspiron
 */
@WebServlet(name = "PanelProductos", urlPatterns = {"/PanelProductos"})
public class PanelProductos extends HttpServlet {

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
        try {
            HttpSession session=request.getSession();
           // sesion.setAttribute("categoria", "Apple");
            String CatSelect = request.getParameter("b");
            String pepe="Apple";
            List<DataProducto> dpList = null;
            //(String)sesion.getAttribute("categoria");
            if (CatSelect==null){
                    //Traer los mas vendidos
                dpList = Factory.getInstance().getCategoriaController().getProductosPorNombreCategoria(pepe);
                session.setAttribute("listaPr", dpList);
//                dpList = null;
//                session.setAttribute("prueba","No hay naranja en la categoria!!!");
            }
            else{
                dpList = Factory.getInstance().getCategoriaController().getProductosPorNombreCategoria(CatSelect);
                session.setAttribute("listaPr", dpList);
//                dpList=null;
//                CatSelect = "Hay algo y es la categoria " + CatSelect ;
//                session.setAttribute("prueba",CatSelect);
            }
            
            //request.getRequestDispatcher("/vistas/producto/ProductNavigation.jsp");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/producto/PanelProductos.jsp");
            dispatcher.forward(request, response);
        } catch (CategoryException ex) {
            Logger.getLogger(ProductNavigation.class.getName()).log(Level.SEVERE, null, ex);
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
//        HttpSession sesion=request.getSession();
//        sesion.setAttribute("categoria", "Apple");
//        request.getRequestDispatcher("/vistas/producto/ProductNavigation.jsp").forward(request, response);
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
}
