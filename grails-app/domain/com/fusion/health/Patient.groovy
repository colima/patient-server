package com.fusion.health

import groovy.time.TimeCategory;

class Patient {

	String lastName
	String middleName
	String firstName
	Date birth
	Gender gender
	Patient.Status status
	static hasOne = [location : Location]
	static hasMany = [usages : Usage]
	
	def getAge(){
		def now = new Date()
		return now.year-birth.year
	}

	def getMedicalRecord() {
		numberCantExceedEightDigits()
		return "MR${String.format('%08d',id)}"
	}
	
	def getLastAHIIndex(){
		return latestAHI()?.AHI ?: null
	}
	def getLastAHIDate(){
		return latestAHI()?.date ?: null
	}
	
	def private latestAHI(){
		def validUsages = usages.findAll{it.AHI!=0}
		if(!validUsages) return null
		return validUsages.max{a,b -> a.date <=> b.date}
	}

	def getCompliance() {
		def c = this.usages.findAll{it.status == Usage.Status.Compliant}.size()
		def np = this.usages.findAll{it.status != Usage.Status.Pending}.size()
		def compliance = (np==0?0.0:(c/np)*100)
		return compliance.setScale(2,BigDecimal.ROUND_HALF_UP)
	}
	
	def getEffort(){
		def nc_c = this.usages.findAll{ 
			it.status == Usage.Status.NonCompliant || 
			it.status == Usage.Status.Compliant
		}.size()
		def np = this.usages.findAll{it.status != Usage.Status.Pending}.size()
		def effort = (np==0?0.0:(nc_c/np)*100)
		return effort.setScale(2,BigDecimal.ROUND_HALF_UP)
	}

	def getFullName() {
		return "${lastName}, ${firstName} ${middleName}"
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
		return getFullName()
	}

	private def numberCantExceedEightDigits() {
		if(id.toString().length()>8) throw new RuntimeException("Could you forget to change MR max digits ?")
	}

	public enum Status {
		Initial, Referred, Treatment, Closed
	}
}