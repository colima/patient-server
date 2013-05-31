package com.fusion.health

class Location {
	
	String code
	String name
	
	static hasMany = [patient:Patient]

    static constraints = {
		code maxSize:10
		name blank:false, maxSize:80	 
    }
	
	@Override
	public String toString() {
		return "${name} ${code}"
	}
}
