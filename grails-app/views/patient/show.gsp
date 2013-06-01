
<%@ page import="com.fusion.health.Patient" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-patient" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-patient" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list patient">
			
				<g:if test="${patientInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="patient.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${patientInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.middleName}">
				<li class="fieldcontain">
					<span id="middleName-label" class="property-label"><g:message code="patient.middleName.label" default="Middle Name" /></span>
					
						<span class="property-value" aria-labelledby="middleName-label"><g:fieldValue bean="${patientInstance}" field="middleName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="patient.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${patientInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.birth}">
				<li class="fieldcontain">
					<span id="birth-label" class="property-label"><g:message code="patient.birth.label" default="Birth" /></span>
					
						<span class="property-value" aria-labelledby="birth-label"><g:formatDate date="${patientInstance?.birth}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.gender}">
				<li class="fieldcontain">
					<span id="gender-label" class="property-label"><g:message code="patient.gender.label" default="Gender" /></span>
					
						<span class="property-value" aria-labelledby="gender-label"><g:fieldValue bean="${patientInstance}" field="gender"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="patient.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${patientInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="patient.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:link controller="location" action="show" id="${patientInstance?.location?.id}">${patientInstance?.location?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${patientInstance?.usages}">
				<li class="fieldcontain">
					<span id="usages-label" class="property-label"><g:message code="patient.usages.label" default="Usages" /></span>
					
						<g:each in="${patientInstance.usages}" var="u">
						<span class="property-value" aria-labelledby="usages-label"><g:link controller="usage" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${patientInstance?.id}" />
					<g:link class="edit" action="edit" id="${patientInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
