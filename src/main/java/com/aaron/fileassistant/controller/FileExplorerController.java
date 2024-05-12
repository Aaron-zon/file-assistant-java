package com.aaron.fileassistant.controller;

import com.aaron.fileassistant.common.utils.Response;
import com.aaron.fileassistant.model.dto.TransferFolderDto;
import com.aaron.fileassistant.service.FileTransferService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;

@RestController
public class FileExplorerController {
    private static final Logger logger = LoggerFactory.getLogger(FileExplorerController.class);

    @Autowired
    FileTransferService fileTransferService;

    String path = "E:\\file\\壁纸";

    @GetMapping("getEFile")
    public Response getEFile() {
        try {
            List<TransferFolderDto> eFile = fileTransferService.getEFile(path);
            return Response.ok(eFile);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return Response.failed(e.getMessage());
        }

    }

    @GetMapping("getFolderStructure")
    public Response getFolderStructure() {
        try {
            List<TransferFolderDto> eFile = fileTransferService.getFolderStructure(path);
            return Response.ok(eFile);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return Response.failed(e.getMessage());
        }
    }
}
