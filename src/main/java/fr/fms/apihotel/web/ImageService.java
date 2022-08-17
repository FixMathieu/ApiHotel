package fr.fms.apihotel.web;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public void init();
    public void save(MultipartFile file);

}
