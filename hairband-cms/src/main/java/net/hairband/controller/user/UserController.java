package net.hairband.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.hairband.hairbandcontant.*;
import net.hairband.dto.user.CmsUser;
import net.hairband.service.user.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import util.framework.FindServiceUtil;
import util.framework.MD5Util;
import util.framework.SessionUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;
import framework.web.ResResult;

/**
 * 
 * 后台用户管理功能
 * 
 * */
@Controller
@RequestMapping("user")
public class UserController {

	
	@RequestMapping("login.htm")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("user/index");
		ReqBo reqBo = new ReqBo(request);
		if(reqBo.getParamStr("username")==null||reqBo.getParamStr("password")==null){
			return new ModelAndView("index");
		}
		CmsUser cu = null;
		IUserService us = FindServiceUtil.findService(IUserService.class);
		ResBo<CmsUser> resBo = us.selectCmsUserByNameOrPas(reqBo);
		if(resBo.isSuccess()){
			cu = resBo.getResult();
			/*IRoleService role = FindServiceUtil.findService(IRoleService.class);
			ResBo<Role> roleResBo = role.selectRoleById(new ReqBo().setParam("id", cu.getRoleId()));
			if(!roleResBo.isSuccess()||roleResBo.getResult() == null){
				view = new ModelAndView("index");
				view.addObject("errMsg", "role info error username="+reqBo.getParamStr("username"));
				return view;
			}
			if(!roleResBo.getResult().getIsUse()){
				view = new ModelAndView("index");
				view.addObject("errMsg", "role info error rolename="+roleResBo.getResult().getRoleName());
				return view;
			}*/
			SessionUtil.replace(CarloanContant.CMSUSER, cu);
			/*String codes = roleResBo.getResult().getAreaCode();
			SessionUtil.replace(CarloanContant.OPERAREAALL, codes == null?"":codes);
			if(StringUtils.isNotBlank(codes)){
				String[] array = codes.split(",");
				if(array.length > 0){
					IAreaService areaSvc = FindServiceUtil.findService(IAreaService.class);
					ResBo<List<Area>> resb = areaSvc.selectAreasByCodes(new ReqBo().setParam("codes", array));
					if(resb.getResult().size() > 0){
						SessionUtil.replace(CarloanContant.OPERAREAID, resb.getResult().get(0).getCode());
					}
				}
			}*/
		}else{
			view = new ModelAndView("index");
			view.addObject("errMsg", resBo.getErrMsg());
			return view;
		}
		view.addObject("cmsuser", cu);
		return view;
	}
	
	@RequestMapping("ajaxLogin.htm")
	@ResponseBody
	public ResBo<?> ajaxLogin(HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		if(reqBo.getParamStr("username")==null||reqBo.getParamStr("password")==null){
			return new ResBo<Object>("username password is not exist");
		}
		CmsUser cu = null;
		IUserService us = FindServiceUtil.findService(IUserService.class);
		ResBo<CmsUser> resBo = us.selectCmsUserByNameOrPas(reqBo);
		if(resBo.isSuccess()){
			cu = resBo.getResult();
			/*IRoleService role = FindServiceUtil.findService(IRoleService.class);
			ResBo<Role> roleResBo = role.selectRoleById(new ReqBo().setParam("id", cu.getRoleId()));
			if(!roleResBo.isSuccess()||roleResBo.getResult() == null){
				return new ResBo<Object>("role info error username="+reqBo.getParamStr("username"));
			}
			if(!roleResBo.getResult().getIsUse()){
				return new ResBo<Object>("role info error rolename="+roleResBo.getResult().getRoleName());
			}*/
			SessionUtil.replace(CarloanContant.CMSUSER, cu);
			/*String codes = roleResBo.getResult().getAreaCode();
			SessionUtil.replace(CarloanContant.OPERAREAALL, codes == null?"":codes);*/
		}else{
			return new ResBo<Object>(resBo.getErrMsg());
		}
		return new ResBo<Object>();
	}
	
	/**
	 * 
	 * 退出系统
	 * */
	@RequestMapping("unlogin.htm")
	public String unlogin(){
		SessionUtil.delete(CarloanContant.CMSUSER);
		return "index";
	}

	@RequestMapping("navigation.htm")
	public String initUserNavigation() {
		return "navigater/user";
	}
	
	@RequestMapping("changeareacode.htm")
	@ResponseBody
	public ResBo<?> changeAreaCode(@RequestParam("code")String code){
		SessionUtil.replace(CarloanContant.OPERAREAID, code);
		return new ResBo<Object>();
	}

	/**
	 * 
	 * 根据条件，分页查找cms用户信息
	 * 
	 * *//*
	@RequestMapping(value = "infos.htm")
	@ResponseBody
	public ResBo<Pager<List<Map<String,Object>>>> getCmsUserInfos(
			HttpServletRequest request) {
		ReqBo reqBo = new ReqBo(request);
		IUserService us = FindServiceUtil.findService(IUserService.class);
		return us.selectCmsUsers(reqBo);
	}*/
	
	/**
	 * 
	 * 新增cms用户信息
	 * 
	 * *//*
	@RequestMapping("add.htm")
	@ResponseBody
	public ResBo<CmsUser> insertCmsUser(@ModelAttribute CmsUser cmsUser){
		cmsUser.setPassword(MD5Util.getMD5Str("123456"));
		IUserService us = FindServiceUtil.findService(IUserService.class);
		return us.insertCmsUser(new ReqBo(cmsUser));
	}*/
	
	/**
	 * 
	 * 
	 * */
	@RequestMapping("addinit.htm")
	public String insertInit(){
		return "user/addinit";
	}
	
	/**
	 *  修改cms用户信息
	 * *//*
	@RequestMapping("update.htm")
	@ResponseBody
	public ResBo<CmsUser> updateCmsUser(@ModelAttribute CmsUser cmsUser){
		IUserService us = FindServiceUtil.findService(IUserService.class);
		return us.updateCmsUser(new ReqBo(cmsUser));
	}
	
	*//**
	 * 
	 * 角色查询
	 * *//*
	@RequestMapping("roles.htm")
	@ResponseBody
	public ResBo<Pager<List<Role>>> selectRoles(@RequestParam("roleName") String roleName,@RequestParam("page") int start,@RequestParam("rows") int number){
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("roleName", roleName);
		reqBo.setParam("start", start);
		reqBo.setParam("number", number);
		IUserService us = FindServiceUtil.findService(IUserService.class);
		return us.selectRoles(reqBo);
	}
	
	@RequestMapping("addrole.htm")
	@ResponseBody
	public ResResult<Integer> addRole(@ModelAttribute Role role){
		role.setCreateTime(new Date());
		IUserService us = FindServiceUtil.findService(IUserService.class);
		ResBo<Role> rb = us.insertRole(new ReqBo(role));
		if(rb.isSuccess()){
			return new ResResult<Integer>(rb.getResult().getId());
		}
		return new ResResult<Integer>(rb.getErrCode(),rb.getErrMsg());
	}
	
	*//**
	 *  修改角色户信息
	 * *//*
	@RequestMapping("updaterole.htm")
	@ResponseBody
	public ResBo<?> updateCmsUser(@ModelAttribute Role role){
		IUserService us = FindServiceUtil.findService(IUserService.class);
		role.setModifyTime(new Date());
		return us.updateRole(new ReqBo(role));
	}
	
	*//**
	 * 
	 * 修改密码
	 * 
	 * *//*
	@RequestMapping("updatepass.htm")
	@ResponseBody
	public ResBo<?> updateCmsPass(@RequestParam("oldpass") String oldPass,@RequestParam("newpass") String newPass){
		CmsUser cu = (CmsUser) SessionUtil.get(CarloanContant.CMSUSER);
		if(!cu.getPassword().equals(MD5Util.getMD5Str(oldPass))){
			return new ResBo<Object>(9);
		}
		ReqBo reqBo = new ReqBo();
		reqBo.setParam("id", cu.getId());
		reqBo.setParam("pass", MD5Util.getMD5Str(newPass));
		IUserService us = FindServiceUtil.findService(IUserService.class);
		ResBo<?> resBo = us.updateCmsUserPass(reqBo);
		if(resBo.isSuccess()){
			cu.setPassword(MD5Util.getMD5Str(newPass));
		}
		return resBo;
	}
	*/
	@RequestMapping("initupdatepass.htm")
	public String initUserUpdatePass(){
		return "user/updatepass";
	}
	
	@RequestMapping("initgetcms.htm")
	public String initCmsUser(){
		return "user/selectCmsUser";
	}
	
	@RequestMapping("initNavigater.htm")
	public String initmemberManage(){
		return "navigater/background";
	}
}
