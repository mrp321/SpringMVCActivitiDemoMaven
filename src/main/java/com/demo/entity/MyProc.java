package com.demo.entity;

/**
 * 流程实体
 * 
 * @author qchen
 * 
 * 
 */
public class MyProc {
	private String procInstId;
	private String procDefId;

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	@Override
	public String toString() {
		return "MyProc [procInstId=" + procInstId + ", procDefId=" + procDefId
				+ "]";
	}

}
