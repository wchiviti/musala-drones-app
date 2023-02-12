package io.github.hobbstech.filemanagement.context;

import org.springframework.core.io.Resource;

@FunctionalInterface
public interface FileDownloadService {
    Resource downloadFile(String fileName);
}
