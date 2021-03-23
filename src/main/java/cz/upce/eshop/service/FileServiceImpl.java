package cz.upce.eshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile image) {
        try {
            String imageDir = "D:\\Dokumenty\\Skola\\UPCE\\Magisterské studium\\1. Prvák\\NNPIA (Programování internetových aplikací)\\Cvičení\\5\\eshop\\images\\";

            Path path = Paths.get(imageDir + image.getOriginalFilename());
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image.getOriginalFilename();
    }
}
