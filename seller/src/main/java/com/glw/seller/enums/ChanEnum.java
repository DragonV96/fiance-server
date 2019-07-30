package com.glw.seller.enums;

/**
 * @author : glw
 * @date : 2019/7/30
 * @time : 14:12
 * @Description : 渠道配置信息
 */
public enum ChanEnum {
    ABC("111", "ABC", "/opt/ABC");

    private String chanId;
    private String chanName;

    private String ftpPath, ftpUser, ftpPwd;

    private String rootDir;

    ChanEnum(String chanId, String chanName, String rootDir) {
        this.chanId = chanId;
        this.chanName = chanName;
        this.rootDir = rootDir;
    }

    /**
     * 根据渠道编号获取渠道配置
     * @param chanId
     * @return
     */
    public static ChanEnum getByChanId(String chanId) {
        for (ChanEnum chanEnum : ChanEnum.values()) {
            if (chanEnum.getChanId().equals(chanId)) {
                return chanEnum;
            }
        }
        return null;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    public String getFtpPath() {
        return ftpPath;
    }

    public void setFtpPath(String ftpPath) {
        this.ftpPath = ftpPath;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPwd() {
        return ftpPwd;
    }

    public void setFtpPwd(String ftpPwd) {
        this.ftpPwd = ftpPwd;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }
}
