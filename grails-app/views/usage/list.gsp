
<%@ page import="com.fusion.health.Usage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usage.label', default: 'Usage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-usage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-usage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="usage.patient.label" default="Patient" /></th>
					
						<g:sortableColumn property="date" title="${message(code: 'usage.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'usage.status.label', default: 'Status')}" />
					
						<g:sortableColumn property="AHI" title="${message(code: 'usage.AHI.label', default: 'AHI')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${usageInstanceList}" status="i" var="usageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${usageInstance.id}">${fieldValue(bean: usageInstance, field: "patient")}</g:link></td>
					
						<td><g:formatDate format="yyyy-MM-dd" date="${usageInstance.date}"/></td>
					
						<td>${fieldValue(bean: usageInstance, field: "status")}</td>
					
						<td>${fieldValue(bean: usageInstance, field: "AHI")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${usageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
