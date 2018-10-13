package com.quantchi.metadatamgr.aop;

import com.quantchi.common.DataSourceType;
import com.quantchi.common.DataSourceTypeManager;

public class DataSourceInterceptor {

	public void setDataSourceMaster(){
		DataSourceTypeManager.set(DataSourceType.MASTER);
	}

	public void setDataSourceSlave(){
		DataSourceTypeManager.set(DataSourceType.SLAVE);
	}

}
