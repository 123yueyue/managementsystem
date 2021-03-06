package com.aynu.data.web.core.adminWeb;


import com.aynu.data.common.bean.BaseController;
import com.aynu.data.common.bean.ConstantMsgUtil;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminIService.IClassService;
import com.aynu.data.web.core.adminIService.IInstitutionService;
import com.aynu.data.web.core.adminIService.IStudentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aynu.data.common.bean.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:学生管理接口
 */
@Api(value = "/data/student",description = "学生管理接口")
@RestController
@RequestMapping("/data/student")
public class StudentController extends BaseController {

    private static final Logger logger = Logger.getLogger(StudentController.class);

    @Autowired
    private IStudentService iStudentService;

    @ApiOperation(value = "", notes = "新建学生")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PostMapping
    public ResponseEntity add(@RequestBody GenericBean genericBean){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            iStudentService.addStudent(genericBean);
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

    @ApiOperation(value = "", notes = "查询学生列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping
    public ResponseEntity get(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            responseEntity.setCode(20000);
            PageInfo pageInfo = iStudentService.getStudentList(genericBean);
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

    @ApiOperation(value = "", notes = "修改学生信息接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PutMapping
    public ResponseEntity update(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            iStudentService.updateStudent(genericBean);
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

    @ApiOperation(value = "", notes = "删除学生接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @DeleteMapping
    public ResponseEntity delete(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            iStudentService.deleteStudent(genericBean);
            responseEntity.setCode(20000);
            responseEntity.setStatus(ConstantMsgUtil.getSuccStatus());
            responseEntity.setMsg(ConstantMsgUtil.getSuccMsg());
        } catch (Exception e) {
            responseEntity.setStatus(ConstantMsgUtil.getFailStatus());
            responseEntity.setMsg(ConstantMsgUtil.getFailMsg());
        }

        return responseEntity;
    }
    @ApiOperation(value = "/getClassList", notes = "获取下拉框班级列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "",value = "")
    )
    @GetMapping("/getClassList")
    public ResponseEntity getClassList(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            PageInfo pageInfo = iStudentService.getClassList(genericBean);
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

    @ApiOperation(value = "/checkName", notes = "校验学生名接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "institutionName",value = "用户名")
    )
    @GetMapping("/checkName")
    public ResponseEntity checkName(){
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            GenericBean genericBean = this.getPageData();
            int count = iStudentService.checkName(genericBean);
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
