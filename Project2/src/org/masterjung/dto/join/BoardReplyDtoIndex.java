package org.masterjung.dto.join;

import java.sql.Date;

public class BoardReplyDtoIndex {
	//board 테이블
	private int id;
	private String board_list_id;
	private String nick_name;
	private int anonymity; 
	private String create_ip;
	private int enabled; //default
	private int refer;
	private int depth;
	private int step; 
	private Date date_created; //default
	private Date last_updated; //default
	private int view_count;
	private int vote_count;
	private	String title;
	private String content;
	private String file_path;
	private String user_image_path;
	private int recommand;

	
	
	public int getRecommand() {
		return recommand;
	}
	public void setRecommand(int recommand) {
		this.recommand = recommand;
	}
	public String getUser_image_path() {
		return user_image_path;
	}
	public void setUser_image_path(String user_image_path) {
		this.user_image_path = user_image_path;
	}
	private int reply_count;
	
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
	public String getBoard_list_id() {
		return board_list_id;
	}
	public void setBoard_list_id(String board_list_id) {
		this.board_list_id = board_list_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public int getAnonymity() {
		return anonymity;
	}
	public void setAnonymity(int anonymity) {
		this.anonymity = anonymity;
	}
	public String getCreate_ip() {
		return create_ip;
	}
	public void setCreate_ip(String create_ip) {
		this.create_ip = create_ip;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getVote_count() {
		return vote_count;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	
}
