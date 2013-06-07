Grails Patient Management System
--------------------------------

Patient Management System written in Grails with RESTfull API.


### API Manual Test

Get patients

curl -H "Accept: application/json" -H "Content-type: application/json" http://localhost:8080/patient-server/api/patient/1

Get all patients

curl -H "Accept: application/json" -H "Content-type: application/json" -X GET http://localhost:8080/patient-server/api/patient

Delete patients

curl -H "Accept: application/json" -H "Content-type: application/json" -X DELETE http://localhost:8080/patient-server/api/patient/1

Create patients

curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d   '{"birth": "1924-11-20",    "firstName": "Benoît",    "gender": "Male",    "lastName": "Mandelbrot",    "location": "1",    "middleName": "B.",    "status": "Treatment"}' http://localhost:8080/patient-server/api/patient

Update patients

curl -H "Accept: application/json" -H "Content-type: application/json" -X PUT -d   '{"birth": "1924-11-20",    "firstName": "Benoît",    "gender": "Male",    "lastName": "Mandelbrot",    "location": "1",    "middleName": "B.",    "status": "Treatment"}' http://localhost:8080/patient-server/api/patient/1

Create usage for a patient

curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"AHI": 10,"date": "2013-01-06","patient": 2,"status": "NonCompliant"}' http://localhost:8080/patient-server/api/patient/1/usage

Get all usages from patients

curl -H "Accept: application/json" -H "Content-type: application/json" http://localhost:8080/patient-server/api/patient/1/usage

### TODO

* Soft or Logic Deletation generalization, maybe a mixin for domain classes.
* Hypermedia RESTful.
* Functional Tests for RESTful API.

### BUGS

* When create a new usage for a patient, the Header Location from response is wrong