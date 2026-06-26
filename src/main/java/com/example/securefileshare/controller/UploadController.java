package com.example.securefileshare.controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.securefileshare.service.FileService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {

    private final FileService fileService;

    public UploadController(FileService fileService) {
        this.fileService = fileService;
    }

   @PostMapping("/upload")
public String uploadFile(@RequestParam("file") MultipartFile file,
                         HttpSession session)
        throws Exception {

    String username = (String) session.getAttribute("username");

    fileService.saveFile(file, username);

    return "redirect:/dashboard";
}
    @GetMapping("/download/{filename}")
public ResponseEntity<byte[]> downloadFile(@PathVariable String filename,
                                           HttpSession session)
        throws Exception {

    String username = (String) session.getAttribute("username");

    if (!fileService.isOwner(filename, username)) {

        return ResponseEntity.status(403).build();

    }

    byte[] file = fileService.getDecryptedFile(filename);

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + filename + "\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(file);
}

}