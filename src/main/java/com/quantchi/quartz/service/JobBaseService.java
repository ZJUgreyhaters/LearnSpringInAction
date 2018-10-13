package com.quantchi.quartz.service;

import com.quantchi.quartz.entity.MDExtractorJob;

import java.util.List;
import java.util.Map;

public interface JobBaseService<T> {

	Map<String,String> createJob(T job);

	Map<String,String> updateJob(T job);

	Map<String,String> deleteJobs(List<T> jobs);

	Map<String,String> fireJobNow(List<T> jobs);
}
