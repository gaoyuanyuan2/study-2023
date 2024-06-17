package com.yan.demo.gof23.utils.xls.read;

import com.alibaba.excel.EasyExcel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @since 2024/6/17 17:39
 */
public class XlsMain {
    public static void main(String[] args) throws IOException {
        XlsListener xlsListener = new XlsListener();
        // sheet 默认从0 开始
        EasyExcel.read(Files.newInputStream(Paths.get("F:\\1.xlsx")), XlsDTO.class, xlsListener)
                .sheet(0)
                .headRowNumber(1)
                .doRead();
        List<XlsDTO> xlsDTOS = xlsListener.getCachedDataList();
        System.out.println(xlsDTOS.stream().map(XlsDTO::getPhone).map(e -> "'" + e + "'").collect(Collectors.joining(",")));
    }
}