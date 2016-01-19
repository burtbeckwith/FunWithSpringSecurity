package hacking.extralogin.auth

import groovy.transform.CompileStatic
import org.springframework.security.core.userdetails.UsernameNotFoundException

@CompileStatic
class NoStackBadCredentialsException extends UsernameNotFoundException {
	private static final long serialVersionUID = 1

	NoStackBadCredentialsException(String message) {
		super(message)
	}

	@Override
	synchronized Throwable fillInStackTrace() {
		// do nothing
		this
	}
}
