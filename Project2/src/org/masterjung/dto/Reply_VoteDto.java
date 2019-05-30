package org.masterjung.dto;

import java.sql.Date;

public class Reply_VoteDto {
	private int id;
	private int vote_id;
	private String reply_voter;
	private Date date_created;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVote_id() {
		return vote_id;
	}
	public void setVote_id(int vote_id) {
		this.vote_id = vote_id;
	}
	public String getReply_voter() {
		return reply_voter;
	}
	public void setReply_voter(String reply_voter) {
		this.reply_voter = reply_voter;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	
}
