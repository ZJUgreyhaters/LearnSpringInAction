package com.quantchi.metadatamgr.data.entity;

import com.quantchi.common.TreeNode;

public class MDMountNodeInfo  extends TreeNode{

	private String description;

	private String entityId;

	private String type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
}
