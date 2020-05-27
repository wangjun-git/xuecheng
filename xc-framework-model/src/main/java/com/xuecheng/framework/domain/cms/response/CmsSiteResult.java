package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/10 18:13
 */
@Data
public class CmsSiteResult extends ResponseResult {
    private List siteList=new ArrayList<CmsSite>();
    public CmsSiteResult(ResultCode resultCode,List<CmsSite>siteList){
        super(resultCode);
        this.siteList=siteList;
    }
}
