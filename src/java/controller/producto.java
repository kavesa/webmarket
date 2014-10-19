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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kavesa
 */
@MultipartConfig
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

            //Collection<Part> partes = request.getParts();
            /* TODO output your page here. You may use following sample code. */
            //lo que sigue son pruebas para verificar que los datos lleguen como se enviaron


            //Producto =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            DataProducto dp = new DataProducto();
            dp.setNombre(request.getParameter("NomProd"));
            dp.setReferencia(request.getParameter("RefProd"));

//Producto Categorias
            //separo el string que vino en el array de categorias (separo por comas)
            String catString = request.getParameter("catsprod");
            //String catTrim = catString.substring(1, catString.length()-1);
            List<String> catList = new ArrayList<String>(Arrays.asList(catString.split(",")));

            List<DataCategoria> lDC = new ArrayList<DataCategoria>();
            DataCategoria dataCategoria;

            for (String cat : catList) {
                if (Factory.getInstance().getCategoriaController().getCategoriaPorNombre(cat).isContieneProductos()) {
                    dataCategoria = Factory.getInstance().getCategoriaController().getCategoriaPorNombre(cat);
                    lDC.add(dataCategoria);
                }
            }
            dp.setDataCategorias(lDC);


            //Producto Proveedor
            //DataUsuario dataProv;
            String provNickname = request.getSession().getAttribute("usuario").toString();
            DataUsuario dataProv = Factory.getInstance().getUsuarioController().getDataProveedor(provNickname);
            //dataProv.setNickname(provNickname);
            //dataProv.setNickname("Eddy");
            dp.setDataProveedor(dataProv);

//Producto Especificacion
            DataEspecificacionProducto dataEsp = new DataEspecificacionProducto();
            dataEsp.setDescripcion(request.getParameter("descprod"));
            dataEsp.setEspecificacion(request.getParameter("espprod"));
            dataEsp.setPrecio(Double.parseDouble(request.getParameter("precprod")));
//Producto Especificacion Imagenes
            List<byte[]> imagenes = new ArrayList<byte[]>();

            byte[] foto1 = util.InputStreamToByteArray(request.getPart("fotoprod1").getInputStream());
            if (foto1 != null && foto1.length > 0) {
                imagenes.add(foto1);
            }

            byte[] foto2 = util.InputStreamToByteArray(request.getPart("fotoprod2").getInputStream());
            if (foto2 != null && foto2.length > 0) {
                imagenes.add(foto2);
            }

            byte[] foto3 = util.InputStreamToByteArray(request.getPart("fotoprod3").getInputStream());
            if (foto3 != null && foto3.length > 0) {
                imagenes.add(foto3);
            }
if(imagenes.isEmpty())
{
    File foo = util.getFotoEstandar("defaultP.jpg");
                byte[] foto = util.imgToBytes(foo);
                imagenes.add(foto);
}
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet altaprod</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Nombre: " + request.getParameter("NomProd") + "</h1>");
//            out.println("<h1>Referencia: " + request.getParameter("RefProd") + "</h1>");
//            out.println("<h1>Descripcion: " + request.getParameter("descprod") + "</h1>");
//            out.println("<h1>Precio: " + request.getParameter("precprod") + "</h1>");
//            out.println("<h1>Categorias: " + Arrays.toString(request.getParameterValues("catsprod")) + "</h1>");
//            out.println("<h1>CatString: " + catString + "</h1>");
//            out.println("<h1>Categoria1: " + catList.get(0).toString() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");

            dataEsp.setImagenes(imagenes);

            dp.setDataEspecificacion(dataEsp);
            Factory.getInstance().getProductoController().altaProducto(dp);

//            request.setAttribute("pCat", catList);
//            request.setAttribute("datosProd", dp);
            request.getSession().setAttribute("success", "Producto Creado Correctamente");
            request.getRequestDispatcher("/vistas/producto/altaprod.jsp").forward(request, response);
            //response.sendRedirect(response.encodeRedirectURL(catString));
        } catch (Exception ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/producto/altaprod.jsp");
            dispatcher.forward(request, response);
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
            if (!(request.getSession().getAttribute("usuario") == null)
                    && Factory.getInstance().getUsuarioController().getDataProveedor(request.getSession().getAttribute("usuario").toString()) != null) {
                pagina = "/vistas/producto/altaprod.jsp";
            } else {
                pagina = "/index.jsp";
            }
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
