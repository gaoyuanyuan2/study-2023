package com.yan.demo.base.tool.xls;

import com.alibaba.excel.EasyExcel;
import com.yan.demo.base.tool.xls.XlsDTO;
import com.yan.demo.base.tool.xls.XlsListener;
import com.yan.demo.base.xls.read.PushBigTable;
import com.yan.demo.base.xls.read.PushBigTable2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.yan.demo.base.xls.read.SassXlsMain.getDetail;
import static com.yan.demo.base.xls.read.SassXlsMain.update;

/**
 * @since 2024/6/17 17:39
 */
public class XlsMain {
    public static void main(String[] args) throws Exception {
        XlsListener xlsListener = new XlsListener();
        // sheet 默认从0 开始
        EasyExcel.read(Files.newInputStream(Paths.get("E:\\1.xlsx")), XlsDTO.class, xlsListener)
                .sheet(0)
                .headRowNumber(1)
                .doRead();
        List<XlsDTO> xlsDTOS = xlsListener.getCachedDataList();
        for (XlsDTO xlsDTO : xlsDTOS) {
            String ddbh = xlsDTO.getPhone();
//            Map detail = getDetail(ddbh);
//            update(ddbh, detail);
            TimeUnit.MILLISECONDS.sleep(200);
            PushBigTable2.pushOne(ddbh);
        }
//        System.out.println(xlsDTOS.stream().map(XlsDTO::getPhone).map(e -> "'" + e + "'").collect(Collectors.joining(",")));
    }
}