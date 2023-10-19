package com.qiaose.controller.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.qiaose.entity.Result;
import com.qiaose.entity.testEntity.test;
import com.qiaose.test.service.serviceImpl.testServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @ClassName: Testcontroller
 * @Description:
 * @Author qiaosefennv
 * @Date 2022/6/11
 * @Version 1.0
 */

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
@Validated
public class Testcontroller {

    @Autowired
    private testServiceImpl testService;


    @GetMapping("/test")
    @ApiOperation("获取测试")
    public Result<?> test(){
        return Result.success("hello world");
    }

//    @GetMapping("/test1")
//    @ApiOperation("获取数据库中的测试")
//    public Result<?> getTest(){
//        return Result.success(testService.getTest());
//    }
//
//    @PostMapping("/insert")
//    @ApiOperation("插入测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> insert(@RequestBody @Valid test t1){
//        return Result.success(testService.getBaseMapper().insert(t1));
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> update(@RequestBody @Valid test t1){
//        return Result.success(testService.getBaseMapper().updateById(t1));
//    }
//
//    @DeleteMapping("/delete")
//    @ApiOperation("删除测试")
//    @Transactional(rollbackFor = {Exception.class})
//    public Result<?> delete(@RequestParam @Min(message = "小于5",value = 5) Integer id){
//        return Result.success(testService.getBaseMapper().deleteById(id));
//    }

}
