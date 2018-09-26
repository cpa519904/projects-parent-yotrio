package com.yotrio.common.domain;
public class Capital extends BaseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private Double capitalBalance = new Double(0);		//帐户余额
	private Double capitalAvailable = new Double(0);	//可用余额
	private Double capitalFrozen = new Double(0);		//冻结余额
	private Double capitalIn = new Double(0);			//资金存入
	private Double capitalOut = new Double(0);			//资金取出
	private Double capitalPrize = new Double(0);		//累计奖金
	private Double capitalConsume = new Double(0);		//累计消费
	private Integer capitalScore = 0;
	private Integer capitalScoreSynchro = 0;
	private Integer capitalType = 0;
	private Double transferIn = new Double(0);			//转账存入
	private Double transferOut = new Double(0);			//转账取出
	private Integer generalize = 0;
	private Double generalizeConsume = new Double(0);
	private Double generalizeBrokerage = new Double(0);
	private Double generalizeExchange = new Double(0);
	private Integer generalizeRebate = 0;
	private Double generalizeConsumeR = new Double(0);
	private Double generalizeBrokerageR = new Double(0);
	private byte[] dataLock;
	private String userName;	//冗余数据
	public Capital() {}
	public Capital(Integer userId, Double capitalBalance, Double capitalAvailable, Double capitalFrozen, Double capitalIn, Double capitalOut, Double capitalPrize, Double capitalConsume, Integer capitalScore, Integer capitalScoreSynchro, Integer capitalType, Double transferIn, Double transferOut, Integer generalize, Double generalizeConsume, Double generalizeBrokerage, Double generalizeExchange,
                   Integer generalizeRebate, Double generalizeConsumeR, Double generalizeBrokerageR, byte[] dataLock) {
		this.userId = userId;
		this.capitalBalance = capitalBalance;
		this.capitalAvailable = capitalAvailable;
		this.capitalFrozen = capitalFrozen;
		this.capitalIn = capitalIn;
		this.capitalOut = capitalOut;
		this.capitalPrize = capitalPrize;
		this.capitalConsume = capitalConsume;
		this.capitalScore = capitalScore;
		this.capitalScoreSynchro = capitalScoreSynchro;
		this.capitalType = capitalType;
		this.transferIn = transferIn;
		this.transferOut = transferOut;
		this.generalize = generalize;
		this.generalizeConsume = generalizeConsume;
		this.generalizeBrokerage = generalizeBrokerage;
		this.generalizeExchange = generalizeExchange;
		this.generalizeRebate = generalizeRebate;
		this.generalizeConsumeR = generalizeConsumeR;
		this.generalizeBrokerageR = generalizeBrokerageR;
		this.dataLock = dataLock;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getCapitalBalance() {
		return this.capitalBalance;
	}
	public void setCapitalBalance(Double capitalBalance) {
		this.capitalBalance = capitalBalance;
	}
	public Double getCapitalAvailable() {
		return this.capitalAvailable;
	}
	public void setCapitalAvailable(Double capitalAvailable) {
		this.capitalAvailable = capitalAvailable;
	}
	public Double getCapitalFrozen() {
		return this.capitalFrozen;
	}
	public void setCapitalFrozen(Double capitalFrozen) {
		this.capitalFrozen = capitalFrozen;
	}
	public Double getCapitalIn() {
		return this.capitalIn;
	}
	public void setCapitalIn(Double capitalIn) {
		this.capitalIn = capitalIn;
	}
	public Double getCapitalOut() {
		return this.capitalOut;
	}
	public void setCapitalOut(Double capitalOut) {
		this.capitalOut = capitalOut;
	}
	public Double getCapitalPrize() {
		return this.capitalPrize;
	}
	public void setCapitalPrize(Double capitalPrize) {
		this.capitalPrize = capitalPrize;
	}
	public Double getCapitalConsume() {
		return this.capitalConsume;
	}
	public void setCapitalConsume(Double capitalConsume) {
		this.capitalConsume = capitalConsume;
	}
	public Integer getCapitalScore() {
		return this.capitalScore;
	}
	public void setCapitalScore(Integer capitalScore) {
		this.capitalScore = capitalScore;
	}
	public Integer getCapitalScoreSynchro() {
		return this.capitalScoreSynchro;
	}
	public void setCapitalScoreSynchro(Integer capitalScoreSynchro) {
		this.capitalScoreSynchro = capitalScoreSynchro;
	}
	public Integer getCapitalType() {
		return this.capitalType;
	}
	public void setCapitalType(Integer capitalType) {
		this.capitalType = capitalType;
	}
	public Double getTransferIn() {
		return this.transferIn;
	}
	public void setTransferIn(Double transferIn) {
		this.transferIn = transferIn;
	}
	public Double getTransferOut() {
		return this.transferOut;
	}
	public void setTransferOut(Double transferOut) {
		this.transferOut = transferOut;
	}
	public Integer getGeneralize() {
		return this.generalize;
	}
	public void setGeneralize(Integer generalize) {
		this.generalize = generalize;
	}
	public Double getGeneralizeConsume() {
		return this.generalizeConsume;
	}
	public void setGeneralizeConsume(Double generalizeConsume) {
		this.generalizeConsume = generalizeConsume;
	}
	public Double getGeneralizeBrokerage() {
		return this.generalizeBrokerage;
	}
	public void setGeneralizeBrokerage(Double generalizeBrokerage) {
		this.generalizeBrokerage = generalizeBrokerage;
	}
	public Double getGeneralizeExchange() {
		return this.generalizeExchange;
	}
	public void setGeneralizeExchange(Double generalizeExchange) {
		this.generalizeExchange = generalizeExchange;
	}
	public Integer getGeneralizeRebate() {
		return this.generalizeRebate;
	}
	public void setGeneralizeRebate(Integer generalizeRebate) {
		this.generalizeRebate = generalizeRebate;
	}
	public Double getGeneralizeConsumeR() {
		return this.generalizeConsumeR;
	}
	public void setGeneralizeConsumeR(Double generalizeConsumeR) {
		this.generalizeConsumeR = generalizeConsumeR;
	}
	public Double getGeneralizeBrokerageR() {
		return this.generalizeBrokerageR;
	}
	public void setGeneralizeBrokerageR(Double generalizeBrokerageR) {
		this.generalizeBrokerageR = generalizeBrokerageR;
	}
	public byte[] getDataLock() {
		return this.dataLock;
	}
	public void setDataLock(byte[] dataLock) {
		this.dataLock = dataLock;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Capital");
		sb.append("{id=").append(id);
		sb.append(", userId=").append(userId);
		sb.append(", capitalBalance=").append(capitalBalance);
		sb.append(", capitalAvailable=").append(capitalAvailable);
		sb.append(", capitalFrozen=").append(capitalFrozen);
		sb.append(", capitalIn=").append(capitalIn);
		sb.append(", capitalOut=").append(capitalOut);
		sb.append(", capitalPrize=").append(capitalPrize);
		sb.append(", capitalConsume=").append(capitalConsume);
		sb.append(", capitalScore=").append(capitalScore);
		sb.append(", capitalScoreSynchro=").append(capitalScoreSynchro);
		sb.append(", capitalType=").append(capitalType);
		sb.append(", transferIn=").append(transferIn);
		sb.append(", transferOut=").append(transferOut);
		sb.append(", generalize=").append(generalize);
		sb.append(", generalizeConsume=").append(generalizeConsume);
		sb.append(", generalizeBrokerage=").append(generalizeBrokerage);
		sb.append(", generalizeExchange=").append(generalizeExchange);
		sb.append(", generalizeRebate=").append(generalizeRebate);
		sb.append(", generalizeConsumeR=").append(generalizeConsumeR);
		sb.append(", generalizeBrokerageR=").append(generalizeBrokerageR);
		sb.append(", dataLock=").append(dataLock == null ? "null" : "");
		for (int i = 0; dataLock != null && i < dataLock.length; ++i)
			sb.append(i == 0 ? "" : ", ").append(dataLock[i]);
		sb.append('}');
		return sb.toString();
	}
}