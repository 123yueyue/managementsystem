package com.aynu.data.web.core.adminWeb;

import com.aynu.data.common.bean.BaseController;
import com.aynu.data.common.bean.ConstantMsgUtil;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.bean.ResponseEntity;
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
 * @Date: 2020/3/22
 * @Description:学院管理接口
 */
@Api(value = "/data/institution",description = "学院管理接口")
@RestController
@RequestMapping("/data/institution")
public class InstitutionController extends BaseController {
    private static final Logger logger = Logger.getLogger(InstitutionController.class);

    @Autowired
    private IInstitutionService iInstitutionService;


    @ApiOperation(value = "", notes = "新建学院")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PostMapping
    public ResponseEntity add(@RequestBody GenericBean genericBean){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            iInstitutionService.addInstitution(genericBean);
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

    @ApiOperation(value = "", notes = "查询学院列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping
    public ResponseEntity get(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            responseEntity.setCode(20000);
            PageInfo pageInfo = iInstitutionService.getInstitutionList(genericBean);
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

    @ApiOperation(value = "", notes = "修改学院信息接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PutMapping
    public ResponseEntity update(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            iInstitutionService.updateInstitution(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        } catch (Exception e) {
            logger.error(e);
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
        }

        return responseEntity;
    }

    @ApiOperation(value = "", notes = "删除学院接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @DeleteMapping
    public ResponseEntity delete(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            iInstitutionService.deleteInstitution(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        } catch (Exception e) {
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }

        return responseEntity;
    }
    @ApiOperation(value = "/schoolList", notes = "获取学院列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "",value = "")
    )
    @GetMapping("/schoolList")
    public ResponseEntity schoolList(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            PageInfo pageInfo = iInstitutionService.schoolList(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setResponsePageInfo(pageInfo);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        } catch (Exception e) {
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }

        return responseEntity;
    }

    @ApiOperation(value = "/checkName", notes = "校验用户名接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "institutionName",value = "用户名")
    )
    @GetMapping("/checkName")
    public ResponseEntity checkName(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            int count = iInstitutionService.checkName(genericBean);
            responseEntity.setCode(20000);
            if(count>0){
                responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
                responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
            }else{
                responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
                responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
            }
        } catch (Exception e) {
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }
        return responseEntity;
    }
}
