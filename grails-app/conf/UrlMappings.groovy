import com.fusion.health.PatientRestfulController;

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/api/patient/$id" {
			controller = "patientRestful"
			action = [GET:"show"]
		}
		"/api/patient" {
			controller = "patientRestful"
			action = [GET:"list"]
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
