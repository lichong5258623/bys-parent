package com.chong.bys.bystest.poi.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lichong
 * 2018/10/23 15:17
 * @version 1
 * @since 1.0
 */
@Slf4j
public class POIUtil {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";


    /**
     * 读入excel文件，解析后返回
     *
     * @param file spring上传流对象
     * @throws IOException
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {

        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        return readWorkBook(workbook);
    }

    /**
     * 读入excel文件，解析后返回
     *
     * @param file 文件对象
     * @throws IOException
     */
    public static List<String[]> readExcel(File file) throws IOException {

        checkFile(file);
        FileInputStream fileInputStream = new FileInputStream(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(fileInputStream, file.getName());
        return readWorkBook(workbook);
    }

    /**
     * 读入excel文件，解析后返回
     *
     * @param inputStream excel文件流
     * @param fileName    文件名
     * @throws IOException
     */
    public static List<String[]> readExcel(InputStream inputStream, String fileName) throws IOException {

        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(inputStream, fileName);
        return readWorkBook(workbook);
    }

    public static List<String[]> readExcel(File file, int sheetNum, int startRowNum) throws IOException {

        checkFile(file);
        FileInputStream fileInputStream = new FileInputStream(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(fileInputStream, file.getName());
        return readWorkBook(workbook, sheetNum, startRowNum);
    }


    /**
     * 读入excel文件，解析后返回
     *
     * @param workbook
     * @throws IOException
     */
    public static List<String[]> readWorkBook(Workbook workbook) {

        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                List<String[]> strings = readWorkBook(workbook, sheetNum, 0);
                if (strings != null) {
                    list.addAll(strings);
                }
            }
        }
        return list;
    }

    /**
     * 读入excel文件，解析后返回
     *
     * @param workbook
     */
    public static List<String[]> readWorkBook(Workbook workbook, int sheetNum, int startRowNum) {

        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<>();
        if (workbook != null) {
            //获得当前sheet工作表
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                return null;
            }
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            //循环除了第一行的所有行
            for (int rowNum = startRowNum; rowNum <= lastRowNum; rowNum++) {
                //获得当前行
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                //获得当前行的开始列
                int firstCellNum = row.getFirstCellNum();
                //获得当前行的列数
                int lastCellNum = row.getPhysicalNumberOfCells();
                String[] cells = new String[row.getPhysicalNumberOfCells()];
                //循环当前行
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                list.add(cells);
            }
        }
        return list;
    }

    public static void checkFile(MultipartFile file) throws IOException {

        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            log.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static void checkFile(File file) throws IOException {

        //判断文件是否存在
        if (null == file) {
            log.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getName();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            log.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(MultipartFile file) {

        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream inputStream = file.getInputStream();
            workbook = getWorkBook(inputStream, fileName);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return workbook;
    }

    public static Workbook getWorkBook(InputStream inputStream, String fileName) {

        Workbook workbook = null;
        try {
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            log.info("excel读取：{}", e.getMessage());
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {

        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

}
