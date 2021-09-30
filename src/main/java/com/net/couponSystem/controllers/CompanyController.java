package com.net.couponSystem.controllers;

import com.net.couponSystem.beans.Company;
import com.net.couponSystem.beans.Coupon;
import com.net.couponSystem.beans.Customer;
import com.net.couponSystem.beans.Image;
import com.net.couponSystem.controllers.model.ByCategory;
import com.net.couponSystem.controllers.model.ByMazPrize;
import com.net.couponSystem.controllers.model.CouponPayload;
import com.net.couponSystem.dto.request.RequestRegister;
import com.net.couponSystem.dto.request.response.RequestRegisterCompany;
import com.net.couponSystem.dto.request.response.ResponseLogin;
import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.mapper.CouponDTO;
import com.net.couponSystem.security.Information;
import com.net.couponSystem.security.LoginManager;
import com.net.couponSystem.security.TokenManager;
import com.net.couponSystem.services.AdminService;
import com.net.couponSystem.services.CompanyService;
import com.net.couponSystem.services.CouponService;
import com.net.couponSystem.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {

    private final TokenManager tokenManager;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginManager loginManager;

    private final ImageService imageService;

    private final CouponService couponService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "coupon",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    CouponDTO addCoupon(@RequestHeader("Authorization") String token, @ModelAttribute CouponPayload payload) throws Exception {
        System.out.println("coupon to add: " + payload);
        return couponService.addCoupon(payload);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("coupon/{id}")
    public void updateCoupon(@RequestHeader("Authorization") String token, @PathVariable int id, @RequestBody CouponDTO couponDTO) throws Exception {
        couponService.updateCoupon(id, couponDTO);
    }

    @GetMapping("coupons")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader("Authorization") String token, @RequestParam int companyId) {
        return new ResponseEntity<>(companyService.getCouponsCompanyByID(companyId), HttpStatus.OK);
    }

//    @GetMapping("coupons")
//    public ResponseEntity<?> getCoupons(@RequestHeader("Authorization") String token, @RequestParam int companyID) {
//        return new ResponseEntity<>(couponService.getAllCompanyCoupons(companyID), HttpStatus.OK);
//    }

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

    @DeleteMapping("coupons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader("Authorization") String token, @PathVariable int id) {
        couponService.deleteCoupon(id);
    }

    @GetMapping("details")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyDetails(@RequestHeader("Authorization") String token, @RequestParam int companyID) {
        return companyService.getCompanyDetails(companyID);
    }

    @PostMapping("coupon")
    public ResponseEntity<?> getCouponsByMaxPrize(@RequestHeader("Authorization") String token, @RequestBody ByMazPrize mazPrize) {
        return new ResponseEntity<>(companyService.getCouponsByMaxPrice(mazPrize.getPrize(), mazPrize.getCompanyId()), HttpStatus.OK);
    }

    @PostMapping("category")
    public ResponseEntity<?> getCouponsByCategory(@RequestHeader("Authorization") String token, @RequestBody ByCategory category) {
        return new ResponseEntity<>(companyService.getCouponsCompanyByCategory(category.getCategory(), category.getCompanyId()), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RequestRegisterCompany requestRegister) throws CouponsException, LoginException {
        Company company = Company.builder()
                .firstName(requestRegister.getFirstName())
                .email(requestRegister.getEmail())
                .password(requestRegister.getPassword())
                .clientType(requestRegister.getClientType())
                .build();
        adminService.addCompany(company);
        String token = loginManager.login(requestRegister.getEmail(), requestRegister.getPassword(),requestRegister.getClientType());

        Information information = tokenManager.getMap().get(token);
        System.out.println("from client info------->" + information);

        ResponseLogin responseLogin = ResponseLogin.builder()
                .id(information.getId())
                .clientType(information.getClientType())
                .name(information.getName())
                .token(token)
                .build();
        return new ResponseEntity<>(responseLogin,HttpStatus.CREATED);
    }

}
