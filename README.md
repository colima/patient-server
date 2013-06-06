Grails pet project of a Patient Management System with RESTfull services.

Get patient
curl -H "Accept: application/json" -H "Content-type: application/json" http://localhost:8080/patient-server/api/patient/1

Get all patient
curl -H "Accept: application/json" -H "Content-type: application/json" -X GET http://localhost:8080/patient-server/api/patient

Delete patient
curl -H "Accept: application/json" -H "Content-type: application/json" -X DELETE http://localhost:8080/patient-server/api/patient

Create patient
curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"birth": "1988-05-26","firstName": "Benoît","gender": "Female","lastName": "Mandelbrot","location": "1","middleName": "B.","status": "Treatment"}'  http://localhost:8080/patient-server/api/patient/1
