package com.fusion.health

import grails.converters.JSON;
import java.text.SimpleDateFormat;


import org.codehaus.groovy.grails.web.mapping.LinkGenerator;
import org.springframework.dao.DataIntegrityViolationException

class PatientRestfulController {

	LinkGenerator grailsLinkGenerator
	
	def list(Integer max) {
		Patient.disableHibernateFilter('notExcludedFilter')
		params.max = Math.min(max ?: 10, 100)
		render Patient.list(params) as JSON
	}
	
	def save() {
		def p = createParams()
		def patient = new Patient(p)
		
		if(patient.save(flush: true)){
			response.setHeader('Location',locationURL(patient))
			response.status = 201
		}else{
			response.status = 500
			render(patient.errors)
		}
		render ""
		return
		
	}

	def show(Long id) {
		Patient.disableHibernateFilter('notExcludedFilter')
		def patient = Patient.get(id)
		if(patient == null) {
			response.status = 404
			render ""
			return
		}
		render patient as JSON
	}

	def update(Long id) {
		def patient = Patient.get(id)
		patient.properties = createParams()
		
		if (patient.save(flush: true)) {
			response.setHeader('Location',locationURL(patient))
			response.status = 200
		}
		render ""			
	}
	
	private def createParams(){
		def json = JSON.parse(request)
		def location = Location.get(json.location)
		def birth = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(json.birth)
		return json+[location:location,birth:birth]
	} 
	
	private def locationURL(patient){
		return grailsLinkGenerator.link(controller : "patientRestful",id:patient.id,action:'show',absolute:true)
	}

	def delete(Long id) {
		def patient = Patient.get(id)
		try {
			patient.delete(flush: true)
			response.status = 200
			render ""
		}
		catch (DataIntegrityViolationException e) {
			response.status = 500
			render ""
		}
	}
}
