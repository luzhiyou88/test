package com.education.classroom.utils.classroom.upload;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {

    public static InputStream compressWithSize(InputStream src, int w, int h, float quality) throws IOException {
        BufferedImage srcImg = ImageIO.read(src);
        int width = srcImg.getWidth();
        int height = srcImg.getHeight();
        int newHeight = h;
        int tmpH = w * height / width;
        if (tmpH > h) {
            newHeight = tmpH;
        }
        BufferedImage thumbnail = new BufferedImage(w, newHeight, BufferedImage.TYPE_INT_RGB);
        thumbnail.getGraphics().drawImage(srcImg, 0, 0, w, newHeight, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /*ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        ImageIO.write(thumbnail, "jpg", ios);*/
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(thumbnail);
        jep.setQuality(quality, true);
        encoder.encode(thumbnail, jep);
        baos.close();
      
	
        
        return new ByteArrayInputStream(baos.toByteArray());
    }
    /**
     * 压缩图片
     * @param src 原图片
     * @param ratio 压缩比例 0~1
     * @param quality jpg质量
     * @return
     * @throws IOException
     */
    public static InputStream compress(InputStream src, double ratio, float quality) throws IOException {
        BufferedImage srcImg = ImageIO.read(src);
        int width = srcImg.getWidth();
        int height = srcImg.getHeight();
        int newWidth = (int) (width * ratio);
        int newHeight = (int) (height * ratio);
        return compressWithSize(src, newWidth, newHeight, quality);
    }

    public static void main(String[] args) throws IOException {
        String src = "C:\\Users\\user01\\Desktop\\c.png";
        BufferedImage image = ImageIO.read(compress(new FileInputStream(src), 0.5, 1f));
        ImageIO.write(image, "jpg", new File("D:\\temp\\" + UUID.randomUUID().toString() + ".jpg"));
    }
}
