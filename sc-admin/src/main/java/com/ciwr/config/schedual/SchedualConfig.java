package com.ciwr.config.schedual;

import com.ciwr.service.sys.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class SchedualConfig {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 每日凌晨0点0分秒执行清空日志表
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanLog() {
      sysLogService.truncateTable();
    }

}
