package com.aaron.fileassistant.model.dto;

import lombok.Data;

import java.io.File;
import java.util.Date;

@Data
public class TransferFolderDto {

    private String id;
    // 路径
    private String path;
    // 文件类型 0: 文件夹 1：文件
    private Integer type;
    // 文件
    private File file;
    // 文件名
    private String fileName;
    // 文件大小 格式化后
    private String fileSize;
    // 文件大小
    private Long fileRealSize;
    // 最后修改时间
    private Long lastModified;
    // 最后修改时间 格式化后
    private String updateDate;
    // 预览
    private String previewContent;
    // 是否为媒体文件
    private boolean isMedia;

}
