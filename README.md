Grails Patient Management System
--------------------------------

Patient Management System written in Grails with RESTfull API.


### API Manual Test

Get patients

curl -H "Accept: application/json" -H "Content-type: application/json" http://localhost:8080/patient-server/api/patient/1

Get all patients

curl -H "Accept: application/json" -H "Content-type: application/json" -X GET http://localhost:8080/patient-server/api/patient

Delete patients

curl -H "Accept: application/json" -H "Content-type: application/json" -X DELETE http://localhost:8080/patient-server/api/patient

Create patients

curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"birth": "1988-05-26","firstName": "Benoît","gender": "Female","lastName": "Mandelbrot","location": "1","middleName": "B.","status": "Treatment"}'  http://localhost:8080/patient-server/api/patient/1

### TODO

* Soft or Logic Deletation generalization, maybe a mixin for domain classes.
* Hypermedia RESTful.
* Functional Tests for RESTful API.