import com.fusion.health.PatientRestfulController;

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/api/patient/$id?"(controller : "patientRestful"){
			action = [GET:"show",DELETE:"delete",PUT:"update"]
		}
		"/api/patient" (controller : "patientRestful"){
			action = [GET:"list",POST:"save"]
		}

		"/api/patient/$idp/usage/$id?"(controller : "usageRestful"){
			action = [GET:"show",DELETE:"delete",PUT:"update"]
		}
		"/api/patient/$idp/usage"(controller : "usageRestful"){
			action = [GET:"list",POST:"save"]
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
