/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.datatype.DataProducto;
import direct.market.exceptions.CategoryException;
import direct.market.exceptions.ProductoException;
import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author kavesa
 */
public class buscador extends HttpServlet {

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
            throws ServletException, IOException, ProductoException, CategoryException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String stringBuscado = ((String) request.getParameter("a_buscar")).trim();
            if (stringBuscado.length() < 3) {
                request.setAttribute("error", "La palabra buscada debe tener al menos 3 letras");
                //request.getRequestDispatcher("/vistas/producto/ProductNavigation.jsp").forward(request, response);
                //Poner algo para que avise en el form
            } else {
                List<DataProducto> listResult = Factory.getInstance().getProductoController().buscarProductoNombreSimilar(stringBuscado);

                List<DataProducto> catResult = Factory.getInstance().getCategoriaController().getProductosPorNombreCategoria(stringBuscado);
                //itero para no agregar un producto dos veces (que ya haya sido agregado por similitud)
                if (catResult != null && !catResult.isEmpty()) {
                    for (DataProducto dp : catResult) {
                        boolean yaesta = false;
                        for (DataProducto result : listResult) {
                            if (dp.getNombre().equals(result.getNombre())) {
                                yaesta = true;
                            }
                        }
                        if (!yaesta) {
                            listResult.add(dp);
                        }
                    }
                }
                if (listResult == null || listResult.isEmpty()) {
                    JSONObject noencontrado = new JSONObject();
                    noencontrado.put("result_search", false);
                    out.print(noencontrado);
                } else {

                    request.setAttribute("result_search", listResult);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/producto/SearchProductos.jsp");
                    dispatcher.forward(request, response);
//                    request.setAttribute("result_search", listResult);
//                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/producto/SearchProductos.jsp");
//                    dispatcher.forward(request, response);
                }
            }
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
        try {
            processRequest(request, response);
        } catch (ProductoException ex) {
            Logger.getLogger(buscador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CategoryException ex) {
            Logger.getLogger(buscador.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ProductoException ex) {
            Logger.getLogger(buscador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CategoryException ex) {
            Logger.getLogger(buscador.class.getName()).log(Level.SEVERE, null, ex);
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
