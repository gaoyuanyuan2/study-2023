package com.yan.spring.boot.util.excel;


import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

/**
 * EasyExcel
 *
 * @author : Y
 * @since 2023/5/13 13:55
 */
public class ReadTest {

    @Test
    public void simpleRead() {
        String fileName = "D:\\SY 2023年3月账单2.0.xlsx";
        TrainLianTieBillVOListener lianTieBillVOListener = new TrainLianTieBillVOListener();
        EasyExcel.read(fileName, TrainLianTieBillVO.class, lianTieBillVOListener).sheet().doRead();
        List<TrainLianTieBillVO> lianTieBillVOList = lianTieBillVOListener.getCachedDataList();
        System.out.println(lianTieBillVOList);

    }


}