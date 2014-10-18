package controller;

import direct.market.datatype.DataComentario;
import direct.market.datatype.DataProducto;
import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
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
@WebServlet(name = "GetJSONcoment", urlPatterns = {"/GetJSONcoment"})
public class GetJSONcoment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DataProducto dprod = Factory.getInstance().getProductoController().buscarProductoPorRef("7");
            List<DataComentario> dcomlist = dprod.getListDataComentarios();

            JSONArray jSONArray = new JSONArray();

            for (int i = 0; i < dcomlist.size();) {
                JSONObject jSONObject = new JSONObject();
                //verifico si esta en la raiz o tiene padre
                if (dcomlist.get(i).getParent() == 0) {

                    jSONObject.put("id", dcomlist.get(i).getId());
                    jSONObject.put("parent", "#");
                    jSONObject.put("text", dcomlist.get(i).getComentario());
                    jSONObject.put("fecha", sdf.format(dcomlist.get(i).getFechaComentario()));
                    jSONObject.put("nick", dcomlist.get(i).getNickname());

                } else {

                    jSONObject.put("id", dcomlist.get(i).getId());
                    jSONObject.put("parent", dcomlist.get(i).getParent());
                    jSONObject.put("text", dcomlist.get(i).getComentario());
                    jSONObject.put("fecha", sdf.format(dcomlist.get(i).getFechaComentario()));
                    jSONObject.put("nick", dcomlist.get(i).getNickname());

                }

                if (!jSONObject.isEmpty()) {
                    jSONArray.add(jSONObject);
                    //jSONObject = null;
                }

                i++;
            }
            out.print(jSONArray.toJSONString().toString());
            //jSONArray = null;
        } catch (Exception e) {

//        try {
//            //DataProducto dprod = Factory.getInstance().getProductoController().buscarProductoPorRef(request.getSession().getAttribute("ref").toString());
//            DataProducto dprod = Factory.getInstance().getProductoController().buscarProductoPorRef("7");
//            JSONArray jSONArray = new JSONArray();
//            List<DataComentario> dcomlist = dprod.getListDataComentarios();
//
//            for (DataComentario dcom : dcomlist) {
//                JSONObject json = new JSONObject();
//                if (dcom.getParent() == 0) {
//                    json.put("id", String.valueOf(dcom.getId()));
//                    json.put("parent", "#");
//                    json.put("nick", dcom.getNickname());
//                    json.put("texto", dcom.getComentario());
//                    json.put("fecha", dcom.getFechaComentario().toString());
//                } else {
//                    json.put("id", String.valueOf(dcom.getId()));
//                    json.put("parent", String.valueOf(dcom.getParent()));
//                    json.put("nick", dcom.getNickname());
//                    json.put("texto", dcom.getComentario());
//                    json.put("fecha", dcom.getFechaComentario().toString());
//                }
//                if (!json.isEmpty()) {
//                    jSONArray.add(json);
//                }
//            }
//            out.print(jSONArray.toJSONString());
//            //out.print(jSONArray.toJSONString().toString());
//
//        } catch (Exception e) {
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