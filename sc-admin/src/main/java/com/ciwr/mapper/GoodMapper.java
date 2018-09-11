package com.ciwr.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciwr.modle.Good;
import org.apache.ibatis.annotations.Insert;

public interface GoodMapper extends BaseMapper<Good>{

    @Insert("")
    void save();
}
