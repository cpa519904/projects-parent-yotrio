package com.yotrio.pound.model;

import java.io.Serializable;
import java.util.Date;
public class PoundInfo implements Serializable {
    /**
     * 自增id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 地磅名
     *
     * @mbg.generated
     */
    private String poundName;

    /**
     * 管理员工号
     *
     * @mbg.generated
     */
    private Integer adminEmpId;

    /**
     * 管理员名称
     *
     * @mbg.generated
     */
    private String adminName;

    /**
     * 管理员手机
     *
     * @mbg.generated
     */
    private String adminMobile;

    /**
     * 所属单位
     *
     * @mbg.generated
     */
    private Integer unitId;

    /**
     * 型号
     *
     * @mbg.generated
     */
    private String model;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 状态
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoundName() {
        return poundName;
    }


    public Integer getAdminEmpId() {
        return adminEmpId;
    }

    public void setAdminEmpId(Integer adminEmpId) {
        this.adminEmpId = adminEmpId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile;
    }

    public void setPoundName(String poundName) {
        this.poundName = poundName;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", poundName=").append(poundName);
        sb.append(", adminEmpId=").append(adminEmpId);
        sb.append(", adminName=").append(adminName);
        sb.append(", adminMobile=").append(adminMobile);
        sb.append(", unitId=").append(unitId);
        sb.append(", model=").append(model);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
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
        PoundInfo other = (PoundInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPoundName() == null ? other.getPoundName() == null : this.getPoundName().equals(other.getPoundName()))
            && (this.getAdminEmpId() == null ? other.getAdminEmpId() == null : this.getAdminEmpId().equals(other.getAdminEmpId()))
            && (this.getAdminName() == null ? other.getAdminName() == null : this.getAdminName().equals(other.getAdminName()))
            && (this.getAdminMobile() == null ? other.getAdminMobile() == null : this.getAdminMobile().equals(other.getAdminMobile()))
            && (this.getUnitId() == null ? other.getUnitId() == null : this.getUnitId().equals(other.getUnitId()))
            && (this.getModel() == null ? other.getModel() == null : this.getModel().equals(other.getModel()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPoundName() == null) ? 0 : getPoundName().hashCode());
        result = prime * result + ((getAdminEmpId() == null) ? 0 : getAdminEmpId().hashCode());
        result = prime * result + ((getAdminName() == null) ? 0 : getAdminName().hashCode());
        result = prime * result + ((getAdminMobile() == null) ? 0 : getAdminMobile().hashCode());
        result = prime * result + ((getUnitId() == null) ? 0 : getUnitId().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}