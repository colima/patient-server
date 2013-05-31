package com.fusion.health



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Patient)
class PatientTests {

	@Test
	void should_medical_number_defined_as_MR00000001() {
		def params = [id:1]
		def patient = new Patient(params)
		assertEquals("MR00000001",patient.medicalRecord().toString())
	}

	@Test
	void should_medical_number_defined_as_MR99988876() {
		def params = [id:99988876]
		def patient = new Patient(params)
		assertEquals("MR99988876",patient.medicalRecord().toString())
	}
	@Test
	void should_medical_number_thow_execption_when_id_greatter_than_eight_digitis() {
		shouldFail(RuntimeException) {
			def params = [id:9999999999999999]
			def patient = new Patient(params)
			patient.medicalRecord()
		}
	}
}
