package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.SysDicthinaryControllerApi;
import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.service.SysDicthinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/26 13:33
 */
@RestController
@RequestMapping("/sys/dictionary")
public class SysDicthinaryController implements SysDicthinaryControllerApi {
    @Autowired
    private SysDicthinaryService sysDicthinaryService;
    @GetMapping("get/{dType}")
    @Override
    public SysDictionary getByType(@PathVariable("dType") String type) {
        return sysDicthinaryService.getByType(type);
    }
}
