package com.tongji.f4.stt.util.testcase;


import com.tongji.f4.stt.service.GlobalVariableOperator;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: stt
 * @description: The test case which source is from excel file
 * @author: saturn
 * @create: 2020/04/03
 **/
public class ExcelTestCase implements Iterator<List<String>> {
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelTestCase.class);
    private GlobalVariableOperator gvo;
    private Sheet sheet;
    private int rowNum, currentRow = 0;

    public static ExcelTestCase create(GlobalVariableOperator gvo, String excelFileName){
        String excelFilePath = gvo.getExcelPath(excelFileName);
        File excelFile = new File(excelFilePath);
        if(!excelFile.exists()){
            LOGGER.error("Excel file " + excelFileName + " doesn't exist");
            return null;
        }
        ExcelTestCase etc = new ExcelTestCase(gvo);
        etc.loadExcelFile(excelFile);
        return etc;
    }

    public ExcelTestCase(GlobalVariableOperator gvo){
        this.gvo = gvo;
    }

    public void loadExcelFile(File excelFile){
        try {
            FileInputStream fis = new FileInputStream(excelFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Workbook wb = WorkbookFactory.create(bis);
            sheet = wb.getSheetAt(0);
            rowNum = sheet.getPhysicalNumberOfRows();
            fis.close();
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        if(currentRow < 0 || currentRow >= rowNum){
            return false;
        }
        return true;
    }

    @Override
    public List<String> next() {
        Row row = sheet.getRow(currentRow++);
        ArrayList<String> params = new ArrayList<>();
        int cellNum = row.getPhysicalNumberOfCells();
        for(int i = 0; i < cellNum; i++){
            Cell cell = row.getCell(i);
            cell.setCellType(CellType.STRING);
            params.add(cell.getStringCellValue());
        }

        return params;
    }
}
