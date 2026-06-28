package com.cn.hotel.config;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HotelSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{
		http
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/login").permitAll()
					.anyRequest().authenticated()
			)
			
			.oauth2Login(oauth2 -> oauth2
					.loginPage("/login")
					.defaultSuccessUrl("/hotel/getAll")
			)
			.oauth2ResourceServer(oauth2 -> oauth2
						.jwt(jwt -> jwt
								.jwtAuthenticationConverter(jwtAuthenticationConverter())
						        )
			);
		return http.build();
	}

	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(jwt ->{
			Collection<GrantedAuthority> authorities = new ArrayList<>();

			//Extract roles from realm_access
			Map<String, Object> claimsMap = jwt.getClaims();
			Object realmAccess = claimsMap.get("realm_access");
			if(realmAccess != null) {
				LinkedTreeMap<String, List<String>> roleMap = (LinkedTreeMap<String, List<String>>) realmAccess;
				List<String> roles = new ArrayList<>(roleMap.get("roles"));
				authorities.addAll(roles.stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList()));
			}
			return authorities;
		});
		return converter;
	}

	@Bean
	public GrantedAuthoritiesMapper userAuthoritiesMapper() {
		return (authorities) -> {
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

			authorities.forEach(authority -> {
				if(OAuth2UserAuthority.class.isInstance(authority)) {

					OAuth2UserAuthority oAuth2UserAuthority = (OAuth2UserAuthority) authority;
					Map<String, Object> userAttributesMap = oAuth2UserAuthority.getAttributes();

					if(userAttributesMap.containsKey("realm_access")) {
						Map<String, Object> realmAccessMap = (Map<String, Object>) userAttributesMap.get("realm_access");
						if(realmAccessMap.containsKey("roles")) {
							List<String> roles = (List<String>)realmAccessMap.get("roles");
							mappedAuthorities.addAll(roles.stream()
									.map(role -> new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()))
									.collect(Collectors.toSet()));
						}
					}
				}
			});
			return mappedAuthorities;
		};
	}


	@Bean
	public JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation("https://lemur-7.cloud-iam.com/auth/realms/key-cloak-hotel-demo");
	}
}