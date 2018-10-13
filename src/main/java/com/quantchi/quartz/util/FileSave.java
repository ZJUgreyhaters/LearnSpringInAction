package com.quantchi.quartz.util;

import com.quantchi.common.AppProperties;
import org.springframework.web.multipart.MultipartFile;

public class FileSave {

	private String filePath = "";
	protected String rootDir = AppProperties.getWithDefault("fileTpl.param.rootDir","");

	protected FileSave(){}

	public static FileSave of(String type, MultipartFile file) throws Exception {

		FileSave obj = null;

		switch (type){
			case "localpath":
				obj = new FileLocal(file);
				break;
			default:
				obj = new FileSave();
		}
		return obj;

	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
