package com.yotrio.pound.tasks;

import com.yotrio.pound.model.Goods;
import com.yotrio.pound.service.IGoodsService;
import com.yotrio.pound.service.IHttpService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

/**
 * 同步物料任务
 * 模块名称：projects-parent com.yotrio.pound.tasks
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-10 9:21
 * 系统版本：1.0.0
 **/

public class SyncGoodsTask extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(SyncGoodsTask.class);

    @Autowired
    private IHttpService httpService;
    @Autowired
    private IGoodsService goodsService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时任务 同步物料 SyncGoodsTask {}", new Date());
        List<Goods> goodsList = httpService.findAllGoods();
        int[] count = {0, 0};
        if (goodsList != null && goodsList.size() > 0) {
            try {
                for (Goods goods : goodsList) {
                    Goods goodsInDB = goodsService.findByGoodsCode(goods.getGoodsCode());
                    //本地不存在，则插入数据
                    if (goodsInDB == null) {
                        goods.setLastUpdateTime(goods.getUpdateTime());
                        goodsService.save(goods);
                        count[0]++;
                    } else {
                        //本地存在则判断是否要更新,线上数据修改时间大于本地数据的时间，则更新
                        if (goodsInDB.getLastUpdateTime() == null || goods.getUpdateTime().after(goodsInDB.getLastUpdateTime())) {
                            //设置服务器更新的时间
                            goods.setLastUpdateTime(goods.getUpdateTime());
                            goods.setUpdateTime(new Date());
                            goodsService.updateByGoodsCode(goods);
                            count[1]++;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("物料数据同步失败 {}" + e.getMessage());
            }
        }
        logger.info("物料|添加：" + count[0] + "|更新：" + count[1]);
    }
}