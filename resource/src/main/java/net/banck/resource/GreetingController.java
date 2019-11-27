package net.banck.resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
public class GreetingController {
    private static final String SERVICE_NAME = "resource";

    @GetMapping("/greeting")
    public ServiceCallResponse greeting(JwtAuthenticationToken jwtAuthentication, HttpServletRequest request){
        ServiceCallResponse serviceCallResponse = new ServiceCallResponse();
        serviceCallResponse.setServiceName(SERVICE_NAME);
        serviceCallResponse.setServiceUri(request.getRequestURL().toString());
        serviceCallResponse.setJti(jwtAuthentication.getToken().getId());
        serviceCallResponse.setSub(jwtAuthentication.getToken().getSubject());
        serviceCallResponse.setAud(jwtAuthentication.getToken().getAudience());
        serviceCallResponse.setAuthorities(jwtAuthentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority).sorted().collect(Collectors.toList()));
        System.out.println(jwtAuthentication.getToken().getClaims().get("user_name"));
        return serviceCallResponse;
    }
}

