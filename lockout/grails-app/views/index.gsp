<html>

<head>
</head>

<body>

<ul>
<li><g:link controller='user'>User Controller</g:link></li>
<li><g:link controller='secure'>Must have ROLE_USER or ROLE_ADMIN</g:link></li>
<li><g:link controller='secure' action='admin'>Must have ROLE_ADMIN</g:link></li>
</ul>

</body>

</html>
