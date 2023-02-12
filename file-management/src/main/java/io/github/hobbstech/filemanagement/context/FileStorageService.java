package io.github.hobbstech.filemanagement.context;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;

public interface FileStorageService {

    String storeFile(MultipartFile file, String originalFileName);

    boolean deleteFile(String fileName);

    Path getFileStorageLocation();

    boolean fileExists(String fileName);

    boolean isRemoteStorage();

    InputStream downloadFile(String fileName);

}
