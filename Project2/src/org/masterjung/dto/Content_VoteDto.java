package org.masterjung.dto;

import java.sql.Date;

public class Content_VoteDto {
	int id;
	int c_vote_id;
	String content_voter;
	Date date_created;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getC_vote_id() {
		return c_vote_id;
	}
	public void setC_vote_id(int c_vote_id) {
		this.c_vote_id = c_vote_id;
	}
	public String getContent_voter() {
		return content_voter;
	}
	public void setContent_voter(String content_voter) {
		this.content_voter = content_voter;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	
}
