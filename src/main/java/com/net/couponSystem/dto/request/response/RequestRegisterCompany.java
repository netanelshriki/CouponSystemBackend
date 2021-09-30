package com.net.couponSystem.dto.request.response;

import com.net.couponSystem.security.ClientType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRegisterCompany {
    private String firstName;

    private String email;
    private String password;
    private ClientType clientType;
}
