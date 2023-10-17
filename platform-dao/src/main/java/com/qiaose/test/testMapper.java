package com.qiaose.test;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiaose.entity.testEntity.test;

import java.util.List;

public interface testMapper extends BaseMapper<test> {
    List<test> getTest();
}
