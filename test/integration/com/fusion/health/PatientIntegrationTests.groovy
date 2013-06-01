package com.fusion.health

import static org.junit.Assert.*

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matcher.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.hamcrest.CoreMatchers;
import org.junit.*

class PatientIntegrationTests {

	private def TIME_PATTERN = "yyyy-MM-dd"
	private def location = new Location(code : 1, name : "Somewhere") 
	private def chaplin = new Patient(lastName:"Chaplin", middleName:"Spencer",firstName:"Charles",gender:Gender.Male,status:Patient.Status.Treatment,birth:new Date().parse(TIME_PATTERN,"1889-04-16"),location:location)
	private def paganini = new Patient(lastName:"Paganini", middleName:"Paganini",firstName:"Niccolò",gender:Gender.Male,status:Patient.Status.Treatment,birth:new Date().parse(TIME_PATTERN,"1782-10-27"),location:location)
	private def bach = new Patient(lastName:"Bach", middleName:"Sebastian",firstName:"Johann",gender:Gender.Male,status:Patient.Status.Initial,birth:new Date().parse(TIME_PATTERN,"1685-03-31"),location:location) 
	private def hawking = new Patient(lastName:"Hawking", middleName:"William",firstName:"Stephen",gender:Gender.Male,status:Patient.Status.Closed,birth:new Date().parse(TIME_PATTERN,"1942-01-08"),location:location)
	
	
    @Before
    void setUp() {
		location.save(failOnError: true)
		chaplin.save(failOnError: true)
		paganini.save(failOnError: true)
		bach.save(failOnError: true)
		hawking.save(failOnError: true)
				
    }

    @After
    void tearDown() {
    }
	
    @Test
    void should_return_only_patient_on_treatment() {
        def onTreatment = Patient.onTreatment.list()
		assertThat(onTreatment,hasItems(paganini,chaplin))
		assertThat(onTreatment,not(hasItems(bach,hawking)))
    }
}
