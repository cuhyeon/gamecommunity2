package org.masterjung.dto.join;

import java.sql.Date;

public class ReplyJoinReplyVoteDto {	
	//reply 테이블
	private int id;
	private int reply_id; //id랑 매핑
	private Date date_created;
	private Date last_updated;
	private String reply_content;
	private String r_nick_name;
	private int vote_count;
	private int refer;
	private int depth;
	private int step;
	private int enabled;
	private int reply_count;
	
	//reply_vote 테이블
	private int rv_id;
	private int rv_vote_id; //reply.reply_id랑 매핑
	private String rv_reply_voter;
	private Date rv_date_created;
	private String u_user_image_path;
	
	public ReplyJoinReplyVoteDto(){}

	public ReplyJoinReplyVoteDto(int id, int reply_id, String reply_content, String r_nick_name, int vote_count,
			int rv_id, int rv_vote_id, String rv_reply_voter) {
		super();
		this.id = id;
		this.reply_id = reply_id;
		this.reply_content = reply_content;
		this.r_nick_name = r_nick_name;
		this.vote_count = vote_count;
		this.rv_id = rv_id;
		this.rv_vote_id = rv_vote_id;
		this.rv_reply_voter = rv_reply_voter;
	}



	public ReplyJoinReplyVoteDto(int id, int reply_id, String reply_content, String r_nick_name, int vote_count,
			int refer, int depth, int step, int rv_id, int rv_vote_id, String rv_reply_voter) {
		super();
		this.id = id;
		this.reply_id = reply_id;
		this.reply_content = reply_content;
		this.r_nick_name = r_nick_name;
		this.vote_count = vote_count;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.rv_id = rv_id;
		this.rv_vote_id = rv_vote_id;
		this.rv_reply_voter = rv_reply_voter;
	}


	public String getU_user_image_path() {
		return u_user_image_path;
	}

	public void setU_user_image_path(String u_user_image_path) {
		this.u_user_image_path = u_user_image_path;
	}

	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
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

	public int getRv_id() {
		return rv_id;
	}

	public void setRv_id(int rv_id) {
		this.rv_id = rv_id;
	}

	public int getRv_vote_id() {
		return rv_vote_id;
	}

	public void setRv_vote_id(int rv_vote_id) {
		this.rv_vote_id = rv_vote_id;
	}

	public String getRv_reply_voter() {
		return rv_reply_voter;
	}

	public void setRv_reply_voter(String rv_reply_voter) {
		this.rv_reply_voter = rv_reply_voter;
	}

	public Date getRv_date_created() {
		return rv_date_created;
	}

	public void setRv_date_created(Date rv_date_created) {
		this.rv_date_created = rv_date_created;
	}
	
	
	
}