package com.net.couponSystem.dto.request.response;

import com.net.couponSystem.security.ClientType;
import com.net.couponSystem.services.ClientService;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class ResponseLogin {
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    private String token;
//    private String email;
//    private String password;
}
