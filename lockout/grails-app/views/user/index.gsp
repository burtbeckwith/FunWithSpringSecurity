<html>
<head>
<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
	</div>
	<div id="list-user" class="content scaffold-list" role="main">
		<h1><g:message code="default.list.label" args="[entityName]" /></h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<table>
		<thead>
			<tr>
				<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />
				<g:sortableColumn property="badCredentialsCount" title="${message(code: 'user.badCredentialsCount.label', default: 'Bad Credentials Count')}" />
				<g:sortableColumn property="accountExpired" title="${message(code: 'user.accountExpired.label', default: 'Account Expired')}" />
				<g:sortableColumn property="accountLocked" title="${message(code: 'user.accountLocked.label', default: 'Account Locked')}" />
				<g:sortableColumn property="enabled" title="${message(code: 'user.enabled.label', default: 'Enabled')}" />
				<g:sortableColumn property="passwordExpired" title="${message(code: 'user.passwordExpired.label', default: 'Password Expired')}" />
			</tr>
		</thead>
		<tbody>
			<g:each in="${userList}" status="i" var="user">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td><g:link action="show" id="${user.id}">${fieldValue(bean: user, field: "username")}</g:link></td>
				<td>${user.badCredentialsCount}</td>
				<td><g:formatBoolean boolean="${user.accountExpired}" /></td>
				<td><g:formatBoolean boolean="${user.accountLocked}" /></td>
				<td><g:formatBoolean boolean="${user.enabled}" /></td>
				<td><g:formatBoolean boolean="${user.passwordExpired}" /></td>
			</tr>
			</g:each>
		</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${userCount ?: 0}" />
		</div>
	</div>
</body>
</html>
