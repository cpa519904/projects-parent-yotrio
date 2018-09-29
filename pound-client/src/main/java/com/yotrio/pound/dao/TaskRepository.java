package com.yotrio.pound.dao;

import com.yotrio.pound.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ${DESCRIPTION}
 * 模块名称：study-boot.com.wangyq.dao
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-08-31 下午5:04
 * 系统版本：1.0.0
 **/
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * 根据用户名查找，条件名要跟属性名对上，不能乱写
     * @param username
     * @return
     */
    public List<Task> findByTaskName(String username);
}
