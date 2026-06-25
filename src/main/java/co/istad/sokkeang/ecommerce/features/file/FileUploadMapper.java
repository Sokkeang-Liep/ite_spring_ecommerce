package co.istad.sokkeang.ecommerce.features.file;

import co.istad.sokkeang.ecommerce.features.file.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

/**
 * Create mapper using class not interface because, We want to manually
 * custom instead of helping from abstract moreover it has only one don't have target and source
 */
public class FileUploadMapper {

    @Value("${file.base-uri}")
    private String baseUri;

    public FileUploadResponse mapFileUploadToFileUploadResponse(FileUpload fileUpload) {
        return FileUploadResponse.builder()
                .name(fileUpload.getName())
                .extension(fileUpload.getExtension())
                .size(fileUpload.getSize())
                .mediaType(fileUpload.getMediaType())
                .uri(baseUri + fileUpload.getName() + "." + fileUpload.getExtension())
                .build();
    }
}
