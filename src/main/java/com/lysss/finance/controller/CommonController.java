package com.lysss.finance.controller;

import com.lysss.finance.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CommonController {

    @Resource
    private CommonService commonService;

    /**
     * 通过serviceBeanName 及方法名称进行测试
     *
     * @param beanName   beanName
     * @param methodName 方法名
     * @return
     */
    @GetMapping("executeByReflect")
    public Object executeByReflect(@RequestParam String beanName,
                                   @RequestParam String methodName,
                                   @RequestParam(required = false) Object... params) {
        return commonService.executeByReflect(beanName, methodName, params);
    }


}
