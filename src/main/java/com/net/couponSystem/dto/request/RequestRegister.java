package com.net.couponSystem.dto.request;

import com.net.couponSystem.security.ClientType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegister {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ClientType clientType;
}
