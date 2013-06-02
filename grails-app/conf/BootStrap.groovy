import com.fusion.health.Gender
import com.fusion.health.Location
import com.fusion.health.Patient
import com.fusion.health.Usage
import com.fusion.health.Patient.Status

class BootStrap {

	def init = { servletContext ->
		development{
			def date = new java.text.SimpleDateFormat("yyyy-MM-dd")
			if (!Location.count()) {
				new Location(code : 1, name : "Test location").save(failOnError: true)
				new Location(code : 2, name : "Fusion Sleep").save(failOnError: true)
			}
			if(!Patient.count()){
				new Patient(lastName:"Brum", middleName:"Henrique",firstName:"Mateus",gender:Gender.Male,status:Patient.Status.Initial,birth:date.parse("1982-01-10"),location:Location.get(1)).save(failOnError: true)
				new Patient(lastName:"Brum", middleName:"Karina",firstName:"Valesca",gender:Gender.Female,status:Patient.Status.Treatment,birth:date.parse("1988-05-26"),location:Location.get(1)).save(failOnError: true)
				new Patient(lastName:"Brum", middleName:"Henrique",firstName:"Iago",gender:Gender.Male,status:Patient.Status.Closed,birth:date.parse("2004-01-29"),location:Location.get(2)).save(failOnError: true)
			}
			if(!Usage.count()){
				new Usage([status:Usage.Status.Pending,date:date.parse("2013-01-01"),AHI:12,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-02"),AHI:13,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.NonCompliant,date:date.parse("2013-01-03"),AHI:15,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.NotUsed,date:date.parse("2013-01-04"),AHI:12,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-05"),AHI:10,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-06"),AHI:14,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-07"),AHI:10,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-08"),AHI:15,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-09"),AHI:11,patient:Patient.get(1)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-10"),AHI:10,patient:Patient.get(1)]).save(failOnError: true)

				new Usage([status:Usage.Status.Pending,date:date.parse("2013-01-01"),AHI:12,patient:Patient.get(2)]).save(failOnError: true)
				new Usage([status:Usage.Status.Compliant,date:date.parse("2013-01-02"),AHI:13,patient:Patient.get(2)]).save(failOnError: true)
				new Usage([status:Usage.Status.NonCompliant,date:date.parse("2013-01-03"),AHI:15,patient:Patient.get(2)]).save(failOnError: true)
				new Usage([status:Usage.Status.NotUsed,date:date.parse("2013-01-04"),AHI:12,patient:Patient.get(2)]).save(failOnError: true)
				new Usage([status:Usage.Status.Pending,date:date.parse("2013-01-05"),AHI:10,patient:Patient.get(2)]).save(failOnError: true)
				new Usage([status:Usage.Status.NonCompliant,date:date.parse("2013-01-06"),AHI:10,patient:Patient.get(2)]).save(failOnError: true)
				new Usage([status:Usage.Status.NonCompliant,date:date.parse("2013-01-07"),AHI:12,patient:Patient.get(2)]).save(failOnError: true)
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
