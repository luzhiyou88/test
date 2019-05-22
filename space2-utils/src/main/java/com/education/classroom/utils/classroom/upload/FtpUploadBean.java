package com.education.classroom.utils.classroom.upload;

import java.io.InputStream;

public class FtpUploadBean {
    private String remote;
    private InputStream local;

    public FtpUploadBean() {
    }

    public FtpUploadBean(String remote, InputStream local) {
        this.remote = remote;
        this.local = local;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public InputStream getLocal() {
        return local;
    }

    public void setLocal(InputStream local) {
        this.local = local;
    }
}
