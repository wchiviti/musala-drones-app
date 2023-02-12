package io.github.hobbstech.filemanagement.context;

import io.github.hobbstech.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
class FileUploadServiceImpl implements FileUploadService {
    private final FileStorageService fileStorageService;

    @Value("${drones-app.file-storage.file-types}")
    private String fileTypes;

    private String validateFileType(MultipartFile file) {

        if (isNull(file)) {
            throw new InvalidRequestException("File  cannot be null");
        }

        val originalFileName = file.getOriginalFilename();

        if (isNull(originalFileName)) {
            throw new InvalidRequestException("Client not supported in uploading files");
        }

        final String fileName = StringUtils.cleanPath(originalFileName);

        for (String fileNameExtension : getFileTypes()) {
            if (fileName.endsWith(fileNameExtension)) {
                return fileNameExtension;
            }
        }

        throw new InvalidRequestException("File type is not accepted");
    }

    private String storeFileOnFileServer(MultipartFile file) {
        if (isNull(file)) {
            throw new InvalidRequestException("File  cannot be null");
        }
        return fileStorageService.storeFile(file, file.getOriginalFilename());
    }


    @Override
    public FileUploadResponse upload(MultipartFile multipartFile) {

        if (isNull(multipartFile))
            throw new InvalidRequestException("File must be provided");

        val fileType = validateFileType(multipartFile);

        String fileName = storeFileOnFileServer(multipartFile);

        return new FileUploadResponse(fileName);
    }


    public List<String> getFileTypes() {
        val temp = Arrays.asList(fileTypes.split(","));
        val types = new ArrayList<>(temp);
        types.addAll(temp.stream().map(String::toUpperCase).toList());
        return types;
    }
}
