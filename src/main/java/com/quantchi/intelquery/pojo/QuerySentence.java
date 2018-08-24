package com.quantchi.intelquery.pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;
import java.util.UUID;

public class QuerySentence {

	@Field
	private String id = UUID.randomUUID().toString().replace("-", "");;
	@Field
	private int count;
	@Field
	private String username;
	@Field
	private String businessName;
	@Field
	private	String query;
	@Field
	private Date	datetime = new Date();
	@Field
	private Boolean isParseable = false;
	@Field
	private String querySql;
	@Field
	private String feedback = "";
	@Field
	private String intelqueryVer;

	public String getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

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
