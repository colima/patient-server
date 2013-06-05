
package com.fusion.health

import static org.hamcrest.CoreMatchers.*
import static org.hamcrest.Matcher.*
import static org.junit.Assert.*
import static org.junit.matchers.JUnitMatchers.hasItems

import org.junit.*

class UsageIntegrationTests {

	private def TIME_PATTERN = "yyyy-MM-dd"
	private def location = new Location([code : 1, name : "Somewhere"])
	private def patient = new Patient([lastName:"Chaplin", middleName:"Spencer",firstName:"Charles",gender:Gender.Male,status:Patient.Status.Treatment,birth:new Date().parse(TIME_PATTERN,"1889-04-16"),location:location])
	private def usage_param = [patient:patient,status:Usage.Status.Pending,date:new Date(),AHI:12]
	private def excluded_usage = new Usage(usage_param+[excluded:true])
	private def not_excluded_usage = new Usage(usage_param+[excluded:false])

	@Before
	void setUp() {
		location.save(failOnError: true)
		patient.save(failOnError: true)
		excluded_usage.save(failOnError: true)
		not_excluded_usage.save(failOnError: true)
	}

	@After
	void tearDown() {
	}

	@Test
	void should_return_only_usages_not_excluded() {
		Usage.withHibernateFilter('notExcludedFilter'){
			Usage.findAll().each {
				assertThat(it.excluded,is(false))
			}
			assertThat(Usage.count(),is(1))
		}
	}
	@Test
	void should_not_return_any_usage() {
		Usage.findAll().each {
			it.excluded=true
			it.save(failOnError: true)
		}

		assertThat("It should not be fisical excluded",Usage.count(),is(2))
		Usage.withHibernateFilter('notExcludedFilter'){
			assertThat(Usage.count(),is(0))
		}
	}
	@Test
	void should_set_excluded_when_delete() {
		markAllAsNotExcluded()
		Usage.deleteAll(Usage.findAll())

		assertThat("It should not be fisical excluded",Usage.count(),is(2))
		Usage.findAll().each {
			assertThat(it.excluded,is(true))
		}
	}
	
	@Test
	void should_be_soft_deletation_on_hibernate_filter() {
		markAllAsNotExcluded()
		Usage.deleteAll(Usage.findAll())
		
		assertThat("It should not be fisical excluded",Usage.count(),is(2))
		Usage.withHibernateFilter('notExcludedFilter'){
			assertThat(Usage.count(),is(0))
		}
	}
	private markAllAsNotExcluded() {
		Usage.findAll().each {
			it.excluded=false
			it.save(failOnError: true)
		}
	}
	
}
