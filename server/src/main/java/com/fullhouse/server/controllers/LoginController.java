package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;
import com.fullhouse.server.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Login controller.
 */
@RestController
public class LoginController {

    private final LoginService loginService;
    private final Map<String, LoginSuccessResponse> recentLogins = new ConcurrentHashMap<>();
    private final Random random = new Random();
    /**
     * Instantiates a new Login controller.
     *
     * @param loginService the login service
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Login success login success response.
     *
     * @param user the user
     * @return an HTML page prompting the user to return to the app
     */
    @GetMapping(value = "/loginSuccess", produces = "text/html")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User user) {

        LoginSuccessResponse response = loginService.registerLogin(user);
        String pin = String.format("%06d", random.nextInt(999999));
        recentLogins.put(pin, response);
        return "<html><body style='text-align:center; font-family:sans-serif; margin-top:50px;'>" +
                "<h1>Login Successful!</h1>" +
                "<p>Please return to the FullHouse app and enter this code:</p>" +
                "<h2 style='font-size: 48px; color: blue;'>" + pin + "</h2>" +
                "<p>You can close this tab safely.</p>" +
                "</body></html>";
    }

    @GetMapping("/login/pin")
    public LoginSuccessResponse exchangePin(@RequestParam String pin) {
        return recentLogins.remove(pin);
    }

//    /**
//     * Login success client login success response.
//     *
//     * @return the login success response
//     */
//    @GetMapping("/loginSuccess/client")
//    public LoginSuccessResponse loginSuccessClient() {
//        return loginService.getLastLogin(email);
//    }
//
//    /**
//     * Logout client response entity.
//     *
//     * @param request the request
//     * @return the response entity
//     */
//    @PostMapping("/logout/client")
//    public ResponseEntity<String> logoutClient(HttpServletRequest request) {
//        loginService.clearLastLogin(email);
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return ResponseEntity.ok("Logged out");
//    }
}