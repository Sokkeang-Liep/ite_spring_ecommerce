package co.istad.sokkeang.ecommerce.features.file;

import co.istad.sokkeang.ecommerce.features.file.dto.FileUploadResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileUploadService {
    /**
     * Upload multiple files
     * @param files is a list for multiple upload
     * @return list of {@link FileUploadResponse }
     */
    List<FileUploadResponse> uploadMultipart(List<MultipartFile> files);

    FileUploadResponse upload(MultipartFile file);

    void deleteByName(String name);

    Page<FileUploadResponse> findAll(int pageNumber, int pageSize);

    FileUploadResponse findByName (String name);
}
