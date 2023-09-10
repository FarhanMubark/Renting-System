package com.example.rentingsystem.Service;

import com.example.rentingsystem.Api.ApiException;
import com.example.rentingsystem.Model.ImageData;
import com.example.rentingsystem.Model.Product;
import com.example.rentingsystem.Repository.ImageDataRepository;
import com.example.rentingsystem.Repository.ProductRepository;
import com.example.rentingsystem.Util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageDataService {

   private final ImageDataRepository imageDataRepository;

   private final ProductRepository productRepository;

    public String uploadImage(MultipartFile file, Integer product_id) throws IOException {
        Product product =  productRepository.findProductById(product_id);
        if (product == null){
            return "Product Not Found";
        }
        ImageData data = new ImageData();
        data.setProduct_id(product_id);
        ImageData imageData = imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).product_id(product_id).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }

        return "Some Thing went wrong !";


    }

    public byte[] downloadImage(String fileName, Integer product_id){
        Product product = productRepository.findProductById(product_id);
        if (product == null) {
                throw new ApiException("Product Id Not Found");
        }
        Optional<ImageData> dbImageData = Optional.ofNullable(imageDataRepository.findByName(fileName));
        byte[] images=ImageUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public String deleteImage(String fileName) {
        Optional<ImageData> dbImageData = Optional.ofNullable(imageDataRepository.findByName(fileName));
        if (dbImageData.isPresent()) {
            imageDataRepository.delete(dbImageData.get());
            return "Image deleted successfully: " + fileName;
        } else {
            return "Image Not Found";
        }
    }
}
