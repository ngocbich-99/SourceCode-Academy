package com.example.demo.service;

import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.BizException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static com.example.demo.constant.CustomerConstant.CUSTOMER_PATH;

@Service
public class FileServiceImpl implements FileService {

    private final Path root = Paths.get(CUSTOMER_PATH);

    @Override
    public String save(MultipartFile file){
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String name = FilenameUtils.getBaseName(file.getOriginalFilename());
            String fileName = name.concat("_").concat(String.valueOf(new Date().getTime())).concat(".").concat(extension);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            System.out.println(CUSTOMER_PATH.concat(fileName));
            Files.copy(file.getInputStream(), root.resolve(fileName));
            return fileName;
        } catch (Exception e) {
            throw new BizException(ResponseEnum.IO,"Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource download(String filesPath) {
        try {
            Path file = Paths.get("var/1635410_1641220729486.jpg");
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new BizException(ResponseEnum.NOT_FOUND,"Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
