package com.quantchi.metadatamgr.data.entity;

import java.util.Date;

public class MDSchemaDiff {
	private String datasourceId;

	private String version;

	private String baseline;

	private String submitter;

	private Date date;

	private Short additions;

	private Short deletions;

	private Short changes;

	private String desc;

	private String diff;

	private Integer tableAmount;

	private Integer fieldAmount;

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public Integer getTableAmount() {
        return tableAmount;
    }

	public void setTableAmount(Integer tableAmount) {
			this.tableAmount = tableAmount;
	}

	public Integer getFieldAmount() {
			return fieldAmount;
	}

	public void setFieldAmount(Integer fieldAmount) {
			this.fieldAmount = fieldAmount;
	}

	public String getVersion() {
			return version;
	}

	public void setVersion(String version) {
			this.version = version == null ? null : version.trim();
	}

	public String getBaseline() {
			return baseline;
	}

	public void setBaseline(String baseline) {
			this.baseline = baseline == null ? null : baseline.trim();
	}

	public String getSubmitter() {
			return submitter;
	}

	public void setSubmitter(String submitter) {
			this.submitter = submitter == null ? null : submitter.trim();
	}

	public Date getDate() {
			return date;
	}

	public void setDate(Date date) {
			this.date = date;
	}

	public Short getAdditions() {
			return additions;
	}

	public void setAdditions(Short additions) {
			this.additions = additions;
	}

	public Short getDeletions() {
			return deletions;
	}

	public void setDeletions(Short deletions) {
			this.deletions = deletions;
	}

	public Short getChanges() {
			return changes;
	}

	public void setChanges(Short changes) {
			this.changes = changes;
	}

	public String getDesc() {
			return desc;
	}

	public void setDesc(String desc) {
			this.desc = desc == null ? null : desc.trim();
	}

	public String getDiff() {
			return diff;
	}

	public void setDiff(String diff) {
			this.diff = diff == null ? null : diff.trim();
	}
}