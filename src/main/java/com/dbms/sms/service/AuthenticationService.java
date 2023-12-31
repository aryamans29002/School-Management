package com.dbms.sms.service;

import com.dbms.sms.entity.User;
import com.dbms.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository users;
    private String SESSION_AUTH_KEY = "AUTH_USER";

    public Boolean checkCredentials(String username, String password) {
        User user = users.getUser(username);
//        if(user==null) return false;
        return user.getPassword().equals(password);
    }

    public void loginUser(HttpSession session, String username) {
        session.setAttribute(SESSION_AUTH_KEY, username);
    }

    public void logoutUser(HttpSession session) {
        session.removeAttribute(SESSION_AUTH_KEY);
    }
    
    public void registerUser(String username,String password,Boolean isAdmin) {
    	User newuser=new User();
    	newuser.setUsername(username);
    	newuser.setPassword(password);
//    	newuser.setIsAdmin(isAdmin);
    }
    public String getCurrentUser(HttpSession session) {
        try {
            return session.getAttribute(SESSION_AUTH_KEY).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isAuthenticated(HttpSession session) {
        return getCurrentUser(session) != null;
    }
}
