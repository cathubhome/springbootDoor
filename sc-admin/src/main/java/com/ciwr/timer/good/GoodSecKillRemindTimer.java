package com.ciwr.timer.good;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class GoodSecKillRemindTimer extends QuartzJobBean {

    /**
     * 任务指定逻辑
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取任务详情内的数据集合
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        //获取商品编号
        Long goodId = dataMap.getLong("goodId");
        log.info("分布式节点quartz-cluster-node-first，开始处理秒杀商品：{}，关注用户推送消息.", goodId);
    }
}