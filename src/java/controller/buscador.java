/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.WScategoria.CategoryException_Exception;
import controller.WSproducto.DataProducto;
import controller.WSproducto.ProductoException_Exception;
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
            throws ServletException, IOException, ProductoException_Exception, CategoryException_Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String stringBuscado = request.getParameter("a_buscar").trim();
            if (stringBuscado.length() < 3) {
                request.setAttribute("error", "La palabra buscada debe tener al menos 3 letras");
                //request.getRequestDispatcher("/vistas/producto/ProductNavigation.jsp").forward(request, response);
                //Poner algo para que avise en el form
            } else {
                List<DataProducto> listResult = buscarProductoNombreSimilar(stringBuscado);

                List<controller.WScategoria.DataProducto> catResult = getProductosPorNombreCategoria(stringBuscado);
                //itero para no agregar un producto dos veces (que ya haya sido agregado por similitud)
                
                if (catResult != null && !catResult.isEmpty()) {
                    for (controller.WScategoria.DataProducto dp : catResult) {
                        boolean yaesta = false;
                        for (DataProducto result : listResult) {
                            if (dp.getNombre().equals(result.getNombre())) {
                                yaesta = true;
                            }
                        }
                        if (!yaesta) {
                            DataProducto dpProd = new DataProducto();
                            dpProd.setReferencia(dp.getReferencia());
                            dpProd.setNombre(dp.getNombre());
                            dpProd.getDataEspecificacion().setPrecio(dp.getDataEspecificacion().getPrecio());
                            dpProd.getDataEspecificacion().setImagenes(dp.getDataEspecificacion().getImagenes());
                                 
                            listResult.add(dpProd);
                        }
                    }
                }
                if (listResult == null || listResult.isEmpty()) {
                    JSONObject noencontrado = new JSONObject();
                    noencontrado.put("result_search", false);
                    out.print(noencontrado);
                } else {
//                    request.setAttribute("result_search", listResult);
//                    request.getRequestDispatcher("/vistas/producto/SearchProductos.jsp").forward(request, response);
                    //                    request.setAttribute("result_search", listResult);
//                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/producto/SearchProductos.jsp");
//                    dispatcher.forward(request, response);
                }
                request.setAttribute("result_search", listResult);
                RequestDispatcher rd1 = request.getRequestDispatcher("/vistas/producto/SearchProductos.jsp");
                rd1.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(InfoProducto.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ProductoException_Exception ex) {
            Logger.getLogger(buscador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CategoryException_Exception ex) {
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
        } catch (ProductoException_Exception ex) {
            Logger.getLogger(buscador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CategoryException_Exception ex) {
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

    private static java.util.List<controller.WSproducto.DataProducto> buscarProductoNombreSimilar(java.lang.String arg0) throws ProductoException_Exception {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service();
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.buscarProductoNombreSimilar(arg0);
    }

    private static java.util.List<controller.WScategoria.DataProducto> getProductosPorNombreCategoria(java.lang.String arg0) throws CategoryException_Exception {
        controller.WScategoria.CategoriaWS_Service service = new controller.WScategoria.CategoriaWS_Service();
        controller.WScategoria.CategoriaWS port = service.getCategoriaWSPort();
        return port.getProductosPorNombreCategoria(arg0);
    }
}
