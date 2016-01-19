package noroles

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

class NorolesGrailsUser extends GrailsUser {

	final String businessUnit
	final String userType

	NorolesGrailsUser(String username, String password, boolean enabled, boolean accountNonExpired,
	                  boolean credentialsNonExpired, boolean accountNonLocked,
	                  Collection<GrantedAuthority> authorities, id, UserType userType, String businessUnit) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities, id)

		this.businessUnit = businessUnit
		this.userType = userType.toString()
	}
}
