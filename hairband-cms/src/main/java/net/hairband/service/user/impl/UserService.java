package net.hairband.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.framework.MD5Util;
import framework.web.ReqBo;
import framework.web.ResBo;
import net.hairband.component.user.IUserComponent;
import net.hairband.dto.user.CmsUser;
import net.hairband.service.user.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserComponent userComponent;
	
	/*@Override
	public ResBo<Boolean> isAuth(ReqBo reqBo) {
		String url = reqBo.getParamStr("url");
		String[] ss = url.split("/");
		if(ss.length == 4){
			StringBuilder sb = new StringBuilder("/");
			sb.append(ss[2]);
			sb.append("/");
			sb.append(ss[3]);
			url = sb.toString();
		}
		RoleFunction rf = userComponent.selectRoleFunctionByUrl(reqBo.getParamInt("roleId"), url);
		boolean result = false;
		if(rf != null&&rf.getIsUse()){
			result = true;
		}
		if(rf == null){
			result = !functionCom.selectIsLog(url);
		}
		return new ResBo<Boolean>(result);
	}
*/
	/*@Override
	public ResBo<CmsUser> insertCmsUser(ReqBo reqBo) {
		CmsUser cu = reqBo.getReqModel(CmsUser.class);
		int count = userComponent.selectCmsUserIsExist(cu.getUserName().trim());
		if(count > 0){
			return new ResBo<CmsUser>(4,cu.getUserName());
		}
		userComponent.insertCmsUser(cu);
		return new ResBo<CmsUser>();
	}

	@Override
	public ResBo<CmsUser> updateCmsUser(ReqBo reqBo) {
		userComponent.updateCmsUser(reqBo.getReqModel(CmsUser.class));
		return new ResBo<CmsUser>();
	}
	
	@Override
	public ResBo<?> updateCmsUserPass(ReqBo reqBo){
		CmsUser cu = new CmsUser();
		cu.setId(reqBo.getParamLong("id"));
		cu.setPassword(reqBo.getParamStr("pass"));
		userComponent.updateCmsUser(cu);
		return new ResBo<Object>();
	}*/



/*
	@Override
	public ResBo<Pager<List<Map<String,Object>>>> selectCmsUsers(ReqBo reqBo) {
		return new ResBo<Pager<List<Map<String,Object>>>>(userComponent.selectCmsUsers(reqBo.getParamStr("name"), reqBo.getParamStr("mobile"), reqBo.getParamStr("department"), reqBo.getParamInt("roleId"), reqBo.getParamInt("page")-1, reqBo.getParamInt("rows")));
	}
*/


	@Override
	public ResBo<CmsUser> selectCmsUserByNameOrPas(ReqBo reqBo) {
		CmsUser cu = userComponent.selectCmsUser(reqBo.getParamStr("username"), MD5Util.getMD5Str(reqBo.getParamStr("password")));
		if(cu == null){
			return new ResBo<CmsUser>(6);
		}
		return new ResBo<CmsUser>(cu);
	}

}
