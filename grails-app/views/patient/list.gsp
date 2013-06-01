
<%@ page import="com.fusion.health.Patient" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-patient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-patient" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="lastName" title="${message(code: 'patient.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="middleName" title="${message(code: 'patient.middleName.label', default: 'Middle Name')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'patient.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="birth" title="${message(code: 'patient.birth.label', default: 'Birth')}" />
					
						<g:sortableColumn property="gender" title="${message(code: 'patient.gender.label', default: 'Gender')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'patient.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${patientInstanceList}" status="i" var="patientInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "lastName")}</g:link></td>
					
						<td>${fieldValue(bean: patientInstance, field: "middleName")}</td>
					
						<td>${fieldValue(bean: patientInstance, field: "firstName")}</td>
					
						<td><g:formatDate date="${patientInstance.birth}" /></td>
					
						<td>${fieldValue(bean: patientInstance, field: "gender")}</td>
					
						<td>${fieldValue(bean: patientInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${patientInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
