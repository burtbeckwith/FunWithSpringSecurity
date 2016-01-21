package autorole

import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import test.User

class AutoRoleUserDetailsService implements GrailsUserDetailsService {

	UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
		loadUserByUsername username
	}

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User.withTransaction {
			User user = User.findByUsername(username)
			if (!user) throw new NoStackUsernameNotFoundException()

			def authorities = [new SimpleGrantedAuthority(user.admin ? 'ROLE_ADMIN' : 'ROLE_USER')]
			new GrailsUser(user.username, user.password, user.enabled,
					!user.accountExpired, !user.passwordExpired,
					!user.accountLocked, authorities, user.id)
		}
	}
}
