package com.quantchi.common;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quantchi.intelquery.utils.ComplexTable;
import com.quantchi.intelquery.utils.ComplexTable.*;
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

  private static final Logger LOGGER = LoggerFactory.getLogger(ExportUtil.class);

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
          "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xls");
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

  /**
   * 导出智能问句查询的excel
   */
  public static class ExportIntelQueryExcel {
    private static class PrimeBlockInfo {
      public int primeBlockBottomRow;
      public ArrayList<NormBlock> normBlocks;
    }
    int headerBottomRow = 0;
    Map<PrimeBlock, PrimeBlockInfo> primeBlockInfos = new HashMap<>();

    WritableSheet sheet;
    WritableCellFormat wcf_center;
    LeafHeader primeHeader;

    // 递归输出普通列
    private final int exportNormalCols (Map<String, Object> normHeader, int curCol, int curRow)
      throws Exception {
      if (curRow > headerBottomRow) {
        headerBottomRow = curRow;
      }

      // 先生成表头
      Set<String> colNames = normHeader.keySet();

      int rightmostCol = curCol;

      for (String colName : colNames) {
        Object obj = normHeader.get(colName);
        if (obj instanceof Map) {
          // 递归调用
          rightmostCol = exportNormalCols((Map<String, Object>) obj, curCol, curRow + 1);
        } else if (obj instanceof LeafHeader) {
          LeafHeader leafHeader = (LeafHeader) obj;

          if (!leafHeader.getTitles().isEmpty()) {
            if ((++curRow) > headerBottomRow) {
              headerBottomRow = curRow;
            }
            for (String leafColName : leafHeader.getTitles()) {
              sheet.addCell(new Label(rightmostCol++, curRow, leafColName, wcf_center));
            }

            // 再生成表数据
            exportNormData((List<NormBlock>) leafHeader.getData(), curCol, curRow + 1);

            --curRow;
          } else {
            // 再生成表数据
            exportNormData((List<NormBlock>) leafHeader.getData(), curCol, curRow + 1);

            ++rightmostCol;
          }
        } else {
          String msg = "ComplexTable数据格式不对";
          LOGGER.error(msg);
          throw new Exception(msg);
        }

        sheet.addCell(new Label(curCol, curRow, colName, wcf_center));
        sheet.mergeCells(curCol, curRow, rightmostCol - 1, curRow);

        curCol = rightmostCol;
      }

      return rightmostCol;
    }

    // 输出普通列数据
    public final void exportNormData(List<NormBlock> normBlocks, int curCol, int curRow)
      throws Exception {
      // 对NormBlock根据所属PrimeBlock进行排序
      for (PrimeBlockInfo primeBlockInfo : primeBlockInfos.values()) {
        primeBlockInfo.normBlocks.clear();
      }

      for (NormBlock normBlock : normBlocks) {
        PrimeBlock primeBlock = normBlock.getBelongTo();
        primeBlockInfos.get(primeBlock).normBlocks.add(normBlock);
      }

      // 输出数据
      for (PrimeBlock primeBlock : (List<PrimeBlock>) primeHeader.getData()) {
        for (NormBlock normBlock : primeBlockInfos.get(primeBlock).normBlocks) {
          int rightmostCol = curCol;
          for (String colValue : normBlock.getRowData()) {
            sheet.addCell(new Label(rightmostCol++, curRow, colValue, wcf_center));
          }
          ++curRow;
        }
        if (curRow - 1 > primeBlockInfos.get(primeBlock).primeBlockBottomRow) {
          primeBlockInfos.get(primeBlock).primeBlockBottomRow = curRow - 1;
        }
      }
    }

    public final void export(HttpServletRequest request, HttpServletResponse response,
                             String fileName, ComplexTable complexTable) throws Exception {
      OutputStream os = response.getOutputStream();
      response.reset();// 清空输出流
      // 针对IE浏览器下载时文件名乱码处理
      String header = request.getHeader("User-Agent").toUpperCase();
      if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
        fileName = URLEncoder.encode(fileName, "utf-8");
      } else {
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
      }
      response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
      response.setContentType("application/msexcel");

      WritableWorkbook workbook = Workbook.createWorkbook(os);
      sheet = workbook.createSheet("Sheet1", 0);
      jxl.SheetSettings sheetset = sheet.getSettings();
      sheetset.setProtected(false);
      WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);

      wcf_center = new WritableCellFormat(NormalFont);
      wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
      wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
      wcf_center.setAlignment(Alignment.CENTRE);
      wcf_center.setWrap(false);

      primeHeader = complexTable.getPrimeHeader();
      for (PrimeBlock primeBlock : (List<PrimeBlock>) primeHeader.getData()) {
        PrimeBlockInfo primeBlockInfo = new PrimeBlockInfo();
        primeBlockInfo.primeBlockBottomRow = 0;
        primeBlockInfo.normBlocks = new ArrayList<NormBlock>();
        primeBlockInfos.put(primeBlock, primeBlockInfo);
      }

      // 先生成表右边的普通列，后生成左边的基本主体(prime)列

      int curRow = 0;
      int curCol = primeHeader.getTitles().size();

      // 依次生成每个普通列
      List<Map<String, Object>> normHeaders = complexTable.getNormHeaders();
      for (Map<String, Object> normHeader : normHeaders) {
        curCol = exportNormalCols(normHeader, curCol, curRow);
      }

      // 生成左边的基本主体(prime)列
      curCol = 0;
      curRow = 0;
      for (String colName : primeHeader.getTitles()) {
        sheet.addCell(new Label(curCol, curRow, colName, wcf_center));
        sheet.mergeCells(curCol, curRow, curCol, headerBottomRow);
        ++curCol;
      }
      curRow = headerBottomRow + 1;
      for (PrimeBlock primeBlock : (List<PrimeBlock>) primeHeader.getData()) {
        curCol = 0;
        int bottowRow = primeBlockInfos.get(primeBlock).primeBlockBottomRow;
        for (String colValue : primeBlock.getRowData()) {
          sheet.addCell(new Label(curCol, curRow, colValue, wcf_center));
          sheet.mergeCells(curCol, curRow, curCol, bottowRow);
          ++curCol;
        }
      }

      workbook.write();
      workbook.close();
    }
  }
}