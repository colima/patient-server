<%@ page import="com.fusion.health.Usage" %>



<div class="fieldcontain ${hasErrors(bean: usageInstance, field: 'patient', 'error')} required">
	<label for="patient">
		<g:message code="usage.patient.label" default="Patient" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="patient" name="patient.id" from="${com.fusion.health.Patient.list()}" optionKey="id" required="" value="${usageInstance?.patient?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usageInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="usage.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${usageInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: usageInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="usage.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${com.fusion.health.Usage$Status?.values()}" keys="${com.fusion.health.Usage$Status.values()*.name()}" required="" value="${usageInstance?.status?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usageInstance, field: 'AHI', 'error')} required">
	<label for="AHI">
		<g:message code="usage.AHI.label" default="AHI" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="AHI" type="number" value="${usageInstance.AHI}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: usageInstance, field: 'excluded', 'error')} ">
	<label for="excluded">
		<g:message code="usage.excluded.label" default="Excluded" />
		
	</label>
	<g:checkBox name="excluded" value="${usageInstance?.excluded}" />
</div>

