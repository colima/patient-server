package com.fusion.health

class Usage {

	Date date
	Usage.Status status
	int AHI

	static constraints = {
		patient blank:false
		date blank:false
		status blank:false
	}

	static belongsTo = [patient : Patient]

	public enum Status{
		Compliant,NonCompliant,NotUsed,Pending
	}


	// TODO : Generalize for all classes domain
	boolean excluded

	static hibernateFilters = {
		notExcludedFilter(condition:"excluded='0'", default:true)
	}

	def beforeDelete(){
		Usage.withNewSession{
			Usage.executeUpdate("update Usage u set u.excluded = true where u.id = ?",[this.id])
		}
		return false
	}
}