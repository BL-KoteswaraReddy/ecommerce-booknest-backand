//package com.ecommerce.security.handler;
//
//import com.ecommerce.filters.JwtUtil;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//
//        // Extract user info from Google
//        String email = oAuth2User.getAttribute("email");
//        String name  = oAuth2User.getAttribute("name");
//
//        // Generate your own JWT
//        List<String> roles = List.of("ROLE_USER");
//        String jwt = jwtUtil.generateToken(email, "CUSTOMER");
//
//        // Option 1: Redirect with token (for frontend SPA)
//        String redirectUrl = "http://localhost:3000/oauth2/callback?token=" + jwt;
//        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//
//        // Option 2: Return token in response body (for REST clients)
//        // response.setContentType("application/json");
//        // response.getWriter().write("{\"token\": \"" + jwt + "\"}");
//    }
//}