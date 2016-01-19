<div class="fieldcontain ${hasErrors(bean: user, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${user?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: user, field: 'badCredentialsCount', 'error')} ">
	<label for="badCredentialsCount"><g:message code="user.badCredentialsCount.label" default="Bad Credentials Count" /></label>
	<g:textField name="badCredentialsCount" value="${user?.badCredentialsCount}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: user, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${user?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: user, field: 'accountExpired', 'error')} ">
	<label for="accountExpired"><g:message code="user.accountExpired.label" default="Account Expired" /></label>
	<g:checkBox name="accountExpired" value="${user?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: user, field: 'accountLocked', 'error')} ">
	<label for="accountLocked"><g:message code="user.accountLocked.label" default="Account Locked" /></label>
	<g:checkBox name="accountLocked" value="${user?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: user, field: 'enabled', 'error')} ">
	<label for="enabled"><g:message code="user.enabled.label" default="Enabled" /></label>
	<g:checkBox name="enabled" value="${user?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: user, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired"><g:message code="user.passwordExpired.label" default="Password Expired" /></label>
	<g:checkBox name="passwordExpired" value="${user?.passwordExpired}" />
</div>
