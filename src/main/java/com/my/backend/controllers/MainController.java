package com.my.backend.controllers;

import com.my.backend.models.User;
import com.my.backend.repositories.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
public class MainController {
    UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    String main(){
        return "Hi";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.checkLogin(user.getLogin()).isEmpty()) {
            userRepository.save(user);
            return "Added user";
        }
        else
            return "That user is already added";
    }
    @PostMapping("/login")
    public User login(@RequestBody User user, HttpServletResponse response) {
//        if (userRepository.checkLogin(user.getLogin()).isEmpty()) {
//            return "That user`s login isn`t correct or you don`t register";
//        } else {
//
//            else
//                return "Password isn`t correct";
//        }
//        if(userRepository.checkLogin(user.getLogin()).get(0).getPassword().equals(user.getPassword())) {
//
//        }
        if (userRepository.checkLogin(user.getLogin()).isEmpty())
            return new User();
        else {
            Cookie loginCook = new Cookie("login", user.getLogin());
            loginCook.setMaxAge(-1);
            response.addCookie(loginCook);
            return userRepository.checkLogin(user.getLogin()).get(0);
        }
    }
    @GetMapping("/exit")
    public String exit(HttpServletResponse response, HttpServletRequest request){
        String res = "You didn`t login";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    res = "Good buy";
                }
            }
        }
        return  res;
    }
}
