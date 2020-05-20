package cn.wdb.community.mapper;

import cn.wdb.community.pojo.Question;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface QuestionMapper extends Mapper<Question> {
}
