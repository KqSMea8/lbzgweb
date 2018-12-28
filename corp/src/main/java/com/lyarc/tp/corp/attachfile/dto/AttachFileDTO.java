package com.lyarc.tp.corp.attachfile.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 附件
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月31日 14:48:52
 */
public class AttachFileDTO {

    /**
     * 附件id(可以为GUID)
     */
    private String fileId;
    /**
     * 所属模块
     */
    private String module;
    /**
     * 对象标识（记录的主键）
     */
    private String objId;
    /**
     * 附件标题
     */
    private String title;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件扩展名
     */
    private String fileNameExt;
    /**
     * 文件大小(字节)
     */
    private Integer fileSize;
    /**
     * 文件md5值
     */
    private String fileMd5;
    /**
     * 文件分类
     */
    private String fileCatalog;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", locale = "zh")
    private LocalDateTime createTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameExt() {
        return fileNameExt;
    }

    public void setFileNameExt(String fileNameExt) {
        this.fileNameExt = fileNameExt;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFileCatalog() {
        return fileCatalog;
    }

    public void setFileCatalog(String fileCatalog) {
        this.fileCatalog = fileCatalog;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AttachFileDTO{" + "fileId='" + fileId + '\'' + ", module='" + module + '\'' + ", objId='" + objId + '\''
                + ", title='" + title + '\'' + ", fileName='" + fileName + '\''
                + ", fileNameExt='" + fileNameExt + '\'' + ", fileSize=" + fileSize + ", fileMd5='" + fileMd5 + '\''
                + ", fileCatalog='" + fileCatalog + '\'' + ", createTime=" + createTime + '}';
    }
}
