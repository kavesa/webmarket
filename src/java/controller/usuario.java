/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import direct.market.enums.UsuarioType;
import direct.market.exceptions.UsuarioException;
import direct.market.factory.Factory;
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
        //////////////////////////////////////////////////////////////////
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        factory.setSizeThreshold(1024);
//        String ubicacionArchivo = getServletContext().getRealPath("/") + "static/media/img/";
//        // Configure a repository (to ensure a secure temp location is used)
//        ServletContext servletContext = this.getServletConfig().getServletContext();
//        //File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(new File(ubicacionArchivo));
//
//        // Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        // Parse the request
//        List<FileItem> items = null;
//        try {
//            items = upload.parseRequest(request);
//        } catch (FileUploadException ex) {
//            Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//


        // Process the uploaded items
//        Iterator<FileItem> iter = items.iterator();

//        while (iter.hasNext()) {
//            FileItem item = iter.next();
//
//            if (item.isFormField()) {
//                //String name = item.getFieldName();
//                if (item.getFieldName().equals("tipo")) {
//                    tipoForm = item.getString();
//                }
//                if (item.getFieldName().equals("nickname")) {
//                    nickForm = item.getString();
//                }
//                if (item.getFieldName().equals("pass")) {
//                    passForm = item.getString();
//                }
//                if (item.getFieldName().equals("nom")) {
//                    nomForm = item.getString();
//                }
//                if (item.getFieldName().equals("ape")) {
//                    apeForm = item.getString();
//                }
//                if (item.getFieldName().equals("mail")) {
//                    mailForm = item.getString();
//                }
//                if (item.getFieldName().equals("fecNac")) {
//                    fecNacForm = item.getString();
//                }
//                if (item.getFieldName().equals("nom-comp")) {
//                    nomCompForm = item.getString();
//                }
//                if (item.getFieldName().equals("url")) {
//                    urlForm = item.getString();
//                }
//            } else {
        try {
//                    String ext = getExtension(item.getName());
//                    String ubicacionBd = ubicacionArchivo + nickForm + "." + ext;
//                    if (item.getName().equals("")) {
//                        ubicacionBd = ubicacionArchivo + "Perfil.jpg";
//                    } else {
//                        File file = new File(ubicacionArchivo, nickForm + "." + ext);
//                        item.write(file);
//                    }

            ///////////////////////////////////////////////////////////////////

            String tipoForm = "", nickForm, passForm, nomForm, apeForm, mailForm, fecNacForm,
                    nomCompForm = "", urlForm = "";

            nickForm = request.getParameter("nickname");
            passForm = request.getParameter("repass");
            nomForm = request.getParameter("nom");
            apeForm = request.getParameter("ape");
            mailForm = request.getParameter("mail");
            fecNacForm = request.getParameter("fecNac");
            tipoForm = request.getParameter("tipo");

            byte[] foto = util.InputStreamToByteArray(request.getPart("imgUser").getInputStream());
            if (foto == null || foto.length <= 0) {
                foto = null;
            }

//                    tipoForm = "", nickForm = null, passForm = null,
//                nomForm = null, apeForm = null, mailForm = null,
//                fecNacForm = null, nomCompForm = null, urlForm = null;

            
            
            //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate = null;
            try {
                fechaDate = formato.parse(fecNacForm);
            } catch (ParseException ex) {
                Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            String tipo;
            if (tipoForm.equals("Cliente")) {
                tipo = UsuarioType.CLIENTE.name();
            } else {
                tipo = UsuarioType.PROVEEDOR.name();
                nomCompForm = request.getParameter("nom-comp");
                urlForm = request.getParameter("url");
            }
            //impacta en bd
            //Factory.getInstance().getUsuarioController().altaUsuario(nickForm, passForm, nomForm, apeForm, fechaDate, mailForm, ubicacionBd, tipo, nomCompForm, urlForm);
            Factory.getInstance().getUsuarioController().altaUsuario(nickForm, passForm, nomForm, apeForm, fechaDate, mailForm, foto, tipo, nomCompForm, urlForm);
            //mensaje de success y redirige a usuario
            request.getSession().setAttribute("success", "Usuario Creado Correctamente.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuario/usuario.jsp");
            dispatcher.forward(request, response);

        } catch (UsuarioException ex) {
            //si ocurre alguna excepcion larga un msg
            request.getSession().setAttribute("error", ex.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuario/usuario.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
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
}
