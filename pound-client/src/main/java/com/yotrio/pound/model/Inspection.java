package com.yotrio.pound.model;

import java.io.Serializable;
import java.util.Date;

public class Inspection implements Serializable {
    /**
     * 自增id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 过磅单id
     *
     * @mbg.generated
     */
    private Integer plId;

    /**
     * 过磅单号
     *
     * @mbg.generated
     */
    private String plNo;

    /**
     * 报检单号
     *
     * @mbg.generated
     */
    private String inspNo;

    /**
     * 车牌号
     *
     * @mbg.generated
     */
    private String plateNo;

    /**
     * 货品类型
     *
     * @mbg.generated
     */
    private Integer goodsKind;

    /**
     * 供货商
     *
     * @mbg.generated
     */
    private String compName;

    /**
     * 报检单重量
     *
     * @mbg.generated
     */
    private Double inspWeight;

    /**
     * 随车退重量
     *
     * @mbg.generated
     */
    private Double returnWeight;

    /**
     * 类型：是否样品
     *
     * @mbg.generated
     */
    private Integer types;

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

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    /** 冗余字段 **/

    /**
     * 过磅单位
     */
    private String unit_name;

    /**
     * 货品类型名称
     */
    private String goodsKindName;

    private String sample;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlId() {
        return plId;
    }

    public void setPlId(Integer plId) {
        this.plId = plId;
    }

    public String getPlNo() {
        return plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public String getInspNo() {
        return inspNo;
    }

    public void setInspNo(String inspNo) {
        this.inspNo = inspNo;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Integer getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(Integer goodsKind) {
        this.goodsKind = goodsKind;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Double getInspWeight() {
        return inspWeight;
    }

    public void setInspWeight(Double inspWeight) {
        this.inspWeight = inspWeight;
    }

    public Double getReturnWeight() {
        return returnWeight;
    }

    public void setReturnWeight(Double returnWeight) {
        this.returnWeight = returnWeight;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
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

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getGoodsKindName() {
        return goodsKindName;
    }

    public void setGoodsKindName(String goodsKindName) {
        this.goodsKindName = goodsKindName;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", plId=").append(plId);
        sb.append(", plNo=").append(plNo);
        sb.append(", inspNo=").append(inspNo);
        sb.append(", plateNo=").append(plateNo);
        sb.append(", goodsKind=").append(goodsKind);
        sb.append(", compName=").append(compName);
        sb.append(", inspWeight=").append(inspWeight);
        sb.append(", returnWeight=").append(returnWeight);
        sb.append(", types=").append(types);
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
        Inspection other = (Inspection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlId() == null ? other.getPlId() == null : this.getPlId().equals(other.getPlId()))
            && (this.getPlNo() == null ? other.getPlNo() == null : this.getPlNo().equals(other.getPlNo()))
            && (this.getInspNo() == null ? other.getInspNo() == null : this.getInspNo().equals(other.getInspNo()))
            && (this.getPlateNo() == null ? other.getPlateNo() == null : this.getPlateNo().equals(other.getPlateNo()))
            && (this.getGoodsKind() == null ? other.getGoodsKind() == null : this.getGoodsKind().equals(other.getGoodsKind()))
            && (this.getCompName() == null ? other.getCompName() == null : this.getCompName().equals(other.getCompName()))
            && (this.getInspWeight() == null ? other.getInspWeight() == null : this.getInspWeight().equals(other.getInspWeight()))
            && (this.getReturnWeight() == null ? other.getReturnWeight() == null : this.getReturnWeight().equals(other.getReturnWeight()))
            && (this.getTypes() == null ? other.getTypes() == null : this.getTypes().equals(other.getTypes()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlId() == null) ? 0 : getPlId().hashCode());
        result = prime * result + ((getPlNo() == null) ? 0 : getPlNo().hashCode());
        result = prime * result + ((getInspNo() == null) ? 0 : getInspNo().hashCode());
        result = prime * result + ((getPlateNo() == null) ? 0 : getPlateNo().hashCode());
        result = prime * result + ((getGoodsKind() == null) ? 0 : getGoodsKind().hashCode());
        result = prime * result + ((getCompName() == null) ? 0 : getCompName().hashCode());
        result = prime * result + ((getInspWeight() == null) ? 0 : getInspWeight().hashCode());
        result = prime * result + ((getReturnWeight() == null) ? 0 : getReturnWeight().hashCode());
        result = prime * result + ((getTypes() == null) ? 0 : getTypes().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}