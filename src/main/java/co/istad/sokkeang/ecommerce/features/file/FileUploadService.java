package co.istad.sokkeang.ecommerce.features.file;

import co.istad.sokkeang.ecommerce.features.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileUploadService {
    FileUploadResponse upload(MultipartFile file);

    List<FileUploadResponse> upload(List<MultipartFile> files);
}
