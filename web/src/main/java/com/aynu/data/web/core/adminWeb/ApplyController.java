package com.aynu.data.web.core.adminWeb;

import com.aynu.data.common.bean.BaseController;
import com.aynu.data.common.bean.ConstantMsgUtil;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.bean.ResponseEntity;
import com.aynu.data.web.core.adminIService.IApplyService;
import com.aynu.data.web.core.adminIService.IClassService;
import com.aynu.data.web.core.adminIService.IInstitutionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Auther: zhangyue
 * @Date: 2020/3/29
 * @Description:学生申请管理接口
 */
@Api(value = "/data/apply",description = "学生申请管理接口")
@RestController
@RequestMapping("/data/apply")
public class ApplyController extends BaseController {
    private static final Logger logger = Logger.getLogger(ApplyController.class);

    @Autowired
    private IApplyService iApplyService;


    @ApiOperation(value = "/getApplyInfo", notes = "获取申请信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping("/getApplyInfo")
    public ResponseEntity getApplyInfo(){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            PageInfo pageInfo = iApplyService.getApplyInfo();
            responseEntity.setCode(20000);
            responseEntity.setResponsePageInfo(pageInfo);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        }catch (Exception e){
            logger.error(e);
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }
        return responseEntity;
    }

    @ApiOperation(value = "/getSecondOptions", notes = "获取申请信息第二张表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "data",value = "第一级联返回值")
    )
    @GetMapping("/getSecondOptions")
    public ResponseEntity getSecondOptions(){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            GenericBean genericBean = this.getPageData() ;
            PageInfo pageInfo = iApplyService.getSecondOptions(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setResponsePageInfo(pageInfo);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        }catch (Exception e){
            logger.error(e);
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }
        return responseEntity;
    }

    @ApiOperation(value = "/getStandardInfo", notes = "获取申请信息的详情信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "data",value = "第二级联返回值")
    )
    @GetMapping("/getStandardInfo")
    public ResponseEntity getStandardInfo(){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            GenericBean genericBean = this.getPageData() ;
            GenericBean pageInfo = iApplyService.getStandardInfo(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setResponseObject(pageInfo);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        }catch (Exception e){
            logger.error(e);
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }
        return responseEntity;
    }

    @ApiOperation(value = "", notes = "申请实践积分")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "data",value = "第二级联返回值")
    )
    @PostMapping
    public ResponseEntity addApply(@RequestBody GenericBean genericBean){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            iApplyService.addApply(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        }catch (Exception e){
            logger.error(e);
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }
        return responseEntity;
    }

    @ApiOperation(value = "/getApplyDetail", notes = "申请实践积分细节详情")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "pageNum,pageSize,studentId",value = "分页")
    )
    @GetMapping("/getApplyDetail")
    public ResponseEntity getApplyDetail(){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            GenericBean genericBean = this.getPageData();
            responseEntity.setCode(20000);
            PageInfo pageInfo = iApplyService.getApplyDetail(genericBean);
            responseEntity.setResponsePageInfo(pageInfo);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        }catch (Exception e){
            logger.error(e);
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }
        return responseEntity;
    }

}
