package hacking.extralogin

import groovy.transform.CompileStatic
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

@CompileStatic
class OrganizationAuthentication extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1

	final String organizationName

	OrganizationAuthentication(principal, credentials, String orgName) {
		super(principal, credentials)
		organizationName = orgName
	}

	OrganizationAuthentication(principal, credentials, String orgName, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities)
		organizationName = orgName
	}
}
