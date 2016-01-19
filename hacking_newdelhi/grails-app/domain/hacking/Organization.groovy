package hacking

import groovy.transform.ToString

@ToString(cache=true, includeNames=true, includePackage=false)
class Organization {

	String name

	Organization(String name) {
		this.name = name
	}

	static constraints = {
		name unique: true, blank: false
	}
}
