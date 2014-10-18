package controller;

import direct.market.datatype.DataComentario;
import direct.market.datatype.DataProducto;
import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author TaherT
 */
@WebServlet(name = "GetJSONcomentario", urlPatterns = {"/GetJSONcomentario"})
public class GetJSONcomentario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            //DataProducto dprod = Factory.getInstance().getProductoController().buscarProductoPorRef(request.getSession().getAttribute("ref").toString());
            DataProducto dprod = Factory.getInstance().getProductoController().buscarProductoPorRef("7");
            JSONArray jSONArray = new JSONArray();

            for (DataComentario dcom : dprod.getListDataComentarios()) {
                JSONObject json = new JSONObject();
                if (dcom.getParent() == 0) {
                    json.put("id", String.valueOf(dcom.getId()));
                    json.put("parent", "#");
                    json.put("nick", dcom.getNickname());
                    json.put("texto", dcom.getComentario());
                    json.put("fecha", dcom.getFechaComentario().toString());
                } else {
                    json.put("id", String.valueOf(dcom.getId()));
                    json.put("parent", String.valueOf(dcom.getParent()));
                    json.put("nick", dcom.getNickname());
                    json.put("texto", dcom.getComentario());
                    json.put("fecha", dcom.getFechaComentario().toString());
                }
                if (!json.isEmpty()) {
                    jSONArray.add(json);
                }
            }

            out.print(jSONArray.toJSONString().toString());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}