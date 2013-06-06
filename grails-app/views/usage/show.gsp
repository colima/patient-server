
<%@ page import="com.fusion.health.Usage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usage.label', default: 'Usage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-usage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-usage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list usage">
			
				<g:if test="${usageInstance?.patient}">
				<li class="fieldcontain">
					<span id="patient-label" class="property-label"><g:message code="usage.patient.label" default="Patient" /></span>
					
						<span class="property-value" aria-labelledby="patient-label"><g:link controller="patient" action="show" id="${usageInstance?.patient?.id}">${usageInstance?.patient?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${usageInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="usage.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${usageInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${usageInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="usage.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${usageInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${usageInstance?.AHI}">
				<li class="fieldcontain">
					<span id="AHI-label" class="property-label"><g:message code="usage.AHI.label" default="AHI" /></span>
					
						<span class="property-value" aria-labelledby="AHI-label"><g:fieldValue bean="${usageInstance}" field="AHI"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${usageInstance?.excluded}">
				<li class="fieldcontain">
					<span id="excluded-label" class="property-label"><g:message code="usage.excluded.label" default="Excluded" /></span>
					
						<span class="property-value" aria-labelledby="excluded-label"><g:formatBoolean boolean="${usageInstance?.excluded}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${usageInstance?.id}" />
					<g:link class="edit" action="edit" id="${usageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
