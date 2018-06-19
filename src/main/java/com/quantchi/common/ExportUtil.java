package com.quantchi.common;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
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
import org.apache.commons.lang.StringUtils;

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
      sheet.setColumnView(7, 20);
      sheet.setColumnView(8, 20);
      sheet.setColumnView(9, 20);
      sheet.setColumnView(10, 20);
      sheet.setColumnView(11, 20);
      sheet.setColumnView(12, 20);

      sheet.addCell(new Label(0, 0, title, wcf_center));
      sheet.mergeCells(0, 0, 8, 0);
      for (int i = 0; i < titleArray.length; i++) {
        sheet.addCell(new Label(i, 1, titleArray[i], wcf_center));
      }
      String str= StringUtils.join(titleArray,",");
      String titles = str.replaceAll("客户号","customer_no")
          .replaceAll("客户姓名", "customer_name").replaceAll("融资负债（万元）", "fin_balance")
          .replaceAll("总资产（万元）", "total_asset").replaceAll("维保比例", "assure_debit_rate")
          .replaceAll("当前仓位", "concentrate").replaceAll("年度收益率", "profit_rate_y");
      String[] splits = titles.split(",");
      for (int i = 0; i < listContent.size(); i++) {
        Map<String, Object> map = listContent.get(i);
        for (int j = 0; j < splits.length; j++) {
          String value = splits[j];
          String value1 = map.get(value).toString();
          sheet.addCell(new Label(j, 2 + i, value1, wcf_center));
        }
      }

      workbook.write();
      workbook.close();
    } catch (Exception e) {
      result = "文件导出失败，原因：" + e.toString();
      System.out.println(result);
      e.printStackTrace();
    }
    return result;
  }
}