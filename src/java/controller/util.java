/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
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
        while ((qt = imgIS.read(buf)) != -1) {
            imgBAOS.write(buf, 0, qt);
        }
        return imgBAOS.toByteArray();
    }

    static public byte[] imgToBytes(File fnew) throws IOException {
        //File fnew=new File(dir);
        BufferedImage originalImage = ImageIO.read(fnew);
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        //
        String name = fnew.getName();
        int pos = name.lastIndexOf('.');
        String ext = name.substring(pos + 1);
        //
        ImageIO.write(originalImage, ext, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }

    static public File getFotoEstandar(String ima) throws IOException {
        String path = new File(".").getCanonicalPath();
        File foo = new File(path + "/Recursos/" + ima);
        return foo;
    }

    static public XMLGregorianCalendar dateTOgregorian(Date fechaDate) throws DatatypeConfigurationException {
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(fechaDate);
        XMLGregorianCalendar fechaReturn = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);
        return fechaReturn;
    }
}
