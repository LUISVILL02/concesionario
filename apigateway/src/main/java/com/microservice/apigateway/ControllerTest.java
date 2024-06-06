package com.microservice.apigateway;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ControllerTest {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            Map<String, Object> claims = principal.getClaims();
            System.out.println("Claims: " + claims);
            OidcIdToken idToken = principal.getIdToken();
            String accessToken = idToken.getTokenValue();
            System.out.println("Access Token: " + accessToken);
            model.addAttribute("accessToken", accessToken);
            model.addAttribute("profile", claims);
        }
        return "index";
    }

    @GetMapping(value = "/private")
    public String privateEndpoint() {
        return "All good. You can see this because you are Authenticated.";
    }
}
