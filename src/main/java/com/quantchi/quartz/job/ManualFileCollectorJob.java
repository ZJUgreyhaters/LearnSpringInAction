package com.quantchi.quartz.job;


import com.quantchi.common.ExcelUtil;
import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.lineage.diff.Table;
import com.quantchi.metadatamgr.extract.ManualFileExtractor;
import com.quantchi.metadatamgr.service.MetaDataDiffService;
import com.quantchi.metadatamgr.service.MetaDataMgrApiService;
import com.quantchi.metadatamgr.service.MetaDataMgrTableApiService;
import com.quantchi.quartz.entity.JobInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class ManualFileCollectorJob extends CollectorJob{

	private static final Logger logger = LoggerFactory.getLogger(ManualFileCollectorJob.class);

	public ManualFileCollectorJob(){
		super();
	}

	@Override
	public void runCollectorTask(JobInfo jobInfo) throws Exception {
		logger.info(jobInfo.getName() + " job call");

		//load file
		List<String[]> result = null;
		File file = new File(jobInfo.getJobParams().get("filepath").toString());
		FileInputStream in_file = new FileInputStream(file);
		MultipartFile multi = new MockMultipartFile(file.getName(),file.getName(), (String)null, (byte[]) FileCopyUtils.copyToByteArray(in_file));

		result = ExcelUtil.readExcel(multi);
		logger.info("load exec over job call");
		List<List<String>> rets = result.stream().map(i-> Arrays.asList(i)).collect(Collectors.toList());
		boolean isSuccess =  ManualFileExtractor.of(jobInfo.getJobParams().get("fileTpl").toString(),rets).extract();
		logger.info("finish {} job ,and result is:{}",jobInfo.getName(),isSuccess);
	}

}
