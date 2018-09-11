package com.ciwr.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.ciwr.modle.sys.SysLog;


public interface SysLogService extends IService<SysLog> {

    void truncateTable();
}
