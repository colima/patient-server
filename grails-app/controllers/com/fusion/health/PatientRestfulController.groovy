package com.fusion.health

import grails.converters.JSON;
import java.text.SimpleDateFormat;


import org.springframework.dao.DataIntegrityViolationException

class PatientRestfulController {

    def list(Integer max) {
		Patient.disableHibernateFilter('notExcludedFilter')
        params.max = Math.min(max ?: 10, 100)
        render Patient.list(params) as JSON
    }

    def save() {
        def patient = new Patient(params)
        if (!patient.save(flush: true)) {
			response.status = 201
			response.locale = "http://localhost:8080/patient/${this.id}"
			return
        }
    }

    def show(Long id) {
		Patient.disableHibernateFilter('notExcludedFilter')
        def patient = Patient.get(id)
		if(patient == null) {
			response.status = 404
			return
		}
        render patient as JSON
    }

    def update(Long id, Long version) {
        def patient = Patient.get(id)
        patient.properties = params
        if (!patient.save(flush: true)) {
			response.status = 200
			return
        }
    }

    def delete(Long id) {
        def patient = Patient.get(id)
        try {
            patient.delete(flush: true)
			response.status = 200
			return
        }
        catch (DataIntegrityViolationException e) {
			response.status = 500
			return
        }
    }
}
