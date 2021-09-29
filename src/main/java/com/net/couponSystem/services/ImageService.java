package com.net.couponSystem.services;

import com.net.couponSystem.beans.Image;
import com.net.couponSystem.exceptions.CouponsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ImageService {
    byte[] findImage(UUID uuid) throws CouponsException;
    boolean isUUIDExist(UUID uuid);
    UUID addImage(byte[] bytes) throws Exception;
    Image getImage(UUID uuid) throws  Exception;


}