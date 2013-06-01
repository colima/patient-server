


package com.fusion.health

import java.text.SimpleDateFormat;
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
	
	@Test
	void should_computate_compliance(){
		def patient = new Patient()
		patient.usages = usages()
		assertEquals(0.5,patient.compliance())
	}
	
	@Test
	void should_computate_effort(){
		def patient = new Patient()
		patient.usages = usages()
		assertEquals(3/4,patient.effort())
	}
	
	@Test
	void should_effort_return_zero_on_lack_of_status(){
		def patient = new Patient()
		assertEquals(0,patient.effort())
	}
	
	@Test
	void should_compliance_return_zero_on_lack_of_status(){
		def patient = new Patient()
		assertEquals(0,patient.effort())
	}
	
	@Test
	void should_return_latest_AHI_index_and_date(){
		def patient = new Patient()
		patient.usages = usages()
		
		assertEquals(10,patient.latestAHI().index)
		assertEquals(date("2013-01-05"),patient.latestAHI().date)
		
	}
	
	@Test
	void should_return_for_null_usages(){
		def patient = new Patient()		
		assertNull(patient.latestAHI())
	}
	
	@Test
	void should_return_for_invalides_usages(){
		def dayOneUsage = new Usage([status:Usage.Status.Pending,date:date("2013-01-01")])
		def dayTwoUsage = new Usage([status:Usage.Status.Compliant,date:date("2013-01-02")])
		def patient = new Patient([usages:[dayOneUsage,dayTwoUsage]])
		assertNull(patient.latestAHI())
	}
	
	private def usages(){
		
		return [
			new Usage([status:Usage.Status.Pending,date:date("2013-01-01"),AHI:12]),
			new Usage([status:Usage.Status.Compliant,date:date("2013-01-02"),AHI:13]),
			new Usage([status:Usage.Status.NonCompliant,date:date("2013-01-03"),AHI:15]),
			new Usage([status:Usage.Status.NotUsed,date:date("2013-01-04"),AHI:12]),
			new Usage([status:Usage.Status.Compliant,date:date("2013-01-05"),AHI:10])
		]
	}
	
	def date(toParse){
		return new SimpleDateFormat("yyyy-MM-dd").parse(toParse)
	}
}
