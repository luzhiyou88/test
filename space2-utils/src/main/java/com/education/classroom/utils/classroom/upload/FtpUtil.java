package com.education.classroom.utils.classroom.upload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public enum FtpUtil {
    INSTANCE;
    private String ftpHost;
    private int ftpPort;
    private String username;
    private String password;

    private FTPClient ftp;
    private ExecutorService pool = Executors.newCachedThreadPool();
     
    FtpUtil() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("/ftp.properties"));
            ftpHost = prop.getProperty("host");
            ftpPort = Integer.parseInt(prop.getProperty("port", "21"));
            username = prop.getProperty("username", "anonymous");
            password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object execute(final FtpCommand command) {
        final Object[] result = {null};
        try {
            before();
            if (command.async()) {
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            result[0] = command.action(ftp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            after();
                        }
                    }
                });
            } else {
                result[0] = command.action(ftp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result[0];
    }

    private void before() throws IOException {
        ftp = new FTPClient();
        ftp.connect(ftpHost, ftpPort);
        ftp.login(username, password);
        int replyCode = ftp.getReplyCode();
        if (FTPReply.isPositiveCompletion(replyCode)) {
            System.out.printf("连接FTP成功[%s@%s]\n", username, ftpHost);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        } else {
            throw new RuntimeException("连接FTP失败");
        }
    }


    private void after() {
        if (ftp != null && ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
                System.out.printf("断开FTP连接\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upload(final List<FtpUploadBean> files, final boolean async) {
        execute(new FtpCommand() {
            public Object action(FTPClient ftp) throws Exception {
                for (FtpUploadBean file : files) {
                    String head = "";
                    String tail = file.getRemote();
                    int i;
                    while ((i = tail.indexOf("/")) > 0) {
                        head += tail.substring(0, i + 1);
                        ftp.makeDirectory(head);
                        tail = tail.substring(i + 1, tail.length());
                    }
                    System.out.printf("正在上传 [%s] \n", file.getRemote());
                    boolean b = ftp.storeFile(file.getRemote(), file.getLocal());
                    if (b) {
                        System.out.printf("上传成功 [%s] \n", file.getRemote());
                    } else {
                        System.out.printf("上传失败 [%s] \n", file.getRemote());
                    }
                }
                return null;
            }

            @Override
            public boolean async() {
                return async;
            }
        });
    }

    public interface FtpCommand {
        Object action(FTPClient ftp) throws Exception;
        boolean async();
    }

    public static void main(String[] args) throws FileNotFoundException {
        FtpUtil ftpUtil = FtpUtil.INSTANCE;
        List<FtpUploadBean> files = new ArrayList<FtpUploadBean>();
        files.add(new FtpUploadBean("image/a.docx", new FileInputStream("D:\\山东品维项目验收报告.docx")));
        ftpUtil.upload(files, false);
    	
    	
    	
    }
}
