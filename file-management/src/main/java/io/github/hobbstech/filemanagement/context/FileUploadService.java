package io.github.hobbstech.filemanagement.context;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUploadResponse upload(MultipartFile fileUploadContext);
}
