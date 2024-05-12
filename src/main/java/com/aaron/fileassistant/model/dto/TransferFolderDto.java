package com.aaron.fileassistant.model.dto;

import lombok.Data;

import java.io.File;

@Data
public class TransferFolderDto {
    private String id;
    private String path;
    // 0: 文件夹 1：文件
    private int type;
    private File file;
    private String fileName;
    private String previewContent;
    private boolean isMedia;
}
