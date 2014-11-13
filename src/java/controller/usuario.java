/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.WSusuario.UsuarioException_Exception;
//import direct.market.enums.UsuarioType;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author clobes
 */
@MultipartConfig
public class usuario extends HttpServlet {

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
            out.println("<title>Servlet addUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet usuario at " + request.getContextPath() + "</h1>");
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
        String op = request.getParameter("op");
        String pagina = "";
        if (op.equals("create")) {
            pagina = "/vistas/usuario/usuario.jsp";

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
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String tipoForm = "", nickForm, passForm, nomForm, apeForm, mailForm, fecNacForm,
                    nomCompForm = "", urlForm = "";
            boolean mailing = false;

            nickForm = request.getParameter("nickname");
            passForm = request.getParameter("repass");
            nomForm = request.getParameter("nom");
            apeForm = request.getParameter("ape");
            mailForm = request.getParameter("mail");
            fecNacForm = request.getParameter("fecNac");
            tipoForm = request.getParameter("tipo");

            byte[] foto = util.InputStreamToByteArray(request.getPart("imgUser").getInputStream());
            if (foto == null || foto.length <= 0) {
                File foo = util.getFotoEstandar("default.jpg");
                foto = util.imgToBytes(foo);
            }
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate = null;
            try {
                fechaDate = formato.parse(fecNacForm);
            } catch (ParseException ex) {
                Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            String tipo;
            if (tipoForm.equals("Cliente")) {
                //tipo = UsuarioType.CLIENTE.name();
                tipo = "CLIENTE";
                mailing = request.getParameter("mailing").equals("true");
            } else {
                //tipo = UsuarioType.PROVEEDOR.name();
                tipo = "PROVEEDOR";
                nomCompForm = request.getParameter("nom-comp");
                urlForm = request.getParameter("url");
            }
            //impacta en bd
            //Factory.getInstance().getUsuarioController().altaUsuario(nickForm, passForm, nomForm, apeForm, fechaDate, mailForm, ubicacionBd, tipo, nomCompForm, urlForm);

            altaUsuario(nickForm, passForm, nomForm, apeForm, util.dateTOgregorian(fechaDate), mailForm, foto, tipo, nomCompForm, urlForm, mailing);
            
            //Factory.getInstance().getUsuarioController().altaUsuario(nickForm, passForm, nomForm, apeForm, fechaDate, mailForm, foto, tipo, nomCompForm, urlForm, mailing);
            //mensaje de success y redirige a usuario
            request.getSession().setAttribute("success", "Usuario Creado Correctamente.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuario/usuario.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            //si ocurre alguna excepcion larga un msg
            request.getSession().setAttribute("error", ex.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuario/usuario.jsp");
            dispatcher.forward(request, response);
            Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
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

    //funcion para sacar la exten del arch
    private String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    private static void altaUsuario(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, javax.xml.datatype.XMLGregorianCalendar arg4, java.lang.String arg5, byte[] arg6, java.lang.String arg7, java.lang.String arg8, java.lang.String arg9, boolean arg10) throws UsuarioException_Exception {
        controller.WSusuario.UsuarioWS_Service service = new controller.WSusuario.UsuarioWS_Service();
        controller.WSusuario.UsuarioWS port = service.getUsuarioWSPort();
        port.altaUsuario(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10);
    }
}
