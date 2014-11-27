package controller;

//import direct.market.datatype.DataComentario;
//import direct.market.datatype.DataProducto;
//import direct.market.factory.Factory;
import controller.WSproducto.DataComentario;
import controller.WSproducto.DataProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
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
@WebServlet(name = "GetJSONcomentario", urlPatterns = {"/GetJSONcomentario"})
public class GetJSONcomentario extends HttpServlet {
    private static final long serialVersionUID = 4838013483227747053L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String prodRef = request.getSession().getAttribute("ref").toString();
            //String prodRef = "1";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DataProducto dprod = buscarProductoPorRef(prodRef);
            //DataProducto dprod = Factory.getInstance().getProductoController().buscarProductoPorRef("7");
            List<DataComentario> dcomlist = dprod.getListDataComentarios();

            JSONArray jSONArray = new JSONArray();

            if (dcomlist != null && !dcomlist.isEmpty()) {
                for (int i = 0; i < dcomlist.size();) {
                    JSONObject jSONObject = new JSONObject();
                    //verifico si esta en la raiz o tiene padre
                    if (dcomlist.get(i).getParent() == 0) {
                        jSONObject.put("id", dcomlist.get(i).getId());
                        jSONObject.put("parent", "#");
                        jSONObject.put("text", "<div class=\"comment-node\"><strong>" + 
                                dcomlist.get(i).getNickname() + 
                                " dijo el " + sdf.format(controller.util.gregorianTOdate(dcomlist.get(i).getFechaComentario())) + 
                                ": </strong><br/>" + dcomlist.get(i).getComentario() + "</div>");
                        //jSONObject.put("fecha", sdf.format(dcomlist.get(i).getFechaComentario()));
                        //jSONObject.put("nick", dcomlist.get(i).getNickname());

                    } else {

                        jSONObject.put("id", dcomlist.get(i).getId());
                        jSONObject.put("parent", dcomlist.get(i).getParent());
                        jSONObject.put("text", "<div class=\"comment-node\"><strong>" + 
                                dcomlist.get(i).getNickname() + " respondio el " + 
                                sdf.format(controller.util.gregorianTOdate(dcomlist.get(i).getFechaComentario())) + 
                                ": </strong><br/>" + dcomlist.get(i).getComentario() + "</div>");
                        //jSONObject.put("fecha", sdf.format(dcomlist.get(i).getFechaComentario()));
                        //jSONObject.put("nick", dcomlist.get(i).getNickname());

                    }

                    if (!jSONObject.isEmpty()) {
                        jSONArray.add(jSONObject);
                        //jSONObject = null;
                    }

                    i++;
                }
            } else {
                JSONObject sinComent = new JSONObject();
                sinComent.put("id", 0);
                sinComent.put("parent", "#");
                sinComent.put("text", "<strong>Nadie ha comentado aun, sea usted el primero en comentar.</strong>");
                jSONArray.add(sinComent);
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

    private static DataProducto buscarProductoPorRef(java.lang.String arg0) throws MalformedURLException {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service(new URL(Configuracion.getProperty("wsdlProducto")));
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.buscarProductoPorRef(arg0);
    }
}