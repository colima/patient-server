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
		def patients = Patient.list(params)
		request.withFormat{
			fusion1{
				JSON.registerObjectMarshaller(Patient){Patient patient -> return v1(patient)}
				render patients as JSON
			}
			fusion2{
				JSON.registerObjectMarshaller(Patient){Patient patient -> return v2(patient)}
				render patients as JSON
			}
		}
	}
	
	def save() {
		JSON.registerObjectMarshaller(Patient){Patient p -> return v2(p)}
		
		def par = createParams()
		def patient = new Patient(par)
		
		if(patient.save(flush: true)){
			response.setHeader('Location',locationURL(patient))
			response.status = 201
			render ""
		}else{
			render(status : 500)
		}
	}

	def show(Long id) {
		Patient.disableHibernateFilter('notExcludedFilter')
		def patient = Patient.get(id)
		request.withFormat{
			fusion1{
				JSON.registerObjectMarshaller(Patient){Patient p -> return v1(p)}
				if(patient == null) {
					render(status:404)
				}else{
					render patient as JSON
				}
			}
			fusion2{
				JSON.registerObjectMarshaller(Patient){Patient p -> return v2(p)}
				if(patient == null) {
					render(status:404)
				}else{
					render patient as JSON
				}
			}
		}
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
			render (status:200)
		}
		catch (DataIntegrityViolationException e) {
			render (status:500)
		}
	}
	def v1(Patient patient) { 
		return 	[
				id 			: 	patient.id,
				firstName 	: 	patient.firstName,
				middleName 	: 	patient.middleName,
				lastName 	: 	patient.lastName,
				birth 		: 	patient.birth,
				gender 		: 	patient.gender.toString(),
				status 		: 	patient.status.toString(),
				location 	: 	patient.location.toString(),
				]
	}
	def v2(Patient patient) {
		return [
				id			:		patient.id,
				fullName	:		patient.fullName,
				birth		:		patient.birth,
				gender		:		patient.gender.toString(),
				status		:		patient.status.toString(),
				location	:		patient.location.toString(),
				excluded	:		patient.excluded
				]
	}
}
