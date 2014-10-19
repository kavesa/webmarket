/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.datatype.DataCategoria;
import direct.market.datatype.DataProducto;
import direct.market.datatype.DataUsuario;
import direct.market.exceptions.CategoryException;
import direct.market.exceptions.UsuarioException;
import direct.market.factory.Factory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nightmare
 */
public class InfoProducto extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UsuarioException {

        try {

            HttpSession sesion = request.getSession();
            String refProd = request.getParameter("nocid");
            DataProducto prod = Factory.getInstance().getProductoController().buscarProductoPorRef(refProd);
            String nickname = (String) sesion.getAttribute("usuario");
            List<DataCategoria> catList = new ArrayList<DataCategoria>();

//        boolean invitado = true;
//        Enumeration<String> attNames = sesion.getAttributeNames();
//        for(;attNames.hasMoreElements();attNames.nextElement()) {
//            if(attNames.nextElement() == "usuario")
//        }
            boolean usuarioCompro = false;
            if (nickname != null) {
                String loUsNick = (String) sesion.getAttribute("usuario");
                DataUsuario du2;
                du2 = Factory.getInstance().getUsuarioController().getDataProveedor(loUsNick);

                if (du2 != null) {
                    request.setAttribute("tipoU", "n");
                } else {
                    request.setAttribute("tipoU", "y");
                }

                usuarioCompro = Factory.getInstance().getUsuarioController().usuarioComproProducto(nickname, refProd);

            } else {
                request.setAttribute("tipoU", "n");
            }
            request.setAttribute("usuarioCompro", usuarioCompro);
            catList = Factory.getInstance().getCategoriaController().getCategoriasDeProducto(prod.getReferencia());

            request.setAttribute("pCat", catList);
            request.setAttribute("datosProd", prod);
            request.getRequestDispatcher("/vistas/producto/InfoProducto.jsp").forward(request, response);
        } catch (CategoryException ex) {
            Logger.getLogger(InfoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UsuarioException uex) {
            Logger.getLogger(InfoProducto.class.getName()).log(Level.SEVERE, null, uex);
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
        try {
            processRequest(request, response);
        } catch (UsuarioException ex) {
            Logger.getLogger(InfoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (UsuarioException ex) {
            Logger.getLogger(InfoProducto.class.getName()).log(Level.SEVERE, null, ex);
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
