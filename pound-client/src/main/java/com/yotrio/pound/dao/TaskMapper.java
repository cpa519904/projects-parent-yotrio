package com.yotrio.pound.dao;

import com.yotrio.pound.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> selectListByMap(Map<String, Object> map);

    List<Task> selectListByStatus(Integer status);

    List<Task> findUnFinishTasksLimit(int account);

    Task findByOtherId(String poundLogNo);
}