package com.fusion.health

class SoftDeletationMixin {
	def beforeDelete(){
		Usage.withNewSession{
			Usage.executeUpdate("update Usage u set u.excluded = 1 where u.id = ?",[this.id])
		}
		return false
	}
}
