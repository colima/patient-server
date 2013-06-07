package com.fusion.health

import grails.converters.JSON;
import java.text.SimpleDateFormat;


import org.codehaus.groovy.grails.web.mapping.LinkGenerator;
import org.springframework.dao.DataIntegrityViolationException

class UsageRestfulController {

	LinkGenerator grailsLinkGenerator

	def list(Long idp) {
		Usage.disableHibernateFilter('notExcludedFilter')
		def patient = Patient.get(idp) 
		
		if(patient==null){
			render(status:404)
			return
		}		
		render patient.usages as JSON
		return
	}

	def save(Long idp) {
		def p = createParams(idp)
		def usage = new Usage(p)

		if(usage.save(flush: true)){
			response.setHeader('Location',locationURL(usage.patient,usage))
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
		def usage = Usage.get(id)
		if(usage == null) {
			response.status = 404
			render ""
			return
		}
		render usage as JSON
	}

	private def createParams(idp){
		def json = JSON.parse(request)
		def patient = Patient.get(idp)
		def date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(json.date)
		return json+[date:date,patient:patient]
	}
	private def locationURL(patient,usage){
		return grailsLinkGenerator.link(controller : "usageRestful",idp:patient.id,id:usage.id,action:'show',absolute:true)
	}
}
