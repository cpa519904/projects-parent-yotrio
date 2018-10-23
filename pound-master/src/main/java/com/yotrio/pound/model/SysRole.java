package com.yotrio.pound.model;

import com.yotrio.common.constants.SysUserConstants;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class SysRole implements Serializable {
    private Integer id;

    /**
     * 角色名称
     *
     * @mbg.generated
     */
    private String roleName;

    /**
     * 角色中文名称
     *
     * @mbg.generated
     */
    private String roleCname;

    /**
     * 角色描述
     *
     * @mbg.generated
     */
    private String roleDesc;

    /**
     * 是否启用
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 状态名
     */
    private String statusName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCname() {
        return roleCname;
    }

    public void setRoleCname(String roleCname) {
        this.roleCname = roleCname;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        if (StringUtils.isNotEmpty(statusName)) {
            this.statusName = statusName;
        } else if (this.status == SysUserConstants.SYS_ROLE_STATUS_IS_ENABLE) {
            this.statusName = "已激活";
        } else if (this.status == SysUserConstants.SYS_ROLE_STATUS_UNABLE) {
            this.statusName = "未激活";
        } else {
            this.statusName = String.valueOf(this.status);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleCname=").append(roleCname);
        sb.append(", roleDesc=").append(roleDesc);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRole other = (SysRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
                && (this.getRoleCname() == null ? other.getRoleCname() == null : this.getRoleCname().equals(other.getRoleCname()))
                && (this.getRoleDesc() == null ? other.getRoleDesc() == null : this.getRoleDesc().equals(other.getRoleDesc()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRoleCname() == null) ? 0 : getRoleCname().hashCode());
        result = prime * result + ((getRoleDesc() == null) ? 0 : getRoleDesc().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}