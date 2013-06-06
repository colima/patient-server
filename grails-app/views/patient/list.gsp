
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
						<g:sortableColumn property="medicalRecord" title="${message(code: 'patient.medicalRecord.label', default: 'MR')}" />
						
						<g:sortableColumn property="fullName" title="${message(code: 'patient.fullName.label', default: 'Full Name')}" />
					
						<g:sortableColumn property="birth" title="${message(code: 'patient.age.label', default: 'Age')}" />
					
						<g:sortableColumn property="gender" title="${message(code: 'patient.gender.label', default: 'Gender')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'patient.status.label', default: 'Status')}" />
						
						<g:sortableColumn property="compliance" title="${message(code: 'patient.compliance.label', default: 'Compliance')}" />
						
						<g:sortableColumn property="effort" title="${message(code: 'patient.effort.label', default: 'Effort')}" />
						
						<g:sortableColumn property="ahiIndex" title="${message(code: 'patient.ahi.index.label', default: 'AHI Index')}" />
						
						<g:sortableColumn property="ahiDate" title="${message(code: 'patient.ahi.date.label', default: 'AHI Date')}" />
						
						<g:sortableColumn property="location" title="${message(code: 'patient.location.name.label', default: 'Location')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${patientInstanceList}" status="i" var="patientInstance">
					<tr class="${patientInstance.getCompliance()<70 ? 'red' : 'green'}">
					
						<td>${fieldValue(bean: patientInstance, field: "medicalRecord")}</td>
					
						<td><g:link action="show" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "fullName")}</g:link></td>					
					
						<td>${fieldValue(bean: patientInstance, field: "age")}</td>
					
						<td>${fieldValue(bean: patientInstance, field: "gender")}</td>
					
						<td>${fieldValue(bean: patientInstance, field: "status")}</td>
						
						<td>${fieldValue(bean: patientInstance, field: "compliance")}</td>
						
						<td>${fieldValue(bean: patientInstance, field: "effort")}</td>
						
						<td>${fieldValue(bean: patientInstance, field: "lastAHIIndex")}</td>
						
						<td><g:formatDate format="yyyy-MM-dd" date="${patientInstance.lastAHIDate}"/></td>
						
						<td><g:link action="show" id="${patientInstance.location.id}">${patientInstance.location.name}</g:link></td>
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
