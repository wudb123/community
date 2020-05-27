package cn.wdb.community.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import cn.wdb.community.pojo.User;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
    @Update("update user set username=#{username},bio=#{bio},avatar_url=#{avatar_url},token=#{token},gmtCreated=#{gmtCreated},gmtModified=#{gmtModified} where accountId=#{accountId}")
    void updateByAccountId(User user);

}
