package com.net.tmi.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.net.tmi.entity.CustomUserDetails;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		CustomUserDetails userDetails=(CustomUserDetails) authentication.getPrincipal();
		
		/*
		 * System.out.println("UserName: " +userDetails.getUsername());
		 * 
		 * Collection<? extends GrantedAuthority> authorities=
		 * userDetails.getAuthorities();
		 * authorities.forEach(auth->System.out.println(auth.getAuthority()));
		 */
		String redirectURL = request.getContextPath();
		
		if (userDetails.hasRole("Doctor")) {
			redirectURL +="/doctor";
		}else if (userDetails.hasRole("User")) {
			redirectURL +="/";
		}else {
			redirectURL +="/admin";
		}
		
		
		response.sendRedirect(redirectURL);
	}
}
