package com.education.classroom.utils.classroom.upload;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class UploadUtil {
  //    private static final Logger log = LoggerFactory.getLogger(UploadUtil.class);
    private static FtpUtil ftpUtil;
    private static final String IMG_ROOT = "image/";
    private static final String DOC_ROOT = "docs/";
    //private static final String THUMB_ROOT = "image/thumb/";
    //private static final String[] ACCEPT_EXT_ARRAY = {"jpg", "gif", "png", "bmp", "jpeg"};
    private static final String  ACCEPT_EXT_AR = "jpg;gif;png;bmp;jpeg";
    static {
        ftpUtil = FtpUtil.INSTANCE;
    }

    public static UploadResult uploadImage(InputStream src, String extName, boolean async) {
        try {    //Arrays.binarySearch(ACCEPT_EXT_ARRAY, extName.toLowerCase()) < 0
        	String pathUrl = IMG_ROOT;
            if (StringUtils.isEmpty(extName) || ACCEPT_EXT_AR.indexOf(extName.toLowerCase()) == -1) {
               // return UploadResult.fail("文件格式不正确");
            	pathUrl = DOC_ROOT;
            }
            List<FtpUploadBean> files = new ArrayList<FtpUploadBean>();
            byte[] data = IOUtils.toByteArray(src);
            // 服务器存储路径生成
            String srcName = UUID.randomUUID().toString().replaceAll("\\-", "");
            String srcFullName = srcName + "." + extName;
            String thumbFullName = srcName + "." + extName;//".jpg";
            // 上传原图
            files.add(new FtpUploadBean(pathUrl + srcFullName, new ByteArrayInputStream(data)));
            // 压缩
           /* InputStream thumb = ImageUtil.compressWithSize(new ByteArrayInputStream(data), 300, 300, 1f);
            // 上传缩略图
            files.add(new FtpUploadBean(THUMB_ROOT + thumbFullName, thumb));*/
            ftpUtil.upload(files, async);
            return UploadResult.success(srcFullName, thumbFullName);
        } catch (Exception e) {
            e.printStackTrace();
//            log.error("上传图片出错", e);
        }
        return UploadResult.fail("上传图片失败");
    }

    public static void main(String[] args) throws FileNotFoundException {
        uploadImage(new FileInputStream("D:\\Desert.jpg"), "png", true);
    }

    public static class UploadResult {
        public final boolean isSuccess;
        public final String srcName;
        public final String thumbName;
        public final String msg;

        public UploadResult(boolean isSuccess, String srcName, String thumbName, String msg) {
            this.isSuccess = isSuccess;
            this.srcName = srcName;
            this.thumbName = thumbName;
            this.msg = msg;
        }


        public static UploadResult success(String srcName, String thumbName) {
            return new UploadResult(true, srcName, thumbName, null);
        }

        public static UploadResult fail(String msg) {
            return new UploadResult(false, null, null, msg);
        }
    }
}
