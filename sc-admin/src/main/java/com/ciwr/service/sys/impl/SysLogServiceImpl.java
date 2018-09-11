package com.ciwr.service.sys.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciwr.mapper.sys.SysLogMapper;
import com.ciwr.modle.sys.SysLog;
import com.ciwr.service.sys.SysLogService;
import org.springframework.stereotype.Service;

@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
        implements SysLogService {

    @Override
    public void truncateTable() {
        baseMapper.truncateTable();
    }
}
