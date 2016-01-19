<html>
<head>
<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="show-user" class="content scaffold-show" role="main">
		<h1><g:message code="default.show.label" args="[entityName]" /></h1>
		<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
		</g:if>
		<ol class="property-list user">
			<li class="fieldcontain">
				<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
				<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${user}" field="username"/></span>
			</li>
			<li class="fieldcontain">
				<span id="badCredentialsCount-label" class="property-label"><g:message code="user.badCredentialsCount.label" default="Bad Credentials Count" /></span>
				<span class="property-value" aria-labelledby="badCredentialsCount-label"><g:fieldValue bean="${user}" field="badCredentialsCount"/></span>
			</li>
			<li class="fieldcontain">
				<span id="accountExpired-label" class="property-label"><g:message code="user.accountExpired.label" default="Account Expired" /></span>
				<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${user?.accountExpired}" /></span>
			</li>
			<li class="fieldcontain">
				<span id="accountLocked-label" class="property-label"><g:message code="user.accountLocked.label" default="Account Locked" /></span>
				<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${user?.accountLocked}" /></span>
			</li>
			<li class="fieldcontain">
				<span id="enabled-label" class="property-label"><g:message code="user.enabled.label" default="Enabled" /></span>
				<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${user?.enabled}" /></span>
			</li>
			<li class="fieldcontain">
				<span id="passwordExpired-label" class="property-label"><g:message code="user.passwordExpired.label" default="Password Expired" /></span>
				<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${user?.passwordExpired}" /></span>
			</li>
		</ol>
		<g:form resource="${user}" method="DELETE">
			<fieldset class="buttons">
				<g:link class="edit" action="edit" resource="${user}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				<input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
