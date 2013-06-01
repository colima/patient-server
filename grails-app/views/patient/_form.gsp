<%@ page import="com.fusion.health.Patient"%>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'lastName', 'error')} required">
	<label for="lastName"> <g:message code="patient.lastName.label"
			default="Last Name" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="30" required=""
		value="${patientInstance?.lastName}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'middleName', 'error')} required">
	<label for="middleName"> <g:message
			code="patient.middleName.label" default="Middle Name" /> <span
		class="required-indicator">*</span>
	</label>
	<g:textField name="middleName" maxlength="10" required=""
		value="${patientInstance?.middleName}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'firstName', 'error')} required">
	<label for="firstName"> <g:message
			code="patient.firstName.label" default="First Name" /> <span
		class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="30" required=""
		value="${patientInstance?.firstName}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'birth', 'error')} required">
	<label for="birth"> <g:message code="patient.birth.label"
			default="Birth" /> <span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birth" precision="day"
		value="${patientInstance?.birth}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'gender', 'error')} required">
	<label for="gender"> <g:message code="patient.gender.label"
			default="Gender" /> <span class="required-indicator">*</span>
	</label>

	<g:radioGroup name="gender" values="${com.fusion.health.Gender?.values()}"
		labels="${com.fusion.health.Gender?.values()*.name()}"
		value="${patientInstance?.gender?.name()}">
		${it.radio}
		<g:message code="${it.label}" />&nbsp;
</g:radioGroup>

</div>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'status', 'error')} required">
	<label for="status"> <g:message code="patient.status.label"
			default="Status" /> <span class="required-indicator">*</span>
	</label>
	<g:select name="status"
		from="${com.fusion.health.Patient$Status?.values()}"
		keys="${com.fusion.health.Patient$Status.values()*.name()}"
		required="" value="${patientInstance?.status?.name()}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'location', 'error')} required">
	<label for="location"> <g:message code="patient.location.label"
			default="Location" /> <span class="required-indicator">*</span>
	</label>
	<g:select id="location" name="location.id"
		from="${com.fusion.health.Location.list()}" optionKey="id" required=""
		value="${patientInstance?.location?.id}" class="many-to-one" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: patientInstance, field: 'usages', 'error')} ">
	<label for="usages"> <g:message code="patient.usages.label"
			default="Usages" />

	</label>

	<ul class="one-to-many">
		<g:each in="${patientInstance?.usages?}" var="u">
			<li><g:link controller="usage" action="show" id="${u.id}">
					${u?.encodeAsHTML()}
				</g:link></li>
		</g:each>
		<li class="add"><g:link controller="usage" action="create"
				params="['patient.id': patientInstance?.id]">
				${message(code: 'default.add.label', args: [message(code: 'usage.label', default: 'Usage')])}
			</g:link></li>
	</ul>

</div>

