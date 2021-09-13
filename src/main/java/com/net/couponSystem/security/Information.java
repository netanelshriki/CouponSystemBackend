package com.net.couponSystem.security;

import com.net.couponSystem.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {

    private int clientID;
    private ClientService clientService;
    private LocalDateTime time;
    private ClientType clientType;
    private String name;

}