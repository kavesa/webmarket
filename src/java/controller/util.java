/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

/**
 *
 * @author nightmare
 */
public class util {

    static public String byteImgToBase64(byte[] imgByte) {
        StringBuilder sb = new StringBuilder();
        sb.append("data:image/png;base64,");
        sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(imgByte, false)));
        String contourChart = sb.toString();
        return contourChart;
    }

    static public byte[] InputStreamToByteArray(InputStream imgIS) throws IOException {
            InputStream fotoProd1 = imgIS;
            ByteArrayOutputStream imgBAOS = new ByteArrayOutputStream();
            byte[] buf = new byte[10000];
            int qt;
            while ((qt = imgIS.read(buf)) != -1){
                imgBAOS.write(buf, 0, qt);
            }
            return imgBAOS.toByteArray();
    }
}
