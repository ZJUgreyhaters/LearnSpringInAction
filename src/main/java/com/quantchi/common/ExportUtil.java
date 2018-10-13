package com.quantchi.common;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quantchi.intelquery.utils.ComplexTable;
import com.quantchi.intelquery.utils.ComplexTable.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

  private static void addCell(Sheet sheet, int columnIndex, int rowIndex, String text, CellStyle style) {
    Row row = sheet.getRow(rowIndex);
    if (row == null) {
      row = sheet.createRow(rowIndex);
    }
    Cell cell = row.getCell(columnIndex);
    if (cell == null) {
      cell = row.createCell(columnIndex);
    }
    cell.setCellStyle(style);
    cell.setCellValue(text);
  }

  private static void mergeCells(Sheet sheet, int firstCol, int firstRow, int lastCol, int lastRow) {
    // POI库要求合并操作必须要有2个或以上cell: Merged region must contain 2 or more cells
    if (lastCol > firstCol || lastRow > firstRow) {
      sheet.addMergedRegion(new CellRangeAddress(
              firstRow, //first row (0-based)
              lastRow, //last row  (0-based)
              firstCol, //first column (0-based)
              lastCol  //last column  (0-based)
      ));
    }
  }

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
          "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xlsx");
      response.setContentType("application/msexcel");

      Workbook workbook = new XSSFWorkbook();
      Sheet sheet = workbook.createSheet("Sheet1");
      workbook.setSheetOrder("Sheet1", 0);

      Font font = workbook.createFont();
      font.setFontHeightInPoints((short)10);
      font.setFontName("ARIAL");
      // font.setBold(true);

      CellStyle styleCenter = workbook.createCellStyle();
      styleCenter.setFont(font);
      styleCenter.setBorderTop(BorderStyle.THIN);
      styleCenter.setBorderBottom(BorderStyle.THIN);
      styleCenter.setBorderLeft(BorderStyle.THIN);
      styleCenter.setBorderRight(BorderStyle.THIN);
      styleCenter.setAlignment(HorizontalAlignment.CENTER);
      styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
      styleCenter.setWrapText(false);

      CellStyle styleLeft = workbook.createCellStyle();
      styleLeft.setFont(font);
      styleLeft.setBorderTop(BorderStyle.THIN);
      styleLeft.setBorderBottom(BorderStyle.THIN);
      styleLeft.setBorderLeft(BorderStyle.THIN);
      styleLeft.setBorderRight(BorderStyle.THIN);
      styleLeft.setAlignment(HorizontalAlignment.LEFT);
      styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
      styleLeft.setWrapText(false);

      // The column width is in units of 1/256th of a character width
      sheet.setColumnWidth(0, 20 * 256);
      sheet.setColumnWidth(1, 20 * 256);
      sheet.setColumnWidth(2, 20 * 256);
      sheet.setColumnWidth(3, 20 * 256);
      sheet.setColumnWidth(4, 20 * 256);
      sheet.setColumnWidth(5, 20 * 256);
      sheet.setColumnWidth(6, 20 * 256);

      addCell(sheet, 0, 0, title, styleCenter);
      mergeCells(sheet,0, 0, 6, 0);
      for (int i = 0; i < titleArray.length; i++) {
        addCell(sheet, i, 1, titleArray[i], styleCenter);
      }
      Set<String> mapKeys = new LinkedHashSet<>();
      for (int i = 0; i < listContent.size(); i++) {
        Map<String, Object> map = listContent.get(i);
        mapKeys = map.keySet();
        String[] splits = mapKeys.toArray(new String[mapKeys.size()]);
        for (int j = 0; j < splits.length; j++) {
          String value = splits[j];
          String valueName = map.get(value).toString();
          addCell(sheet, j, 2 + i, valueName, styleCenter);
        }
      }

      workbook.write(os);
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

    Sheet sheet;
    CellStyle styleCenter;
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
              addCell(sheet, rightmostCol++, curRow, leafColName, styleCenter);
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

        addCell(sheet, curCol, curRow, colName, styleCenter);
        mergeCells(sheet, curCol, curRow, rightmostCol - 1, curRow);

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
            addCell(sheet, rightmostCol++, curRow, colValue, styleCenter);
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
      response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
      response.setContentType("application/msexcel");

      Workbook workbook = new XSSFWorkbook();
      sheet = workbook.createSheet("Sheet1");
      workbook.setSheetOrder("Sheet1", 0);

      Font font = workbook.createFont();
      font.setFontHeightInPoints((short)10);
      font.setFontName("ARIAL");

      styleCenter = workbook.createCellStyle();
      styleCenter.setFont(font);
      styleCenter.setBorderTop(BorderStyle.THIN);
      styleCenter.setBorderBottom(BorderStyle.THIN);
      styleCenter.setBorderLeft(BorderStyle.THIN);
      styleCenter.setBorderRight(BorderStyle.THIN);
      styleCenter.setAlignment(HorizontalAlignment.CENTER);
      styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
      styleCenter.setWrapText(false);

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
        addCell(sheet, curCol, curRow, colName, styleCenter);
        mergeCells(sheet, curCol, curRow, curCol, headerBottomRow);
        ++curCol;
      }
      curRow = headerBottomRow + 1;
      for (PrimeBlock primeBlock : (List<PrimeBlock>) primeHeader.getData()) {
        curCol = 0;
        int bottowRow = primeBlockInfos.get(primeBlock).primeBlockBottomRow;
        for (String colValue : primeBlock.getRowData()) {
          addCell(sheet, curCol, curRow, colValue, styleCenter);
          if (bottowRow > curRow) {
            mergeCells(sheet, curCol, curRow, curCol, bottowRow);
          }
          ++curCol;
        }
        curRow = bottowRow + 1;
      }

      workbook.write(os);
      workbook.close();
    }
  }
}