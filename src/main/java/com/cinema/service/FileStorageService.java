package com.cinema.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface FileStorageService {

    String storeFile(MultipartFile file);

    Resource loadFile(String fileName);
}