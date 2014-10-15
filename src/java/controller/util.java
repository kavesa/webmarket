/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

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
}
