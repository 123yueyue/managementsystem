package com.aynu.data.web.core.adminWeb;

import com.aynu.data.common.bean.BaseController;
import com.aynu.data.common.bean.ConstantMsgUtil;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.bean.ResponseEntity;
import com.aynu.data.web.core.adminIService.ITeacherService;
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
 * @Date: 2020/3/20
 * @Description:教师管理接口
 */
@Api(value = "/data/teacher",description = "教师管理接口")
@RestController
@RequestMapping("/data/teacher")
public class TeacherController extends BaseController {

    private static final Logger logger = Logger.getLogger(SchoolController.class);

    @Autowired
    private ITeacherService iTeacherService;

    @ApiOperation(value = "", notes = "添加教师")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PostMapping
    public ResponseEntity add(@RequestBody GenericBean genericBean){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            iTeacherService.addTeacher(genericBean);
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

    @ApiOperation(value = "", notes = "查询学校列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping
    public ResponseEntity get(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            PageInfo pageInfo = iTeacherService.getTeacherList(genericBean);
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

    @ApiOperation(value = "", notes = "修改学校信息接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PutMapping
    public ResponseEntity update(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            iTeacherService.updateTeacher(genericBean);
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

    @ApiOperation(value = "", notes = "删除教师接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @DeleteMapping
    public ResponseEntity delete(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            iTeacherService.deleteTeacher(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        } catch (Exception e) {
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }

        return responseEntity;
    }
    @ApiOperation(value = "/checkAccount", notes = "校验账号接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "account",value = "账号")
    )
    @GetMapping("/checkAccount")
    public ResponseEntity checkAccount(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            int count = iTeacherService.checkAccount(genericBean);
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

    @ApiOperation(value = "/checkUserName", notes = "校验用户名接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "userName",value = "用户名")
    )
    @GetMapping("/checkUserName")
    public ResponseEntity checkUserName(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            //int count = iTeacherService.checkUserName(genericBean);
            responseEntity.setCode(20000);
//            if(count>0){
//                responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
//                responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
//            }else{
//                responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
//                responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
//            }
        } catch (Exception e) {
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }

        return responseEntity;
    }
}
