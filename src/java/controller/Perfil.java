package controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import controller.WSproducto.DataProducto;
import controller.WSusuario.DataUsuario;
import controller.WSusuario.UsuarioException_Exception;
//import direct.market.datatype.DataProducto;
//import direct.market.datatype.DataUsuario;
import java.io.IOException;
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
public class Perfil extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UsuarioException_Exception {
        try {
            HttpSession sesion=request.getSession();
            String loUsNick=(String)sesion.getAttribute("usuario");
            DataUsuario du2;
            du2 = getDataProveedor(loUsNick);
            //du2 = Factory.getInstance().getUsuarioController().getDataProveedor(loUsNick);
            if (du2 != null) {
                //List<DataProducto> dpList = Factory.getInstance().getProductoController().getProductListPorProveedor(loUsNick);
                List<DataProducto> dpList = getProductListPorProveedor(loUsNick);
                request.setAttribute("listaPr", dpList);
            } else {
                //du2 = Factory.getInstance().getUsuarioController().getDataCliente(loUsNick);
                du2 = getDataCliente(loUsNick);
            }

//            StringBuilder sb = new StringBuilder();
//            sb.append("data:image/png;base64,");
//            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(du2.getImagen(), false)));
//            String contourChart = sb.toString();
            String ima64b = util.byteImgToBase64(du2.getImagen());
            request.setAttribute("img64", ima64b);
            
            request.setAttribute("usuario", du2);
            request.getRequestDispatcher("/vistas/usuario/Perfil.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (UsuarioException_Exception ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (UsuarioException_Exception ex) {
            Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
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

    private static controller.WSusuario.DataUsuario getDataProveedor(java.lang.String arg0) {
        controller.WSusuario.UsuarioWS_Service service = new controller.WSusuario.UsuarioWS_Service();
        controller.WSusuario.UsuarioWS port = service.getUsuarioWSPort();
        return port.getDataProveedor(arg0);
    }

    private static java.util.List<controller.WSproducto.DataProducto> getProductListPorProveedor(java.lang.String arg0) {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service();
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.getProductListPorProveedor(arg0);
    }

    private static controller.WSusuario.DataUsuario getDataCliente(java.lang.String arg0) throws UsuarioException_Exception {
        controller.WSusuario.UsuarioWS_Service service = new controller.WSusuario.UsuarioWS_Service();
        controller.WSusuario.UsuarioWS port = service.getUsuarioWSPort();
        return port.getDataCliente(arg0);
    }
}