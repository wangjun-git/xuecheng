package com.xuecheng.framework.domain.cms.response;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/10 18:10
 */
@Data
public class CmsTemplateResult extends ResponseResult {
    private List templateList=new ArrayList<CmsTemplate>();
    public CmsTemplateResult(ResultCode resultCode, List<CmsTemplate> templateList) {
        super(resultCode);
        this.templateList = templateList;
    }
}
