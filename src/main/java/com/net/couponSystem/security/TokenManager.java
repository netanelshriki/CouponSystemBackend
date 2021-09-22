package com.net.couponSystem.security;

import com.net.couponSystem.services.ClientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@Data
public class TokenManager {
    private static final int MINUTES_BEFORE_DELETE=2;

    @Autowired
    private Map<String, Information> map;

    public String createToken(ClientService clientService,ClientType clientType, int clientID, String name) {
       removePreviewsTokenById(clientID);

        String token =  UUID.randomUUID().toString();
        Information info = Information.builder()
                .time(LocalDateTime.now())
                .clientType(clientType)
                .id(clientID)
                .name(name)
               .clientService(clientService)
                .build();
        map.put(token, info);
        System.out.println("from Add token map--------> " + map.get(token));

        return token;
    }

    public void deleteToken(String token) {
        map.remove(token);
    }
    public void removePreviewsTokenById(int clientId) {
        map.entrySet().removeIf(valueToRemove -> valueToRemove.getValue().getId() == clientId);
    }


    public boolean isExist(String token) {
        return map.get(token) != null;
    }

    public void updateTokenTime(String token) {
        map.get(token).setTime(LocalDateTime.now());
    }

    public boolean isControllerAllowed( ClientType clientType,String token) {
        Information information = map.get(token);
        if (!information.getClientType().equals(clientType)) {
            throw new SecurityException("Wrong token... you should try again");
        }
        return true;
    }

    private static boolean removeExpired(Map.Entry<String,Information>container) {
        return container.getValue().getTime().isBefore(LocalDateTime.now().minusMinutes(MINUTES_BEFORE_DELETE));
    }

    public void removeExpired() {
        map.entrySet().removeIf(TokenManager::removeExpired);
    }




}
