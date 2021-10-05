package com.net.couponSystem.security;

import com.net.couponSystem.exceptions.CouponsException;
import com.net.couponSystem.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class LoginManager {


    private final ApplicationContext ctx;
    private final TokenManager tokenManager;

    public String login(String email, String password, ClientType clientType) throws CouponsException, LoginException {

        switch (clientType) {
            case ADMIN:
                AdminService adminService = ctx.getBean(AdminService.class);

                if (((AdminServiceImpl) adminService).login(email, password)) {
                    int adminId = 1;
                    String name = "ADMIN";
                    String token = tokenManager.createToken((ClientService) adminService, ClientType.ADMIN, adminId,name);
                    return token;
                }
            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (((CompanyServiceImpl) companyService).login(email, password)) {
                    int companyId = ((CompanyServiceImpl) companyService).getCompanyID();
                    String name = ((CompanyServiceImpl)companyService).getName();
                    String token = tokenManager.createToken((ClientService) companyService, ClientType.COMPANY, companyId,name);
                    return token;
                }
            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (((CustomerServiceImpl) customerService).login(email, password)) {
                    int customerId = ((CustomerServiceImpl) customerService).getCustomerID();
                    String name = ((CustomerServiceImpl)customerService).getCustomerName();
                    String token = tokenManager.createToken((ClientService) customerService, ClientType.CUSTOMER, customerId ,name);
//                    ((CustomerServiceImpl) customerService).setCustomerID(customerId, name);

                    return token;
                }
        }
                throw new CouponsException("no user found");
        }

}
