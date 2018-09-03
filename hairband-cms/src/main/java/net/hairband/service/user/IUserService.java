package net.hairband.service.user;

import net.hairband.dto.user.CmsUser;
import framework.web.ReqBo;
import framework.web.ResBo;

public interface IUserService {
	/**
	 * 
	 * 根据用户名密码查询用cms户信息
	 * 
	 * */
	public ResBo<CmsUser> selectCmsUserByNameOrPas(ReqBo reqBo);
	
}
