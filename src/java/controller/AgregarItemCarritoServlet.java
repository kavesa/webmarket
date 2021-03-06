/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import direct.market.datatype.DataEspecificacionProducto;
//import direct.market.datatype.DataLineaOC;
//import direct.market.datatype.DataProducto;
//import direct.market.factory.Factory;
import controller.WSordenCompra.DataLineaOC;
import controller.WSproducto.DataProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
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
        int totalItemsCarrito;
        if (lineas == null || lineas.isEmpty()) {
            lineas = new ArrayList<DataLineaOC>();
             totalItemsCarrito = 0;
            //Falta crear datalineaoc
        } else {
            totalItemsCarrito = Integer.parseInt(sesion.getAttribute("totalCarrito").toString());
        }
        DataLineaOC dataLinea = new DataLineaOC();
        String refProd = request.getParameter("nocid");
        DataProducto prod = buscarProductoPorRef(refProd);

        controller.WSordenCompra.DataProducto prodOC = new controller.WSordenCompra.DataProducto();
        controller.WSordenCompra.DataEspecificacionProducto depOC = new controller.WSordenCompra.DataEspecificacionProducto();
        prodOC.setNombre(prod.getNombre());
        prodOC.setReferencia(prod.getReferencia());
        depOC.setDescripcion(prod.getDataEspecificacion().getDescripcion());
        depOC.setPrecio(prod.getDataEspecificacion().getPrecio());
        depOC.setId(prod.getDataEspecificacion().getId());
        prodOC.setDataEspecificacion(depOC);
//        DataEspecificacionProducto esp = prod.getDataEspecificacion();
//        esp.setDescripcion(esp.getDescripcion());
//        esp.setPrecio(esp.getPrecio());
//        esp.setId(esp.getId());
//        prod.setDataEspecificacion(esp);
//        prod.setReferencia(prod.getReferencia());
        totalItemsCarrito += Integer.parseInt(request.getParameter("cant"));
        dataLinea.setCantidad(Integer.parseInt(request.getParameter("cant")));
        dataLinea.setProducto(prodOC);
        lineas.add(dataLinea);
        sesion.setAttribute("lineasOrden", lineas);
        sesion.setAttribute("totalCarrito", totalItemsCarrito);
        request.setAttribute("flag", "1");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InfoProducto?nocid=" + refProd);
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

    private static DataProducto buscarProductoPorRef(java.lang.String arg0) throws MalformedURLException {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service(new URL(Configuracion.getProperty("wsdlProducto")));
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.buscarProductoPorRef(arg0);
    }
}
