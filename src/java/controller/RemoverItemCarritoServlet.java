/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mary
 */
@WebServlet(name = "RemoveFromShoppingCartServlet", urlPatterns = {"/removeFromShoppingCartServlet"})
public class RemoverItemCarritoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            out.println("<title>Servlet RemoveFromShoppingCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveFromShoppingCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType( "text/html" );          
        PrintWriter aPW = response.getWriter();          
        aPW.println( "<HTML><HEAD><TITLE>Shopping Basket using Hidden Forms" + "</TITLE></HEAD><BODY>" );          
        String newItems[] = request.getParameterValues( "items" );   
        aPW.println( "Basket's contents:<BR><BR>" );   
        if ( newItems != null ) {              
            float count = 0;              
            aPW.println( "<UL>" );              
            for ( int i=0; i < newItems.length; i++ ) {                  
                int positionOfPound = newItems[i].indexOf( "$" ) + 1;                 
                String numberStr = newItems[i].substring( positionOfPound );                  
                System.out.println( "positionOfPound = " + positionOfPound );                  
                System.out.println( "numberStr = " + numberStr );                  
                float price = Float.parseFloat( numberStr );                  
                count = count + price;                  
                aPW.println( "<LI>" + newItems[i] + "<input type='checkbox' checked='checked' name='items' value='" + newItems[i] + "'" + " />");                      
            }              
            aPW.println( "<BR><BR>Total:  $" + count );              
            aPW.println( "</UL>" );  
        }   
        aPW.println( "<FORM ACTION='AddToShoppingCart' METHOD=GET>" );          
        if ( newItems != null ) {              
            for ( int i=0; i < newItems.length; i++ ) {                  
                aPW.println( "<INPUT TYPE=hidden NAME=items VALUE='" + newItems[i] + "'>" );                  
            }  
        }  
          
        aPW.println( "<BR><INPUT TYPE=submit VALUE='Add more items'>" );          
        aPW.println( "</FORM></BODY></HTML>" );        
          
        aPW.println( "<FORM ACTION='ReviewShoppingCart' METHOD=GET>" );          
        if ( newItems != null ) {              
            for ( int i=0; i < newItems.length; i++ ) {                  
                aPW.println( "<INPUT TYPE=hidden NAME=items VALUE='" + newItems[i] + "'>" );                  
            }  
        }  
          
        aPW.println( "<BR><INPUT TYPE=submit VALUE='Remove items'>" );          
        aPW.println( "</FORM></BODY></HTML>" );  
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
