package com.fusion.health

class Patient {
	String lastName
	String middleName
	String firstName
	Date birth
	Gender gender
	Patient.Status status
	static hasOne = [location : Location]
	static hasMany = [usages : Usage]

	def medicalRecord() {
		numberCantExccedEightDigits()
		String formatedId = String.format("%08d",id)
		return "MR${formatedId}"
	}
	def compliance() {
		def c = this.usages.findAll{it.status == Usage.Status.Compliant}.size()
		def np = this.usages.findAll{it.status != Usage.Status.Pending}.size()
		return np==0?0:c/np
	}
	def effort(){
		def nc_c = this.usages.findAll{ 
			it.status == Usage.Status.NonCompliant || 
			it.status == Usage.Status.Compliant
		}.size()
		def np = this.usages.findAll{it.status != Usage.Status.Pending}.size()
		return np==0?0:nc_c/np
	}
	def fullName() {
		return "${firstName} ${middleName} ${lastName}"
	}

	static namedQueries={
		onTreatment { eq 'status' , Status.Treatment	 }
	}


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
	private def numberCantExccedEightDigits() {
		if(id.toString().length()>8) throw(RuntimeException)
	}
	public enum Status {
		Initial, Referred, Treatment, Closed
	}
}
