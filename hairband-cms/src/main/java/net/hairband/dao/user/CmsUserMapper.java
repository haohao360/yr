package net.hairband.dao.user;

import java.util.List;
import net.hairband.dto.user.CmsUser;
import net.hairband.dto.user.CmsUserExample;
import org.apache.ibatis.annotations.Param;

public interface CmsUserMapper {
    int countByExample(CmsUserExample example);

    int deleteByExample(CmsUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsUser record);

    int insertSelective(CmsUser record);

    List<CmsUser> selectByExample(CmsUserExample example);

    CmsUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsUser record, @Param("example") CmsUserExample example);

    int updateByExample(@Param("record") CmsUser record, @Param("example") CmsUserExample example);

    int updateByPrimaryKeySelective(CmsUser record);

    int updateByPrimaryKey(CmsUser record);
}