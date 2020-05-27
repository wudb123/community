package cn.wdb.community.mapper;

import cn.wdb.community.pojo.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface QuestionMapper extends Mapper<Question> {
    @Update("update question set viewCount=#{viewCount} where id =#{id}")
    void updateViewById(Question question);
}
