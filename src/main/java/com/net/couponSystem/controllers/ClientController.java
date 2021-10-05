package com.net.couponSystem.controllers;

import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.beans.Image;
import com.net.couponSystem.controllers.model.CouponPayload;
import com.net.couponSystem.dto.request.RequestLogin;
import com.net.couponSystem.dto.request.response.ResponseLogin;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.mapper.CouponDTO;
import com.net.couponSystem.repos.CouponRepository;
import com.net.couponSystem.repos.CustomerRepository;
import com.net.couponSystem.security.Information;
import com.net.couponSystem.security.LoginManager;
import com.net.couponSystem.security.TokenManager;
import com.net.couponSystem.services.CouponService;
import com.net.couponSystem.services.CustomerService;
import com.net.couponSystem.services.CustomerServiceImpl;
import com.net.couponSystem.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.UUID;

@RestController
@RequestMapping(value = "client")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@Scope("prototype")
public class ClientController {

    private final LoginManager loginManager;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;


    private final ImageService imageService;


    private final CouponService couponService;

    @Autowired
    private TokenManager tokenManager;

    private CustomerService customerService;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin) throws CouponsException, LoginException {
        String token = loginManager.login(requestLogin.getEmail(), requestLogin.getPassword(), requestLogin.getClientType());
//        Customer customer = customerRepository.findByEmailAndPassword(requestLogin.getEmail(), requestLogin.getPassword());
//        ((CustomerServiceImpl) customerService).setCustomerID(customer.getId(), customer.getFirstName());
        Information information = tokenManager.getMap().get(token);
        System.out.println("from client info------->" + information);

        ResponseLogin responseLogin = ResponseLogin.builder()
                .id(information.getId())
                .clientType(information.getClientType())
                .name(information.getName())
                .token(token)
                .build();
        System.out.println("from client ---------->" + responseLogin);
        return new ResponseEntity<>(responseLogin, HttpStatus.CREATED);
    }

    @GetMapping("coupons")
    public ResponseEntity<?> allCoupons(){
        return new ResponseEntity<>(couponService.getAllCoupons(),HttpStatus.OK);
    }

    @RequestMapping(value = "coupons/images/{uuid}", method = RequestMethod.GET)
    public String getCouponImage(@PathVariable UUID uuid, HttpServletResponse response) throws Exception {
        Image image = imageService.getImage(uuid);

        response.setHeader("Content-Disposition", "inline;filename=\"" + image.getId().toString() + "\"");
        OutputStream out = response.getOutputStream();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(new ByteArrayInputStream(image.getImage()), out);
        out.close();


        return null;
    }

}



