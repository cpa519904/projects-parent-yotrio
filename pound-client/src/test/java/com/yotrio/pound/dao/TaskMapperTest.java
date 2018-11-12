package com.yotrio.pound.dao;

import com.yotrio.pound.model.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void selectListByStatus() {
        List<Task> tasks = taskMapper.selectListByStatus(0);
    }

    @Test
    public void selectListByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        List<Task> tasks = taskMapper.selectListByMap(map);
        System.out.println(tasks);

    }
}