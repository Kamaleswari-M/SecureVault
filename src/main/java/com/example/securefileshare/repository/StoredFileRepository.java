package com.example.securefileshare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securefileshare.entity.StoredFile;

public interface StoredFileRepository extends JpaRepository<StoredFile, Long> {

    List<StoredFile> findByUsername(String username);

    Optional<StoredFile> findByFileName(String fileName);

    Optional<StoredFile> findByFileNameAndUsername(String fileName,
                                                   String username);
}