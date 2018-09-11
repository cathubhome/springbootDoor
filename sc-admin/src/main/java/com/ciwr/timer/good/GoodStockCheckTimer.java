package com.ciwr.timer.good;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@Slf4j
public class GoodStockCheckTimer extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("分布式节点quartz-cluster-node-first，执行库存检查定时任务，执行时间：{}", new Date());
    }
}
