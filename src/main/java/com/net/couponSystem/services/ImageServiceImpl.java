package com.net.couponSystem.services;

import com.net.couponSystem.beans.Image;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.repos.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public UUID addImage(byte[] bytes) throws Exception {
        Image image = null;
        UUID uuid = null;
        try {

            image = new Image(bytes);
            uuid = imageRepository.save(image).getId();
        } catch (Exception e) {
            throw new Exception("something went wrong...");
        }
        System.out.println("add image coupon uuid: "+uuid);
        return uuid;
    }
    @Override
    public Image getImage(UUID uuid) throws Exception {
        if(!imageRepository.existsById(uuid)){
            throw new Exception("something went wrong...");
        }
        System.out.println("get image coupon uuid: "+uuid);
        return imageRepository.findById(uuid).orElse(new Image());
    }

    @Override
    public byte[] findImage(UUID uuid) throws  CouponsException {
        if(!isUUIDExist(uuid)){
            throw new CouponsException("image didnt find...");
        }

        return imageRepository.findImageByUUID(uuid);
    }

    @Override
    public boolean isUUIDExist(UUID uuid) {
        return imageRepository.existsById(uuid);
    }

}
