package com.net.couponSystem.dto.request;

import com.net.couponSystem.security.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestLogin {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;


}
