package com.fusion.health

class Usage {

	Date date
	Usage.Status status
	int AHI
	boolean excluded

	static constraints = {
		patient blank:false
		date blank:false
		status blank:false
	}

	static belongsTo = [patient : Patient]
	
	static hibernateFilters = {
		notExcludedFilter(condition:'excluded=0', default:true)
	}
	def beforeDelete(){
		Usage.withNewSession{
			Usage.executeUpdate("update Usage u set u.excluded = 1 where u.id = ?",[this.id])
		}
		return false
	}
	public enum Status{
		Compliant,NonCompliant,NotUsed,Pending	
	}
}