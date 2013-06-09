Grails Patient Management System
--------------------------------

Patient Management System written in Grails with RESTfull API.

### Installation

$ git clone git://github.com/mateushenriquebrum/patient-server.git

$ cd patient-server

$ grails grails install-plugin hibernate-filter

$ grails test-app

$ grails run-app



### API Manual Test

**Patients Version 2**

Get patients

curl -H "Accept: vnd.com.fusion.v2+json" -H "Content-type: vnd.com.fusion.v2+json" http://localhost:8080/patient-server/api/patient/1

Get all patients

curl -H "Accept: vnd.com.fusion.v2+json" -H "Content-type: vnd.com.fusion.v2+json" -X GET http://localhost:8080/patient-server/api/patient

Delete patients

curl -H "Accept: vnd.com.fusion.v2+json" -H "Content-type: vnd.com.fusion.v2+json" -X DELETE http://localhost:8080/patient-server/api/patient/1

Create patients

curl -H "Accept: vnd.com.fusion.v2+json" -H "Content-Type: vnd.com.fusion.v2+json" -X POST -d   '{"birth": "1924-11-20",    "firstName": "Benoît",    "gender": "Male",    "lastName": "Mandelbrot",    "location": "1",    "middleName": "B.",    "status": "Treatment"}' http://localhost:8080/patient-server/api/patient

Update patients

curl -H "Accept: vnd.com.fusion.v2+json" -H "Content-type: vnd.com.fusion.v2+json" -X PUT -d   '{"birth": "1924-11-20",    "firstName": "Benoît",    "gender": "Male",    "lastName": "Mandelbrot",    "location": "1",    "middleName": "B.",    "status": "Treatment"}' http://localhost:8080/patient-server/api/patient/1

**Patients Version 1**

Just change Accept and Content-type to mimetype vnd.com.fusion.v1+json, eg.

curl -H "Accept: vnd.com.fusion.v1+json" -H "Content-type: vnd.com.fusion.v1+json" http://localhost:8080/patient-server/api/patient/1
curl -H "Accept: vnd.com.fusion.v1+json" -H "Content-type: vnd.com.fusion.v1+json" http://localhost:8080/patient-server/api/patient/

*Version interchangeable work only for get by id and get all*

**Usages**

Create usage for a patient

curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"AHI": 10,"date": "2013-01-06","patient": 2,"status": "NonCompliant"}' http://localhost:8080/patient-server/api/patient/1/usage

Get all usages from patients

curl -H "Accept: application/json" -H "Content-type: application/json" http://localhost:8080/patient-server/api/patient/1/usage

### TODO

* Generalize SoftDelete, maybe a mixin for domain classes.
* Hypermedia RESTful, just follow the links to get, delete and edit Usages, for example.
* Functional Tests for RESTful API, currently it is manual.

### BUGS

* When create a new Usage for a Patient, the Header Location of response is wrong.
* Hibernate Filter not automatic installed by grails, it's the reason why you have to install it manual.
