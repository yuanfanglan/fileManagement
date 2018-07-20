package com.yfl.mapper;

import com.yfl.pojo.Homeuser;
import com.yfl.pojo.HomeuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomeuserMapper {
    long countByExample(HomeuserExample example);

    int deleteByExample(HomeuserExample example);

    int deleteByPrimaryKey(String username);

    int insert(Homeuser record);

    int insertSelective(Homeuser record);

    List<Homeuser> selectByExample(HomeuserExample example);

    Homeuser selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") Homeuser record, @Param("example") HomeuserExample example);

    int updateByExample(@Param("record") Homeuser record, @Param("example") HomeuserExample example);

    int updateByPrimaryKeySelective(Homeuser record);

    int updateByPrimaryKey(Homeuser record);
}