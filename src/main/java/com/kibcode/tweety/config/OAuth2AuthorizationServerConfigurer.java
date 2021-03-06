package com.kibcode.tweety.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private final AuthenticationManager authenticationManagerBean;


    public OAuth2AuthorizationServerConfigurer(AuthenticationManager authenticationManagerBean) {
        this.authenticationManagerBean = authenticationManagerBean;
    }

    @Override
    public void configure (ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("angularjsapp")
                .secret("{noop}angularjs123")
                .authorizedGrantTypes("password")
                .scopes("read", "write");
    }
}
