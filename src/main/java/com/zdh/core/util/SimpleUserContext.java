package com.zdh.core.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class SimpleUserContext {
	public static SimpleUserContext getInstance() {
		return new SimpleUserContext();
	}

	protected Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if ((authentication == null) || (!authentication.isAuthenticated())) {
			return null;
		}
		return authentication;
	}

	public void setCurrentUserDetails(UserDetails user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
		SecurityContextHolder.setStrategyName("MODE_GLOBAL");
	}

	public UserDetails getCurrentUserDetails() {
		Authentication authentication = getAuthentication();
		if (authentication == null)
			return null;
		Object principal = authentication.getPrincipal();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) principal;
			return userDetail;
		}
		return null;
	}

	public String getCurrentUserName() {
		UserDetails user = getCurrentUserDetails();
		return user == null ? null : user.getUsername();
	}

	public boolean isAnonymous() {
		Authentication authentication = getAuthentication();
		return (authentication == null)
				|| ((authentication instanceof AnonymousAuthenticationToken));
	}

	public String getCurrentUserIP() {
		Authentication authentication = getAuthentication();
		if (authentication == null)
			return null;
		Object details = authentication.getDetails();
		if (!(details instanceof WebAuthenticationDetails))
			return null;
		return ((WebAuthenticationDetails) details).getRemoteAddress();
	}
}