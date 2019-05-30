package org.masterjung.dto;

import java.sql.Date;

public class ReplyDto {
	private int id;
	private int reply_id;
	private Date date_created;
	private Date last_updated;
	private String reply_content;
	private String r_nick_name;
	private int vote_count;
	private int refer;
	private int depth;
	private int step;
	private int enabled;
	
	public ReplyDto(){}
	
	public ReplyDto(int reply_id, int vote_count) {
		super();
		this.reply_id = reply_id;
		this.vote_count = vote_count;
	}

	public ReplyDto(int reply_id, String reply_content, String r_nick_name, int refer, int depth, int step) {
		super();
		this.reply_id = reply_id;
		this.reply_content = reply_content;
		this.r_nick_name = r_nick_name;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public Date getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getR_nick_name() {
		return r_nick_name;
	}
	public void setR_nick_name(String r_nick_name) {
		this.r_nick_name = r_nick_name;
	}
	public int getVote_count() {
		return vote_count;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	
}
