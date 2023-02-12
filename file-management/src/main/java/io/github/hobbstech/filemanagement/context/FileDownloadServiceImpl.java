package io.github.hobbstech.filemanagement.context;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
class FileDownloadServiceImpl implements FileDownloadService {

    private final FileStorageService fileStorageService;

    @Override
    public Resource downloadFile(String fileName) {
        val inputStream = fileStorageService.downloadFile(fileName);
        return new InputStreamResource(inputStream);
    }

}
