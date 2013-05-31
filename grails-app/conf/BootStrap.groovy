import java.util.regex.Pattern.LastNode;

import com.fusion.health.Gender;
import com.fusion.health.Location;
import com.fusion.health.Patient;
import com.fusion.health.Status;

class BootStrap {

	def init = { servletContext ->
		development{
			if (!Location.count()) {
				new Location(code : 1, name : "Test location").save(failOnError: true)
				new Location(code : 2, name : "Fusion Sleep").save(failOnError: true)
			}
			if(!Patient.count()){
				new Patient(lastName:"Brum", middleName:"Henrique",firstName:"Mateus",gender:Gender.Male,status:Status.Initial,birth:new Date(),location:Location.get(1)).save(failOnError: true)
				new Patient(lastName:"Brum", middleName:"Karina",firstName:"Valesca",gender:Gender.Female,status:Status.Treatment,birth:new Date(),location:Location.get(1)).save(failOnError: true)
				new Patient(lastName:"Brum", middleName:"Henrique",firstName:"Iago",gender:Gender.Male,status:Status.Closed,birth:new Date(),location:Location.get(2)).save(failOnError: true)
			}
		}
		test{}
		production{}
	}
	def destroy = {
	}
}
