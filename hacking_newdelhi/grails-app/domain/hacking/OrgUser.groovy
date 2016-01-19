package hacking

import groovy.transform.ToString

@ToString(cache=true, includeNames=true, includePackage=false)
class OrgUser {

	Organization organization
	User user

	OrgUser(Organization organization, User user) {
		this.organization = organization
		this.user = user
	}

	static constraints = {
		user unique: 'organization'
	}
}
