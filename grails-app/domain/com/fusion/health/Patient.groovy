package com.fusion.health

class Patient {
	String lastName
	String middleName
	String firstName
	Date birth
	Gender gender
	Status status
	
	def medicalRecord() {
		numberCantExccedEightDigits()
		String formatedId = String.format("%08d",id)
		return "MR${formatedId}"
	}

	private numberCantExccedEightDigits() {
		if(id.toString().length()>8) throw(RuntimeException)
	}
	def fullName() {return "${firstName} ${middleName} ${lastName}"}
	
	static namedQueries={
		onTreatment {
			eq 'status' , Status.Treatment	
		}
	}
	
	static hasOne = [location : Location]
	static hasMany = [usage : Usage]

    static constraints = {
		lastName blank:false, maxSize:30
		middleName blank:false, maxSize:10
		firstName blank:false, maxSize:30
		birth blank:false
		gender blank:false
		status blank:false
		id bindable:true, maxSize:8
    }
	@Override
	public String toString() {
		return fullName()
	}
}
