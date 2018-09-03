package net.hairband.component.user;

import net.hairband.dto.user.CmsUser;

public interface IUserComponent {

	CmsUser selectCmsUser(String paramStr, String md5Str);

}
