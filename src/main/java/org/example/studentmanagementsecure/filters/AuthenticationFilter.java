package org.example.studentmanagementsecure.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import org.example.studentmanagementsecure.exceptions.AuthenticationException;
import java.io.IOException;
@Component
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        String token=httpRequest.getHeader("token");
        if(token==null||(!token.equals("student-token")&&!token.equals("admin-token"))){
            throw new AuthenticationException("Invalid login id");
        }
        if(token.equals("student-token")){
            httpRequest.setAttribute("role","student");
        }
        if(token.equals("admin-token")){
            httpRequest.setAttribute("role","admin");
        }
        chain.doFilter(request,response);
    }
}
