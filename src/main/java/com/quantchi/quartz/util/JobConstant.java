package com.quantchi.quartz.util;

public class JobConstant {
	public static final String ACTIVE = "1";
	public static final String UNACTIVE = "0";

	public static final String ClzName = "com.quantchi.quartz.job.HiveCollectorJob";
	public static final String DEFAULTGROUP = "default";

	public static final String JOB_HIVE_COLLECTOR_AUTO_TYPE = "1";
	public static final String JOB_COLLECTOR_MANUAL_TYPE = "2";

	public static final String JOB_COLLECTOR_DOING = "1";
	public static final String JOB_COLLECTOR_DONE = "2";
	public static final String JOB_COLLECTOR_FAILED = "-1";

	public static final String INFLUXDBNAME="job.autogen.taskLog";

	public static final String TABLE_FILE_MANUAL_TYPE="table";
	public static final String FIELD_FILE_MANUAL_TYPE="field";

	public static final String BASICS_TARGET_FILE_MANUAL_TYPE="basicsTarget";
	public static final String DERIVE_TARGET_FILE_MANUAL_TYPE="deriveTarget";
	public static final String COMMON_TARGET_FILE_MANUAL_TYPE="commonTarget";

	public static final String CODE_STANDARD_FILE_MANUAL_TYPE="codeStandard";
	public static final String BASICS_STANDARD_FILE_MANUAL_TYPE="basicsStandard";
	public static final String TARGET_STANDARD_FILE_MANUAL_TYPE="targetStandard";

	public static final String TARGET_MAPPING_FILE_MANUAL_TYPE="targetMapping";
	public static final String STANDARD_MAPPING_FILE_MANUAL_TYPE="standardMapping";

}
