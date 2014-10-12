/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.datatype.DataCategoria;
import direct.market.datatype.DataEspecificacionProducto;
import direct.market.datatype.DataProducto;
import direct.market.datatype.DataUsuario;
import direct.market.exceptions.CategoryException;
import direct.market.exceptions.ProductoException;
import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kavesa
 */

public class producto extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
            throws ServletException, IOException, CategoryException, ProductoException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            //lo que sigue son pruebas para verificar que los datos lleguen como se enviaron
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet altaprod</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet altaprod at " + request.getContextPath() + "</h1>");
            out.println("<h1>Nombre: " + request.getParameter("nomprod") + "</h1>");
            out.println("<h1>Referencia: " + request.getParameter("refprod") + "</h1>");
            out.println("<h1>Descripcion: " + request.getParameter("descprod") + "</h1>");
            out.println("<h1>Precio: " + request.getParameter("precprod") + "</h1>");
            out.println("<h1>Especificacion: " + request.getParameter("espprod") + "</h1>");
            out.println("</body>");
            out.println("</html>");
            

            //Producto =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            DataProducto dp = new DataProducto();
            dp.setNombre(request.getParameter("nomprod"));
            dp.setReferencia(request.getParameter("refprod"));
//Producto Categorias
            List<DataCategoria> lDC = new ArrayList<DataCategoria>();
            DataCategoria dataCategoria;
            dataCategoria = Factory.getInstance().getCategoriaController().getCategoriaPorNombre("iPhone");
            lDC.add(dataCategoria);
            dataCategoria = Factory.getInstance().getCategoriaController().getCategoriaPorNombre("iOS");
            lDC.add(dataCategoria);
            dataCategoria = Factory.getInstance().getCategoriaController().getCategoriaPorNombre("Apple");
            lDC.add(dataCategoria);
            dp.setDataCategorias(lDC);
//Producto Proveedor
            DataUsuario dataProv = new DataUsuario();
            dataProv.setNickname("Tim1");
            dp.setDataProveedor(dataProv);
//Producto Especificacion
            DataEspecificacionProducto dataEsp = new DataEspecificacionProducto();
            dataEsp.setDescripcion(request.getParameter("descprod"));
            dataEsp.setEspecificacion(request.getParameter("espprod"));
            dataEsp.setPrecio(Double.parseDouble(request.getParameter("precprod")));
//Producto Especificacion Imagenes
            List<String> imagenes = new ArrayList<String>();
            imagenes.add("/fotos/IM1-topic_iphone_5.png");
            dataEsp.setImagenes(imagenes);
            //TODO:
            //cambiar para enviar imagenes como byte[]

            dp.setDataEspecificacion(dataEsp);
            Factory.getInstance().getProductoController().altaProducto(dp);

            
        } catch(Exception ex) {            
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
        String op = request.getParameter("op");
        String pagina = "";
        if (op.equals("create")) {
            pagina = "/vistas/producto/altaprod.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
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
        } catch (CategoryException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductoException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
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
