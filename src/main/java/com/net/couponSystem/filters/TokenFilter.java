package com.net.couponSystem.filters;

import com.net.couponSystem.security.ClientType;
import com.net.couponSystem.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(2)
@Component
@RequiredArgsConstructor
public class TokenFilter implements Filter {

    private final TokenManager tokenManager;
    private final static String ADMIN = "admin";
    private final static String CUSTOMER = "customer";
    private final static String COMPANY = "company";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println("url : " + url);


        if (    url.endsWith("login")
                || url.endsWith("favicon.ico")
                || url.endsWith("signup")
                ||url.endsWith("home")
                ||url.endsWith("/")
                ||url.endsWith("*")
        )
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
            String token;
            token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
            System.out.println("from token filter--------->" + token);
            String type = FilterHelper.getType(url);
            System.out.println("type : " + type);

            switch (type) {
                case ADMIN:
                    tokenManager.isControllerAllowed(ClientType.ADMIN,token);
                    break;
                case CUSTOMER:
                    tokenManager.isControllerAllowed( ClientType.CUSTOMER,token);
                    break;
                case COMPANY:
                    tokenManager.isControllerAllowed(ClientType.COMPANY,token);
                    break;
            }
            filterChain.doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);

        } catch (Exception e) {
            ((HttpServletResponse) servletResponse).sendError(401, e.getMessage());

        }
    }
}

