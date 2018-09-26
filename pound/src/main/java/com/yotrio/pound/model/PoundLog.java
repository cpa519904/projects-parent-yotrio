package com.yotrio.pound.model;

import java.io.Serializable;
import java.util.Date;

public class PoundLog implements Serializable {
    /**
     * 自增id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 地磅id
     *
     * @mbg.generated
     */
    private Integer poundId;

    /**
     * 地磅名
     *
     * @mbg.generated
     */
    private String poundName;

    /**
     * 总重
     *
     * @mbg.generated
     */
    private Double grossWeight;

    /**
     * 毛重
     *
     * @mbg.generated
     */
    private Double tareWeight;

    /**
     * 净重
     *
     * @mbg.generated
     */
    private Double netWeight;

    /**
     * 总重图片
     *
     * @mbg.generated
     */
    private String grossImgUrl;

    /**
     * 毛重图片
     *
     * @mbg.generated
     */
    private String tareImgUrl;

    /**
     * 货品
     *
     * @mbg.generated
     */
    private String goodsName;

    /**
     * 供货商
     *
     * @mbg.generated
     */
    private String compName;

    /**
     * 收货单位
     *
     * @mbg.generated
     */
    private String unitName;

    /**
     * 货单号
     *
     * @mbg.generated
     */
    private String deliveryNumb;

    /**
     * 车牌号
     *
     * @mbg.generated
     */
    private String plateNumb;

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

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoundId() {
        return poundId;
    }

    public void setPoundId(Integer poundId) {
        this.poundId = poundId;
    }

    public String getPoundName() {
        return poundName;
    }

    public void setPoundName(String poundName) {
        this.poundName = poundName;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(Double tareWeight) {
        this.tareWeight = tareWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public String getGrossImgUrl() {
        return grossImgUrl;
    }

    public void setGrossImgUrl(String grossImgUrl) {
        this.grossImgUrl = grossImgUrl;
    }

    public String getTareImgUrl() {
        return tareImgUrl;
    }

    public void setTareImgUrl(String tareImgUrl) {
        this.tareImgUrl = tareImgUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDeliveryNumb() {
        return deliveryNumb;
    }

    public void setDeliveryNumb(String deliveryNumb) {
        this.deliveryNumb = deliveryNumb;
    }

    public String getPlateNumb() {
        return plateNumb;
    }

    public void setPlateNumb(String plateNumb) {
        this.plateNumb = plateNumb;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", poundId=").append(poundId);
        sb.append(", poundName=").append(poundName);
        sb.append(", grossWeight=").append(grossWeight);
        sb.append(", tareWeight=").append(tareWeight);
        sb.append(", netWeight=").append(netWeight);
        sb.append(", grossImgUrl=").append(grossImgUrl);
        sb.append(", tareImgUrl=").append(tareImgUrl);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", compName=").append(compName);
        sb.append(", unitName=").append(unitName);
        sb.append(", deliveryNumb=").append(deliveryNumb);
        sb.append(", plateNumb=").append(plateNumb);
        sb.append(", remark=").append(remark);
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
        PoundLog other = (PoundLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPoundId() == null ? other.getPoundId() == null : this.getPoundId().equals(other.getPoundId()))
            && (this.getPoundName() == null ? other.getPoundName() == null : this.getPoundName().equals(other.getPoundName()))
            && (this.getGrossWeight() == null ? other.getGrossWeight() == null : this.getGrossWeight().equals(other.getGrossWeight()))
            && (this.getTareWeight() == null ? other.getTareWeight() == null : this.getTareWeight().equals(other.getTareWeight()))
            && (this.getNetWeight() == null ? other.getNetWeight() == null : this.getNetWeight().equals(other.getNetWeight()))
            && (this.getGrossImgUrl() == null ? other.getGrossImgUrl() == null : this.getGrossImgUrl().equals(other.getGrossImgUrl()))
            && (this.getTareImgUrl() == null ? other.getTareImgUrl() == null : this.getTareImgUrl().equals(other.getTareImgUrl()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getCompName() == null ? other.getCompName() == null : this.getCompName().equals(other.getCompName()))
            && (this.getUnitName() == null ? other.getUnitName() == null : this.getUnitName().equals(other.getUnitName()))
            && (this.getDeliveryNumb() == null ? other.getDeliveryNumb() == null : this.getDeliveryNumb().equals(other.getDeliveryNumb()))
            && (this.getPlateNumb() == null ? other.getPlateNumb() == null : this.getPlateNumb().equals(other.getPlateNumb()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPoundId() == null) ? 0 : getPoundId().hashCode());
        result = prime * result + ((getPoundName() == null) ? 0 : getPoundName().hashCode());
        result = prime * result + ((getGrossWeight() == null) ? 0 : getGrossWeight().hashCode());
        result = prime * result + ((getTareWeight() == null) ? 0 : getTareWeight().hashCode());
        result = prime * result + ((getNetWeight() == null) ? 0 : getNetWeight().hashCode());
        result = prime * result + ((getGrossImgUrl() == null) ? 0 : getGrossImgUrl().hashCode());
        result = prime * result + ((getTareImgUrl() == null) ? 0 : getTareImgUrl().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getCompName() == null) ? 0 : getCompName().hashCode());
        result = prime * result + ((getUnitName() == null) ? 0 : getUnitName().hashCode());
        result = prime * result + ((getDeliveryNumb() == null) ? 0 : getDeliveryNumb().hashCode());
        result = prime * result + ((getPlateNumb() == null) ? 0 : getPlateNumb().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}