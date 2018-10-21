package com.yotrio.pound.model;

import com.yotrio.common.constants.SysUserConstants;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class SysPermission implements Serializable {
    private Integer id;

    /**
     * 权限名称
     *
     * @mbg.generated
     */
    private String permissionName;

    /**
     * 权限
     *
     * @mbg.generated
     */
    private String permission;

    /**
     * 权限描述
     *
     * @mbg.generated
     */
    private String permissionDesc;

    /**
     * 类型;menu、button
     *
     * @mbg.generated
     */
    private String type;

    /**
     * url
     *
     * @mbg.generated
     */
    private String url;

    /**
     * 父id
     *
     * @mbg.generated
     */
    private Integer pid;

    /**
     * 层级关系;0/1/41
     *
     * @mbg.generated
     */
    private String pids;

    /**
     * 是否启用
     *
     * @mbg.generated
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String statusName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        if (StringUtils.isNotEmpty(statusName)) {
            this.statusName = statusName;
        } else if (this.status == SysUserConstants.SYS_PERMISSION_STATUS_IS_ENABLE) {
            this.statusName = "已激活";
        } else if (this.status == SysUserConstants.SYS_PERMISSION_STATUS_UNABLE) {
            this.statusName = "未激活";
        } else {
            this.statusName = String.valueOf(this.status);
        }
    }

    public enum PermissionType {
        menu("菜单"), button("按钮");

        private String typeName;

        PermissionType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", permission=").append(permission);
        sb.append(", permissionDesc=").append(permissionDesc);
        sb.append(", type=").append(type);
        sb.append(", url=").append(url);
        sb.append(", pid=").append(pid);
        sb.append(", pids=").append(pids);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
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
        SysPermission other = (SysPermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPermissionName() == null ? other.getPermissionName() == null : this.getPermissionName().equals(other.getPermissionName()))
            && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()))
            && (this.getPermissionDesc() == null ? other.getPermissionDesc() == null : this.getPermissionDesc().equals(other.getPermissionDesc()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getPids() == null ? other.getPids() == null : this.getPids().equals(other.getPids()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPermissionName() == null) ? 0 : getPermissionName().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        result = prime * result + ((getPermissionDesc() == null) ? 0 : getPermissionDesc().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getPids() == null) ? 0 : getPids().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}