package com.yan.demo.gof23.utils.xls.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

/**
 * @since 2024/6/6 21:19
 */
public class JsonToExcelConverter {
    public static void main(String[] args) {
        String jsonFilePath = "C:\\Users\\Desktop\\1\\b1.json";
        String excelFilePath = "C:\\Users\\Desktop\\1\\b1.xlsx";
        convertJsonToExcel(jsonFilePath, excelFilePath);
    }

    private static final Gson GSON_TO_LINE = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public static void convertJsonToExcel(String jsonFilePath, String excelFilePath) {
        try {
            // 读取JSON文件
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            Map dataMap = GSON_TO_LINE.fromJson(jsonContent, Map.class);
            // 创建Excel工作簿和工作表
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Coupon");
            // 创建表头
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("手机号");
            headerRow.createCell(1).setCellValue("地址");
            // 填充数据
            int rowNum = 1;
            Map map1 = (Map) dataMap.get("hits");
            ArrayList<Map> arrayList = (ArrayList<Map>) map1.get("hits");
            for (Map map : arrayList) {
                Map map3 = (Map) map.get("_source");
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue((String) map3.get("phone"));
                row.createCell(1).setCellValue((String) map3.get("address"));
            }
            // 写入Excel文件
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }
            System.out.println("Excel file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}