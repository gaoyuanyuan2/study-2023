package com.yan.demo.base.tool.xls;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 模板的读取类
 *
 * @author Jiaju Zhuang
 */
public class XlsListener implements ReadListener<XlsDTO> {
    /**
     * 5000 条打印异常日志
     */
    private static final int COUNT = 5000;


    private List<XlsDTO> cachedDataList = ListUtils.newArrayListWithExpectedSize(COUNT);
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(XlsListener.class);

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. It is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(XlsDTO data, AnalysisContext context) {
        cachedDataList.add(data);
        if (cachedDataList.size() % COUNT == 0) {
            logger.info("解析中{}", cachedDataList.size());
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        logger.info("解析完成");
    }


    public List<XlsDTO> getCachedDataList() {
        return cachedDataList;
    }


}
