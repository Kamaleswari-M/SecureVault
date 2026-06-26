
package com.example.securefileshare.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.securefileshare.entity.StoredFile;
import com.example.securefileshare.repository.StoredFileRepository;
import com.example.securefileshare.security.AESUtil;

@Service
public class FileService {

    private final String UPLOAD_DIR = "uploads/";

    private final StoredFileRepository storedFileRepository;

    public FileService(StoredFileRepository storedFileRepository) {
        this.storedFileRepository = storedFileRepository;
    }

    // Save encrypted file and owner details
    public void saveFile(MultipartFile file, String username) throws Exception {

        Path path = Paths.get(UPLOAD_DIR);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        byte[] fileBytes = file.getBytes();

        byte[] encryptedBytes = AESUtil.encrypt(fileBytes);

        Files.write(
                path.resolve(file.getOriginalFilename()),
                encryptedBytes);

        StoredFile storedFile =
                new StoredFile(file.getOriginalFilename(), username);

        storedFileRepository.save(storedFile);
    }

    // Return only files belonging to the logged-in user
    public List<String> getAllFiles(String username) {

        return storedFileRepository.findByUsername(username)
                .stream()
                .map(StoredFile::getFileName)
                .collect(Collectors.toList());
    }

    // Decrypt file for download
    public byte[] getDecryptedFile(String filename) throws Exception {

        Path path = Paths.get(UPLOAD_DIR).resolve(filename);

        byte[] encryptedBytes = Files.readAllBytes(path);

        return AESUtil.decrypt(encryptedBytes);
    }

    // Dashboard statistics
    public long getFileCount() {
        return storedFileRepository.count();
    }
    public boolean isOwner(String filename, String username) {

    return storedFileRepository
            .findByFileNameAndUsername(filename, username)
            .isPresent();
}
}
