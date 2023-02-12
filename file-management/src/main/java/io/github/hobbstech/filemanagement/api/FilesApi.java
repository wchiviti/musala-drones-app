package io.github.hobbstech.filemanagement.api;

import io.github.hobbstech.filemanagement.context.FileDownloadService;
import io.github.hobbstech.filemanagement.context.FileUploadResponse;
import io.github.hobbstech.filemanagement.context.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "Files")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FilesApi {

    private final FileDownloadService fileService;

    private final FileUploadService fileUploadService;

    @GetMapping("/opn/v1/files")
    @Operation(summary = "Download File")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        val resource = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(resource);
    }

    @PostMapping(value = "/cmn/v1/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload File")
    public FileUploadResponse upload(@RequestParam("file") MultipartFile file) {
        return fileUploadService.upload(file);
    }

}
