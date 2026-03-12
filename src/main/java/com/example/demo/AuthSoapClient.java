package com.example.demo;

import mn.edu.num.auth.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class AuthSoapClient {

    private WebServiceTemplate webServiceTemplate;

    public AuthSoapClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("mn.edu.num.auth");
        
        webServiceTemplate = new WebServiceTemplate(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setDefaultUri("http://localhost:8081/ws");
    }

    public boolean isTokenValid(String token) {
        try {
            ValidateTokenRequest request = new ValidateTokenRequest();
            request.setToken(token);

            ValidateTokenResponse response = (ValidateTokenResponse) 
                webServiceTemplate.marshalSendAndReceive(request);

            return response.isIsValid();
        } catch (Exception e) {
            return false;
        }
    }
}