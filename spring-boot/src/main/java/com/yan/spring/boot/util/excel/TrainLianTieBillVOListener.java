package com.yan.spring.boot.util.excel;

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
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class TrainLianTieBillVOListener implements ReadListener<TrainLianTieBillVO> {
    /**
     * 5000 条打印异常日志
     */
    private static final int COUNT = 5000;


    private List<TrainLianTieBillVO> cachedDataList = ListUtils.newArrayListWithExpectedSize(COUNT);
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(TrainLianTieBillVOListener.class);

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. It is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(TrainLianTieBillVO data, AnalysisContext context) {
        cachedDataList.add(data);
        if (cachedDataList.size() % COUNT == 0) {
            logger.info("Train-Bill账单上传中{}", cachedDataList.size());
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        logger.info("Train-Bill账单上传完成");
    }


    public List<TrainLianTieBillVO> getCachedDataList() {
        return cachedDataList;
    }


}
