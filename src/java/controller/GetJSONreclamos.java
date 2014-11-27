package controller;

import controller.WSproducto.DataProducto;
import controller.WSproducto.DataReclamo;
import controller.WSproducto.ProductoException_Exception;
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
 * @author K
 */
@WebServlet(name = "GetJSONreclamos", urlPatterns = {"/GetJSONreclamos"})
public class GetJSONreclamos extends HttpServlet {

    private static final long serialVersionUID = 9003070782545675578L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String provNick = request.getSession().getAttribute("usuario").toString();
            //String provNick = "tim1";
            List<DataProducto> ldp = getProductListPorProveedor(provNick);
            JSONArray jsArrayReclamos = new JSONArray();
            int reclamoId = 1;

            for (DataProducto dp : ldp) {
                JSONObject nodo = new JSONObject();
                nodo.put("id", dp.getReferencia());
                nodo.put("text", "<div class=\"comment-node producto-reclamo\"><strong>" + 
                        "Producto: " + dp.getNombre() + "</strong><br/></div>");
                nodo.put("parent", "#");
                jsArrayReclamos.add(nodo);

                List<DataReclamo> reclamos = getReclamosPorProducto(dp.getReferencia());

                if (!reclamos.isEmpty()) {
                    for (DataReclamo dr : reclamos) {
                        JSONObject nodoR = new JSONObject();
                        nodoR.put("id", "reclamo" + reclamoId);
                        nodoR.put("text", "<div class=\"comment-node\"><strong>" + 
                                dr.getNickCliente() + 
                                " dijo el " + 
                                sdf.format(controller.util.gregorianTOdate(dr.getFechaReclamo())) + 
                                ": </strong><br/>" + dr.getTextoRec() + "</div>");
                        //nodoR.put("text", dr.getNickCliente() + " dijo el " + sdf.format(dr.getFechaReclamo()) + ": <br/>" + dr.getTextoRec());
                        nodoR.put("parent", dp.getReferencia());
                        jsArrayReclamos.add(nodoR);
                        reclamoId++;
                    }
                } else {
                    JSONObject sinReclamos = new JSONObject();
                    sinReclamos.put("id", dp.getReferencia() + "sinReclamos");
                    sinReclamos.put("text", "<div class=\"comment-node\">" + "Este producto no tiene reclamos." + "</div>");
                    sinReclamos.put("parent", dp.getReferencia());
                    jsArrayReclamos.add(sinReclamos);
                }
                reclamoId++;
            }
            out.print(jsArrayReclamos.toJSONString().toString());

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

    private static java.util.List<controller.WSproducto.DataProducto> getProductListPorProveedor(java.lang.String arg0) throws MalformedURLException {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service(new URL(Configuracion.getProperty("wsdlProducto")));
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.getProductListPorProveedor(arg0);
    }

    private static java.util.List<controller.WSproducto.DataReclamo> getReclamosPorProducto(java.lang.String arg0) throws ProductoException_Exception, MalformedURLException {
        controller.WSproducto.ProductoWS_Service service = new controller.WSproducto.ProductoWS_Service(new URL(Configuracion.getProperty("wsdlProducto")));
        controller.WSproducto.ProductoWS port = service.getProductoWSPort();
        return port.getReclamosPorProducto(arg0);
    }
}