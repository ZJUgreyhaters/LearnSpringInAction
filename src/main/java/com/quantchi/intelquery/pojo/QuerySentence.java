package com.quantchi.intelquery.pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;
import java.util.UUID;

public class QuerySentence {

	@Field("id")
	private String id = UUID.randomUUID().toString().replace("-", "");;
	@Field("times")
	private Long count = 1L;
	@Field("username")
	private String username;
	@Field("businessId")
	private String businessId;
	@Field("query")
	private	String query;
	@Field("add_time")
	private Date	datetime = new Date();
	@Field("isParseable")
	private Boolean isParseable = false;
	@Field("querySql")
	private String querySql;
	@Field("feedback")
	private String feedback = "";
	@Field("intelqueryVer")
	private String intelqueryVer;

	@Field("favor")
	private Long likeNums = 0L;

	@Field("disfavor")
	private Long dislikeNums = 0L;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getLikeNums() {
		return likeNums;
	}

	public void setLikeNums(Long likeNums) {
		this.likeNums = likeNums;
	}

	public void addLikeNums() {
		this.likeNums++;
	}

	public Long getDislikeNums() {
		return dislikeNums;
	}

	public void setDislikeNums(Long dislikeNums) {
		this.dislikeNums = dislikeNums;
	}

	public void addDislikeNums() {
		this.dislikeNums++;
	}

	public String getId() {
		return id;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void addCount() {
		this.count++;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	/*public String getQuerySeg() {
		return querySeg;
	}

	public void setQuerySeg(String querySeg) {
		this.querySeg = querySeg;
	}*/

	public Boolean getParseable() {
		return isParseable;
	}

	public void setParseable(Boolean parseable) {
		isParseable = parseable;
	}

	public String getQuerySql() {
		return querySql;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getIntelqueryVer() {
		return intelqueryVer;
	}

	public void setIntelqueryVer(String intelqueryVer) {
		this.intelqueryVer = intelqueryVer;
	}
}
