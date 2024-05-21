package com.aaron.fileassistant.service;

import com.aaron.fileassistant.common.utils.FileUtils;
import com.aaron.fileassistant.model.dto.TransferFolderDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileTransferService {

    public List<TransferFolderDto> getEFile(String path) throws IOException {
        List<TransferFolderDto> result = new ArrayList<>();
        File file = new File(path);
        if (file.isFile()) {

            TransferFolderDto transferFolderDto = new TransferFolderDto();
            transferFolderDto.setFile(file);
            transferFolderDto.setPath(path);
            transferFolderDto.setType(1);
            transferFolderDto.setFileName(file.getName());
            if (FileUtils.isImgFile(file)) {
                transferFolderDto.setPreviewContent(FileUtils.fileToBase64(file));
            }

            result.add(transferFolderDto);
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            int i = 0;
            if (files != null) {
                for (File f : files) {
                    System.out.println(f.getName()); // 输出文件名
                    TransferFolderDto transferFolderDto = new TransferFolderDto();
                    transferFolderDto.setFile(f);
                    transferFolderDto.setPath(path);
                    transferFolderDto.setType(1);
                    transferFolderDto.setFileName(f.getName());
                    if (FileUtils.isImgFile(f)) {
                        transferFolderDto.setPreviewContent(FileUtils.fileToBase64(f));
                    }
                    result.add(transferFolderDto);
                    i ++;
                    System.out.println(i);
                    if (i >= 20) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public List<TransferFolderDto> getFolderStructure(String path) {
        List<TransferFolderDto> result = new ArrayList<>();
        File file = new File(path);
        if (file.isFile()) {
            result.add(getTransferFolderDto(file));
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                result.add(getTransferFolderDto(f));
            }
        }
        return result;
    }

    private TransferFolderDto getTransferFolderDto(File file) {
        if (file == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        TransferFolderDto transferFolderDto = new TransferFolderDto();
        transferFolderDto.setType(1);
        transferFolderDto.setFileName(file.getName());
        transferFolderDto.setMedia(FileUtils.isMedia(file));
        transferFolderDto.setFileSize(FileUtils.formatFileSize(file.length()));
        transferFolderDto.setFileRealSize(file.length());
        transferFolderDto.setLastModified(file.lastModified());
        transferFolderDto.setUpdateDate(sdf.format(new Date(file.lastModified())));

        return transferFolderDto;
    }
}
