package net.hairband.component.user.impl;

import java.util.List;

import net.hairband.component.user.IUserComponent;
import net.hairband.dao.user.CmsUserMapper;
import net.hairband.dto.user.CmsUser;
import net.hairband.dto.user.CmsUserExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.framework.BusinessExceptionUtil;
@Component
public class UserComponent implements IUserComponent{
	@Autowired
	private CmsUserMapper cmsUserMapper;
	@Override
	public CmsUser selectCmsUser(String username, String password) {
		CmsUserExample e = new CmsUserExample();
		e.createCriteria().andUserNameEqualTo(username).andPasswordEqualTo(password).andStateEqualTo(1);
		List<CmsUser> list = cmsUserMapper.selectByExample(e);
		if(list.size() > 1){
			throw BusinessExceptionUtil.createBusinessException(5L, username);
		}
		if(list.size() == 1){
			return list.get(0);
		}
		return null;
	}

}
