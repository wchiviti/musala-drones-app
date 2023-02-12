package io.github.hobbstech.filemanagement.context;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;


@Component
class LocalFileStorageServiceImpl implements FileStorageService {

    private final FileStorageProperties fileStorageProperties;

    public LocalFileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    @Override
    public String storeFile(final MultipartFile file, String originalFileName) {

        requireNonNull(file, "Uploaded file can not be null");

        val ogFileName = nonNull(file.getOriginalFilename()) ? file.getOriginalFilename() : originalFileName;

        String fileName = StringUtils.cleanPath(ogFileName);

        checkIfFileNameContainsInvalidCharacters(fileName);

        fileName = generateUniqueFileName(fileName);

        Path targetLocation = getFileStorageLocation().resolve(fileName);

        createDirectoriesIfThereDoNotExist(getFileStorageLocation());

        this.persistFile(file, targetLocation);

        return fileName;

    }

    @Override
    public boolean deleteFile(String fileName) {
        Path filePath = getFileStorageLocation().resolve(fileName).normalize();
        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to delete file : " + e.getMessage());
        }
    }

    private void persistFile(MultipartFile file, Path path) {

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not store file " + file.getName() + ". Please try again!");
        }

    }

    static String generateUniqueFileName(String fileName) {
        if (fileName.lastIndexOf('.') < 0) {
            return UUID.randomUUID().toString();
        }
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        return UUID.randomUUID().toString() + fileExtension;
    }


    static void checkIfFileNameContainsInvalidCharacters(String fileName) {
        if (fileName.contains("..")) {
            throw new IllegalStateException("Filename contains invalid path sequence " + fileName);
        }
    }

    @Override
    public Path getFileStorageLocation() {
        return Paths.get(this.fileStorageProperties.getUploadRoot())
                .toAbsolutePath().normalize();
    }

    static void createDirectoriesIfThereDoNotExist(Path fileStorageLocation) {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new IllegalStateException("Could not create the directory where the uploaded files will be stored." + ex);
        }

    }

    @Override
    public boolean fileExists(String fileName) {
        val filePath = getFileStorageLocation().resolve(fileName).normalize();
        return Files.exists(filePath);
    }

    @Override
    public boolean isRemoteStorage() {
        return false;
    }

    @SneakyThrows(value = IOException.class)
    @Override
    public InputStream downloadFile(String fileName) {
        Path file = Paths.get(fileStorageProperties.getUploadRoot(),fileName)
                .toAbsolutePath()
                .normalize();
        return new FileInputStream(file.toFile());
    }


}
