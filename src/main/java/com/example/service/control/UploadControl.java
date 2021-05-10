package com.example.service.control;

import com.example.service.dto.Response;
import com.example.service.entity.Image;
import com.example.service.repository.ImageRepository;
import com.example.service.service.CloudinaryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by NhanNguyen on 5/8/2021
 *
 * @author: NhanNguyen
 * @date: 5/8/2021
 */
@RestController
@RequestMapping("/cloudinary")
@CrossOrigin("*")
public class UploadControl {
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/list")
    public ResponseEntity<Map> getList() {
        List<Image> lst = (List<Image>) imageRepository.findAll();
        return new ResponseEntity(new Response("success", lst), HttpStatus.OK);

    }

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (Objects.isNull(bi)) {
            return new ResponseEntity(new Response("image invalid"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image();
        image.setName(String.valueOf(result.get("original_filename")));
        image.setImageUrl(String.valueOf(result.get("url")));
        image.setImageId(String.valueOf(result.get("public_id")));
        imageRepository.save(image);
        return new ResponseEntity(new Response("success",result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> upload(@PathVariable("id") int id) throws IOException {
        Image image = imageRepository.findById(id).orElse(null);
        if (Objects.isNull(image)) {
            return new ResponseEntity(new Response("Not_Found"), HttpStatus.NOT_FOUND);
        }
        cloudinaryService.delete(image.getImageId());
        imageRepository.deleteById(id);
        return new ResponseEntity(new Response("success"), HttpStatus.OK);
    }
}
