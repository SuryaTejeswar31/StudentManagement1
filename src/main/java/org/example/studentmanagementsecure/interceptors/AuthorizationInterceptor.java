package org.example.studentmanagementsecure.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.studentmanagementsecure.exceptions.AuthenticationException;
import org.example.studentmanagementsecure.exceptions.ForbiddenException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getAttribute("role").toString()==null){
            throw new AuthenticationException("You are unauthorized to perform this operation");
        }
        String role= request.getAttribute("role").toString();
        if(handler instanceof HandlerMethod method){
            String methodName=method.getMethod().getName();
            if(role.equals("admin")){
                return true;
            }
            else if(role.equals("student")){
                if(methodName.equals("getStudentById")||methodName.equals("getAllStudents")||methodName.equals("getStudentsByDepartment")){
                    return true;
                }
                else throw new ForbiddenException("You are not authorized to perform this operation");
            }
            else throw new ForbiddenException("You are not authorized to perform this operation");
        }
        return true;
    }
}
