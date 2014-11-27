package controller;

//import direct.market.datatype.DataCategoria;
//import direct.market.factory.Factory;
import controller.WScategoria.CategoryException_Exception;
import controller.WScategoria.DataCategoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
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
            categorias = getCategorias();
            //dejar solo datos que importan: nombre, padre, id;

            JSONArray jSONArray = new JSONArray();

            for (int i = 0; i < categorias.size();) {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObjectDisabled = new JSONObject();
                JSONObject jSONObjectNoCheckBox = new JSONObject();
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
                        jSONObjectNoCheckBox.put("class", "no_checkbox");
                        jSONObject.put("a_attr", jSONObjectNoCheckBox);
                        jSONObjectNoCheckBox.put("rel", "disabled");
                        jSONObject.put("a_attr", jSONObjectNoCheckBox);
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
                        jSONObjectNoCheckBox.put("class", "no_checkbox");
                        jSONObject.put("a_attr", jSONObjectNoCheckBox);
                        jSONObjectNoCheckBox.put("rel", "disabled");
                        jSONObject.put("a_attr", jSONObjectNoCheckBox);
                    }
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

    private static java.util.List<controller.WScategoria.DataCategoria> getCategorias() throws CategoryException_Exception, MalformedURLException {
        controller.WScategoria.CategoriaWS_Service service = new controller.WScategoria.CategoriaWS_Service(new URL(Configuracion.getProperty("wsdlCategoria")));
        controller.WScategoria.CategoriaWS port = service.getCategoriaWSPort();
        return port.getCategorias();
    }
}