package com.yotrio.pound.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 过磅单号
     *
     * @mbg.generated
     */
    private String poundLogNo;

    /**
     * 毛重
     *
     * @mbg.generated
     */
    private Double grossWeight;

    /**
     * 皮重
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
     * 样品净重
     *
     * @mbg.generated
     */
    private Double sampleNetWeight;

    /**
     * 随车退货重量
     *
     * @mbg.generated
     */
    private Double returnWeightTotal;

    /**
     * 磅差
     *
     * @mbg.generated
     */
    private Double diffWeight;

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
     * 收货单位
     *
     * @mbg.generated
     */
    private String unitName;

    /**
     * 车牌号
     *
     * @mbg.generated
     */
    private String plateNo;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * 类型
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
     * 是否退货 0不是，1退货
     *
     * @mbg.generated
     */
    private Integer returnGoods;

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

    /**
     * 第一次过磅时间
     *
     * @mbg.generated
     */
    private Date firstTime;

    /**
     * 第二次过磅时间
     *
     * @mbg.generated
     */
    private Date secondTime;

    private List<Inspection> inspections;

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

    public String getPoundLogNo() {
        return poundLogNo;
    }

    public void setPoundLogNo(String poundLogNo) {
        this.poundLogNo = poundLogNo;
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

    public Double getSampleNetWeight() {
        return sampleNetWeight;
    }

    public void setSampleNetWeight(Double sampleNetWeight) {
        this.sampleNetWeight = sampleNetWeight;
    }

    public Double getReturnWeightTotal() {
        return returnWeightTotal;
    }

    public void setReturnWeightTotal(Double returnWeightTotal) {
        this.returnWeightTotal = returnWeightTotal;
    }

    public Double getDiffWeight() {
        return diffWeight;
    }

    public void setDiffWeight(Double diffWeight) {
        this.diffWeight = diffWeight;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getReturnGoods() {
        return returnGoods;
    }

    public void setReturnGoods(Integer returnGoods) {
        this.returnGoods = returnGoods;
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

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(Date secondTime) {
        this.secondTime = secondTime;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
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
        sb.append(", poundLogNo=").append(poundLogNo);
        sb.append(", grossWeight=").append(grossWeight);
        sb.append(", tareWeight=").append(tareWeight);
        sb.append(", netWeight=").append(netWeight);
        sb.append(", sampleNetWeight=").append(sampleNetWeight);
        sb.append(", returnWeightTotal=").append(returnWeightTotal);
        sb.append(", diffWeight=").append(diffWeight);
        sb.append(", grossImgUrl=").append(grossImgUrl);
        sb.append(", tareImgUrl=").append(tareImgUrl);
        sb.append(", unitName=").append(unitName);
        sb.append(", plateNo=").append(plateNo);
        sb.append(", remark=").append(remark);
        sb.append(", types=").append(types);
        sb.append(", status=").append(status);
        sb.append(", returnGoods=").append(returnGoods);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", firstTime=").append(firstTime);
        sb.append(", secondTime=").append(secondTime);
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
            && (this.getPoundLogNo() == null ? other.getPoundLogNo() == null : this.getPoundLogNo().equals(other.getPoundLogNo()))
            && (this.getGrossWeight() == null ? other.getGrossWeight() == null : this.getGrossWeight().equals(other.getGrossWeight()))
            && (this.getTareWeight() == null ? other.getTareWeight() == null : this.getTareWeight().equals(other.getTareWeight()))
            && (this.getNetWeight() == null ? other.getNetWeight() == null : this.getNetWeight().equals(other.getNetWeight()))
            && (this.getSampleNetWeight() == null ? other.getSampleNetWeight() == null : this.getSampleNetWeight().equals(other.getSampleNetWeight()))
            && (this.getReturnWeightTotal() == null ? other.getReturnWeightTotal() == null : this.getReturnWeightTotal().equals(other.getReturnWeightTotal()))
            && (this.getDiffWeight() == null ? other.getDiffWeight() == null : this.getDiffWeight().equals(other.getDiffWeight()))
            && (this.getGrossImgUrl() == null ? other.getGrossImgUrl() == null : this.getGrossImgUrl().equals(other.getGrossImgUrl()))
            && (this.getTareImgUrl() == null ? other.getTareImgUrl() == null : this.getTareImgUrl().equals(other.getTareImgUrl()))
            && (this.getUnitName() == null ? other.getUnitName() == null : this.getUnitName().equals(other.getUnitName()))
            && (this.getPlateNo() == null ? other.getPlateNo() == null : this.getPlateNo().equals(other.getPlateNo()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getTypes() == null ? other.getTypes() == null : this.getTypes().equals(other.getTypes()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getReturnGoods() == null ? other.getReturnGoods() == null : this.getReturnGoods().equals(other.getReturnGoods()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getFirstTime() == null ? other.getFirstTime() == null : this.getFirstTime().equals(other.getFirstTime()))
            && (this.getSecondTime() == null ? other.getSecondTime() == null : this.getSecondTime().equals(other.getSecondTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPoundId() == null) ? 0 : getPoundId().hashCode());
        result = prime * result + ((getPoundName() == null) ? 0 : getPoundName().hashCode());
        result = prime * result + ((getPoundLogNo() == null) ? 0 : getPoundLogNo().hashCode());
        result = prime * result + ((getGrossWeight() == null) ? 0 : getGrossWeight().hashCode());
        result = prime * result + ((getTareWeight() == null) ? 0 : getTareWeight().hashCode());
        result = prime * result + ((getNetWeight() == null) ? 0 : getNetWeight().hashCode());
        result = prime * result + ((getSampleNetWeight() == null) ? 0 : getSampleNetWeight().hashCode());
        result = prime * result + ((getReturnWeightTotal() == null) ? 0 : getReturnWeightTotal().hashCode());
        result = prime * result + ((getDiffWeight() == null) ? 0 : getDiffWeight().hashCode());
        result = prime * result + ((getGrossImgUrl() == null) ? 0 : getGrossImgUrl().hashCode());
        result = prime * result + ((getTareImgUrl() == null) ? 0 : getTareImgUrl().hashCode());
        result = prime * result + ((getUnitName() == null) ? 0 : getUnitName().hashCode());
        result = prime * result + ((getPlateNo() == null) ? 0 : getPlateNo().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getTypes() == null) ? 0 : getTypes().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReturnGoods() == null) ? 0 : getReturnGoods().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getFirstTime() == null) ? 0 : getFirstTime().hashCode());
        result = prime * result + ((getSecondTime() == null) ? 0 : getSecondTime().hashCode());
        return result;
    }
}