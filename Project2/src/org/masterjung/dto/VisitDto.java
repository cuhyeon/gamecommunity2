package org.masterjung.dto;

import java.sql.Date;

public class VisitDto {
	private int id;
	private Date visit_day;
	private String visit_ip;
	
	public VisitDto(String visit_ip) {
		super();
		this.visit_ip = visit_ip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getVisit_day() {
		return visit_day;
	}

	public void setVisit_day(Date visit_day) {
		this.visit_day = visit_day;
	}

	public String getVisit_ip() {
		return visit_ip;
	}

	public void setVisit_ip(String visit_ip) {
		this.visit_ip = visit_ip;
	}
	
	
}
