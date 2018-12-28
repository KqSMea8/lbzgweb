package com.lyarc.tp.corp.attachfile.bean;

import com.googlecode.jmapper.annotations.JMap;
import com.lyarc.tp.corp.attachfile.dto.AttachFileDTO;

import java.time.LocalDateTime;

/**
 * 附件
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月31日 14:48:25
 */
public class AttachFileBean {
    /**
     * 附件id(可以为GUID)
     */
    @JMap(attributes = {"fileId"}, classes = {AttachFileDTO.class})
    private String fileId;
    /**
     * 所属模块
     */
    @JMap(attributes = {"module"}, classes = {AttachFileDTO.class})
    private String module;
    /**
     * 对象标识（记录的主键）
     */
    @JMap(attributes = {"objId"}, classes = {AttachFileDTO.class})
    private String objId;
    /**
     * 附件标题
     */
    @JMap(attributes = {"title"}, classes = {AttachFileDTO.class})
    private String title;
    /**
     * 文件名
     */
    @JMap(attributes = {"fileName"}, classes = {AttachFileDTO.class})
    private String fileName;
    /**
     * 文件扩展名
     */
    @JMap(attributes = {"fileNameExt"}, classes = {AttachFileDTO.class})
    private String fileNameExt;
    /**
     * 文件大小(字节)
     */
    @JMap(attributes = {"fileSize"}, classes = {AttachFileDTO.class})
    private Integer fileSize;
    /**
     * 文件md5值
     */
    @JMap(attributes = {"fileMd5"}, classes = {AttachFileDTO.class})
    private String fileMd5;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件分类
     */
    @JMap(attributes = {"fileCatalog"}, classes = {AttachFileDTO.class})
    private String fileCatalog;
    /**
     * 删除文件(0 未删除,1 已删除)
     */
    private Boolean deleted;
    /**
     * 删除时间
     */
    private LocalDateTime deleteTime;
    /**
     * 删除人
     */
    private String deleter;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    @JMap(attributes = {"createTime"}, classes = {AttachFileDTO.class})
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime lastUpdate;
    /**
     * 时间戳
     */
    private LocalDateTime tmstamp;

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileCatalog() {
        return fileCatalog;
    }

    public void setFileCatalog(String fileCatalog) {
        this.fileCatalog = fileCatalog;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleter() {
        return deleter;
    }

    public void setDeleter(String deleter) {
        this.deleter = deleter;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(LocalDateTime tmstamp) {
        this.tmstamp = tmstamp;
    }

    @Override
    public String toString() {
        return "AttachFileBean{" + "fileId='" + fileId + '\'' + ", module='" + module + '\'' + ", objId='" + objId
                 + '\'' + ", title='" + title + '\'' + ", fileName='" + fileName + '\''
                + ", fileNameExt='" + fileNameExt + '\'' + ", fileSize=" + fileSize + ", fileMd5='" + fileMd5 + '\''
                + ", filePath='" + filePath + '\'' + ", fileCatalog='" + fileCatalog + '\'' + ", deleted=" + deleted
                + ", deleteTime=" + deleteTime + ", deleter='" + deleter + '\'' + ", creator='" + creator + '\''
                + ", createTime=" + createTime + ", lastUpdate=" + lastUpdate + ", tmstamp=" + tmstamp + '}';
    }
}
