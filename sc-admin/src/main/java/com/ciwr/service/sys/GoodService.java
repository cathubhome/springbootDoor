package com.ciwr.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.ciwr.modle.Good;

public interface GoodService extends IService<Good> {

    Long save(Good good) throws Exception;

    int updateGood(Good good);

}
