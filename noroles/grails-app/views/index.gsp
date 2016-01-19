<html>

<head>
</head>

<body>

<ul>
<li><g:link controller='secure'>Anyone can access</g:link></li>
<li><g:link controller='secure' action='secure'>Must be authenticated</g:link></li>
<li><g:link controller='secure' action='sales'>Must have UserType.sales</g:link></li>
<li><g:link controller='secure' action='group1'>Must have businessUnit 'group1'</g:link></li>
<li><g:link controller='secure' action='group2'>Must have businessUnit 'group2'</g:link></li>
<li><g:link controller='secure' action='dev'>Must have developer=true</g:link></li>
</ul>

</body>

</html>
