package com.isv;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.security.xsuaa.token.SpringSecurityContext;
import com.sap.cloud.security.xsuaa.token.XsuaaToken;

@RestController
@EnableAutoConfiguration
public class GetPrincipal {

	private static final Logger logger = LoggerFactory.getLogger(GetPrincipal.class);

	@GetMapping(value = "/getPrincipal", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> echo(@AuthenticationPrincipal XsuaaToken xsuaaJwtToken) {

    	List<JSONObject> entities = new ArrayList<JSONObject>();
    	
    	logger.debug("Request included Jwt: {}", xsuaaJwtToken);
        if(xsuaaJwtToken != null) {
            Collection<String> grantedScopes = xsuaaJwtToken.getScopes();

            logger.debug("Received a JWT token with the following authorities: {}", grantedScopes);
            logger.debug("You could programmatically check for them now.");
            
            String sEmail = SpringSecurityContext.getToken().getEmail();

            JSONObject entity = new JSONObject();
            entity.put("Email", sEmail);
            entity.put("GrantedScopes", grantedScopes);
            entities.add(entity);
            
            return ResponseEntity.status(HttpStatus.OK).body(entities.toString());
        } else {
        	return ResponseEntity.status(HttpStatus.OK).body("Echo: Not authenticated");
        }
       
    }
}



