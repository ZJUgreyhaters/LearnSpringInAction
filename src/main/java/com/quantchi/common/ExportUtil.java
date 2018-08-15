package com.quantchi.common;

import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 导出Excel模板
 * <p>Title:ExportUtil.java</p>
 * <p>Company: quant-chi</p>
 * <p>Description:</p>
 *
 * @author:maxj
 * @date: 2017年12月21日
 */
public class ExportUtil {

  /**
   * 导出关联方查询的excel
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ExportUtil.class);

  public final static String exportRelationExcel(HttpServletResponse response, String fileName,
      String title, String[] titleArray, List<Map<String, Object>> listContent) {
    String result = "Excel文件导出成功！";
    try {
      OutputStream os = response.getOutputStream();
      response.reset();// 清空输出流
      response.setHeader("Content-disposition",
          "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1") + ".xls");
      response.setContentType("application/msexcel");

      WritableWorkbook workbook = Workbook.createWorkbook(os);
      WritableSheet sheet = workbook.createSheet("Sheet1", 0);
      jxl.SheetSettings sheetset = sheet.getSettings();
      sheetset.setProtected(false);
      //WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
      WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);

      WritableCellFormat wcf_center = new WritableCellFormat(NormalFont);
      wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
      wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
      wcf_center.setAlignment(Alignment.CENTRE);
      wcf_center.setWrap(false);

      WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
      wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN);
      wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE);
      wcf_left.setAlignment(Alignment.LEFT);
      wcf_left.setWrap(false);

      sheet.setColumnView(0, 20);
      sheet.setColumnView(1, 20);
      sheet.setColumnView(2, 30);
      sheet.setColumnView(3, 20);
      sheet.setColumnView(4, 20);
      sheet.setColumnView(5, 20);
      sheet.setColumnView(6, 20);

      sheet.addCell(new Label(0, 0, title, wcf_center));
      sheet.mergeCells(0, 0, 6, 0);
      for (int i = 0; i < titleArray.length; i++) {
        sheet.addCell(new Label(i, 1, titleArray[i], wcf_center));
      }
      Set<String> mapKeys = new LinkedHashSet<>();
      for (int i = 0; i < listContent.size(); i++) {
        Map<String, Object> map = listContent.get(i);
        mapKeys = map.keySet();
        String[] splits = mapKeys.toArray(new String[mapKeys.size()]);
        for (int j = 0; j < splits.length; j++) {
          String value = splits[j];
          String valueName = map.get(value).toString();
          sheet.addCell(new Label(j, 2 + i, valueName, wcf_center));
        }
      }

      workbook.write();
      workbook.close();
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.info("文件导出失败", e);
    }
    return result;
  }
}