package com.quantchi.metadatamgr.extract;

import com.quantchi.quartz.util.JobConstant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManualFileExtractor extends BaseExtractor {

  private static final Logger logger = LoggerFactory.getLogger(ManualFileExtractor.class);

  private List<List<String>> content = null;

  public ManualFileExtractor() {

  }

  public static ManualFileExtractor of(String type, List<List<String>> content) throws Exception {
    ManualFileExtractor obj = null;
    switch (type) {
      case JobConstant.TABLE_FILE_MANUAL_TYPE:
      case JobConstant.FIELD_FILE_MANUAL_TYPE:
        obj = new ManualUpdateExtractor(type);
        break;
      case JobConstant.BASICS_TARGET_FILE_MANUAL_TYPE:
      case JobConstant.DERIVE_TARGET_FILE_MANUAL_TYPE:
      case JobConstant.COMMON_TARGET_FILE_MANUAL_TYPE:
        obj = new ManualTargetExtractor(type);
        break;
      case JobConstant.CODE_STANDARD_FILE_MANUAL_TYPE:
      case JobConstant.BASICS_STANDARD_FILE_MANUAL_TYPE:
      case JobConstant.TARGET_STANDARD_FILE_MANUAL_TYPE:
        obj = new ManualStandardExtractor(type);
        break;
      case JobConstant.TARGET_MAPPING_FILE_MANUAL_TYPE:
        obj = new ManualTargetMappingExtractor(type);
        break;
      case JobConstant.STANDARD_MAPPING_FILE_MANUAL_TYPE:
        obj = new ManualStandardMappingExtractor(type);
        break;
      default:
        throw new Exception("unknown type: " + type);
    }
    obj.setContent(content);
    return obj;
  }

  public List<List<String>> getContent() {
    return content;
  }

  public void setContent(List<List<String>> content) {
    this.content = content;
  }

  public List<String> getTitleList(String mapping) throws Exception {
    String[] split = mapping.split(",");
    Map<String, String> map = new HashMap<String, String>();
    for (String str : split) {
      String[] split1 = str.split(":");
      map.put(split1[0], split1[1]);
    }
    List<String> strings = content.get(0);
    List<String> list = new LinkedList<>();
    List<String> listStrings = new LinkedList<>();
    for (String str : strings) {
      if (str.length() == 0) {
        continue;
      }
      listStrings.add(str);
      if (map.get(str) != null) {
        list.add(map.get(str));
      }
    }
    if (list.size() != listStrings.size()) {
      logger.error("配置字段与传入字段不匹配");
      throw new Exception("配置字段与传入字段不匹配！");
    }
    return list;
  }

  @Override
  public boolean extract() {
    return false;
  }
}
