package cn.wdb.community.mapper;

import org.springframework.stereotype.Repository;
import cn.wdb.community.pojo.User;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}
