package com.tongji.f4.stt.util;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * @program: stt
 * @description: The excel workbook parse tool
 * @author: saturn
 * @create: 2020/04/02
 **/
public class ExcelParser {
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelParser.class);

    public static void parse(InputStream excelFileStream) throws IOException {
        Workbook wb = WorkbookFactory.create(excelFileStream);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);

        Cell cell = row.getCell(0);
        if (cell != null) {
            String v = cell.getStringCellValue();
        }
    }

    public static void parse(String path) {
        LOGGER.info("Trying to parse excel file " + path);
        try {
            ClassPathResource cpr = new ClassPathResource(path);
            BufferedInputStream excelFile = new BufferedInputStream(cpr.getInputStream());
            parse(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
