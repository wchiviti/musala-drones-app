package io.github.hobbstech.filemanagement.context;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Data
public class FileStorageProperties {

    @Value("${drones-app.file-storage.directory}")
    private String uploadRoot;

}
