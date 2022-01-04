package com.example.demo.controller;

import com.example.demo.model.response.CloudFileResponse;
import com.example.demo.model.response.CloudResponse;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;


    @PostMapping
    public CloudResponse<String> saveFile(@RequestParam("file") MultipartFile file) {
        return CloudResponse.ok(fileService.save(file));
    }

    @GetMapping
    public ResponseEntity<CloudFileResponse<String>> getFile(@RequestParam("filePath") String filePath) throws IOException {
        try {
            Resource file = fileService.download(filePath);
            Path path = file.getFile().toPath();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(CloudFileResponse.ok("Ok",file));
//            return new CloudFileResponse().setFile(file).setData(CloudResponse.ok("OK")).build();
//            return ResponseEntity.ok()
////                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
//                    .header(
//                            HttpHeaders.CONTENT_DISPOSITION,
//                            "attachment; filename=\"" + file.getFilename() + "\"")
//                    .body(CloudResponse.ok(file));
//            return CloudResponse.ok(file);

        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
            return ResponseEntity.ok(CloudFileResponse.ok("Bad", null));
//            return CloudResponse.ok("Faild");
        }
    }
}
