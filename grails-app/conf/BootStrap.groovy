import com.fusion.health.Gender
import com.fusion.health.Location
import com.fusion.health.Patient
import com.fusion.health.Usage
import com.fusion.health.Patient.Status
import grails.converters.JSON

class BootStrap {

	def init = { servletContext ->
		def DATE_PATTERN = "yyyy-MM-dd"

		JSON.registerObjectMarshaller(Date) {
			return it?.format(DATE_PATTERN)
		}

		JSON.registerObjectMarshaller(Usage){ Usage usage ->
			return[
				id 			: 	usage.id,
				AHI 		: 	usage.AHI,
				date		:	usage.date,
				patient		:	usage.patient.id,
				status		: 	usage.status.toString(),
				excluded	:	usage.excluded
			]
		}

		development{
			def formatter = new java.text.SimpleDateFormat("yyyy-MM-dd")
			def l1
			def l2
			def p1
			def p2
			def p3
			

			if (!Location.count()) {
				Location.withTransaction {
					l1 = new Location(code : 1, name : "Test location").save(failOnError: true)
					l2 = new Location(code : 2, name : "Fusion Sleep").save(failOnError: true)
				}
			}
			if(!Patient.count()){
				Patient.withTransaction {
					p1 = new Patient(lastName:"Newton", middleName:"Issac",firstName:"Sir",gender:Gender.Male,status:Patient.Status.Initial,birth:formatter.parse("1643-01-04"),location:l1).save(failOnError: true)
					p2 = new Patient(lastName:"Leibniz", middleName:"Wilhelm",firstName:"Gottfried",gender:Gender.Male,status:Patient.Status.Treatment,birth:formatter.parse("1646-07-01"),location:l1).save(failOnError: true)
					p3 = new Patient(lastName:"Hawking", middleName:"William",firstName:"Stephen",gender:Gender.Male,status:Patient.Status.Closed,birth:formatter.parse("1942-01-08"),location:l1).save(failOnError: true)
				}
			}
			if(!Usage.count()){
				Usage.withTransaction {
					new Usage([status:Usage.Status.Pending,date:formatter.parse("2013-01-01"),AHI:12,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-02"),AHI:13,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.NonCompliant,date:formatter.parse("2013-01-03"),AHI:15,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.NotUsed,date:formatter.parse("2013-01-04"),AHI:12,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-05"),AHI:10,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-06"),AHI:14,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-07"),AHI:10,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-08"),AHI:15,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-09"),AHI:11,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-10"),AHI:10,patient:p1]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-11"),AHI:12,patient:p1]).save(failOnError: true)

					new Usage([status:Usage.Status.Pending,date:formatter.parse("2013-01-01"),AHI:12,patient:p2]).save(failOnError: true)
					new Usage([status:Usage.Status.Compliant,date:formatter.parse("2013-01-02"),AHI:13,patient:p2]).save(failOnError: true)
					new Usage([status:Usage.Status.NonCompliant,date:formatter.parse("2013-01-03"),AHI:15,patient:p2]).save(failOnError: true)
					new Usage([status:Usage.Status.NotUsed,date:formatter.parse("2013-01-04"),AHI:12,patient:p2]).save(failOnError: true)
					new Usage([status:Usage.Status.Pending,date:formatter.parse("2013-01-05"),AHI:10,patient:p2]).save(failOnError: true)
					new Usage([status:Usage.Status.NonCompliant,date:formatter.parse("2013-01-06"),AHI:10,patient:p2]).save(failOnError: true)
					new Usage([status:Usage.Status.NonCompliant,date:formatter.parse("2013-01-07"),AHI:12,patient:p2]).save(failOnError: true)
				}
			}
		}
		test{
		}
		production{
		}
	}
	def destroy = {
	}
}
