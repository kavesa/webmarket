package controller;

import direct.market.datatype.DataCategoria;
import direct.market.factory.Factory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "GetJSON", urlPatterns = {"/GetJSON"})
public class GetJSON extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("application/json");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            List<DataCategoria> categorias = new ArrayList<DataCategoria>();
            categorias = Factory.getInstance().getCategoriaController().getCategorias();
            //dejar solo datos que importan: nombre, padre, id;

            JSONArray jSONArray = new JSONArray();

            for (int i = 0; i < categorias.size();) {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObjectDisabled = new JSONObject();
                //verifico si esta en la raiz o tiene padre
                if (categorias.get(i).getParent().toString().equals("Categorias")) {
                    if (categorias.get(i).isContieneProductos()) {
                        jSONObject.put("id", categorias.get(i).getNombre());
                        jSONObject.put("parent", "#");
                        jSONObject.put("text", categorias.get(i).getNombre());
                    } else {
                        jSONObject.put("id", categorias.get(i).getNombre());
                        jSONObject.put("parent", "#");
                        jSONObject.put("text", categorias.get(i).getNombre());
                        jSONObjectDisabled.put("disabled", true);
                        jSONObject.put("state", jSONObjectDisabled);
                    }
                } else {
                    if (categorias.get(i).isContieneProductos()) {
                        jSONObject.put("id", categorias.get(i).getNombre());
                        jSONObject.put("parent", categorias.get(i).getParent());
                        jSONObject.put("text", categorias.get(i).getNombre());
                    } else {
                        jSONObject.put("id", categorias.get(i).getNombre());
                        jSONObject.put("parent", categorias.get(i).getParent());
                        jSONObject.put("text", categorias.get(i).getNombre());
                        jSONObjectDisabled.put("disabled", true);
                        jSONObject.put("state", jSONObjectDisabled);
                    }
                }
                //le seteo el icono de acuerdo a si contiene productos o no
                if (categorias.get(i).isContieneProductos()) {
                    //jSONObject.put("icon", "static/img/algo.jpg");
                } else {
                    //jSONObject.put("icon", "static/img/folder.jpg");
                }
                /*
                
                 jSONObject.put("state", "open");
                 jSONObject.put("data", listCPV.get(i).getTechName());
                 jSONObject.put("data", categorias.get(i).getId());

                 JSONObject jsonAttr = new JSONObject();
                 jsonAttr.put("techname", categorias.get(i).getNombre());
                 jSONObject.put("attr", jsonAttr);
                 jsonAttr = null;

                 if (listCPV.get(i + 1).getId() == 0) {
                 JSONArray jsonChildarray = new JSONArray();

                 while (listCPV.get(i + 1).getId() == 0) {
                 i++;
                 JSONObject child = new JSONObject();
                 child.put("data", listCPV.get(i).getTechName());

                 JSONObject jsonChildAttr = new JSONObject();
                 jsonChildAttr.put("techname", listCPV.get(i).getTechName());
                 child.put("attr", jsonChildAttr);
                 jsonChildAttr = null;

                 jsonChildarray.add(child);
                 child = null;

                 if (listCPV.size() == (i + 1)) {
                 break;
                 }
                 }*/
                //jSONObject.put("children", jsonChildarray);
                if (!jSONObject.isEmpty()) {
                    jSONArray.add(jSONObject);
                    //jSONObject = null;
                }

                i++;
            }
            System.out.println(jSONArray);

            out.print(jSONArray.toJSONString().toString());
            //jSONArray = null;
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