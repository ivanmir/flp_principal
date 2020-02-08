package com.isv;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;


@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	XsuaaServiceConfiguration xsuaaServiceConfiguration;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http //.addFilterAfter(new RequestContextServletFilter(), SessionManagementFilter.class)
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)        	
            .and()
                .authorizeRequests()
                .antMatchers(GET, "/actuator/**").anonymous() // accepts unauthenticated user (w/o JWT)
                .antMatchers(GET, "/hello").anonymous() // accepts unauthenticated user (w/o JWT)
                .antMatchers(GET, "/getPrincipal/**").authenticated() 
                .anyRequest().denyAll() // denies anything not configured above
            .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(getJwtAuthoritiesConverter());
    }

	Converter<Jwt, AbstractAuthenticationToken> getJwtAuthoritiesConverter() {
		TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
		converter.setLocalScopeAsAuthorities(true);
		return converter;
	}
	
}

