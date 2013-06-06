package com.fusion.health

import grails.converters.JSON;
import java.text.SimpleDateFormat;


import org.codehaus.groovy.grails.web.mapping.LinkGenerator;
import org.springframework.dao.DataIntegrityViolationException

class UsageRestfulController {

	LinkGenerator grailsLinkGenerator

	def list(Long idp,Integer max) {
		Usage.disableHibernateFilter('notExcludedFilter')
		def p = createParams()
		def patient = Patient.get(idp) 
		
		if(patient==null){
			render(status:404)
			return
		}		
		render patient.usages as JSON
		return
	}

	def save(Long idp) {
		def p = createParams()
		def usage = new Usage(p)
		def patient = Patient.get(idp)
		usage.patient = patient

		if(usage.save(flush: true)){
			response.setHeader('Location',locationURL(patient,usage))
			response.status = 201
		}else{
			response.status = 500
			render(usage.errors)
		}
		render ""
		return
	}

	def show(Long id) {
		Usage.disableHibernateFilter('notExcludedFilter')
		def patient = Usage.get(id)
		if(patient == null) {
			response.status = 404
			render ""
			return
		}
		render patient as JSON
	}

	private def createParams(){
		return JSON.parse(request)
	}
	private def locationURL(patient,usage){
		return grailsLinkGenerator.link(controller : "usageRestful",idp:patient.id,id:usage.id,action:'show',absolute:true)
	}
}
