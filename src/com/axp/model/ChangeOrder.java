package com.axp.model;

import java.sql.Timestamp;

public class ChangeOrder implements java.io.Serializable  {
	/**未接受*/
	public static final Integer UNACCEPTED=0; //未接受
	/**接受*/
	public static final Integer ACCEPTED=1; //接受
	/**拒绝*/
	public static final Integer REFUSE =2; //拒绝
	/**删除*/
	public static final Integer DELETE=3; //删除  
	/**完成*/
	public static final Integer SUCCESS=4; //完成
	
	/**已删除*/
	public static final Integer orderStatus_yi_shan_chu=-1; //已删除   //这个已删除 是为了 在自己看不到的情况下 对方还能看到
	/**待发货*/
	public static final Integer orderStatus_dai_fa_huo=10; //待发货
	/**已发货*/
	public static final Integer orderStatus_yi_fa_huo=20; //已发货
	/**已收货*/
	public static final Integer orderStatus_yi_shou_huo=30; //已收货
	/**确认支付*/
	public static final Integer orderStatus_que_ren_zhi_fu=40; //确认支付 //针对积分
	/**已完成*/
	public static final Integer orderStatus_jiao_yi_wan_cheng=50; //已完成
	
	/**
	 * 超时时间
	 */
	public static final Integer HOUR=360;
	
	public String getStatusName(Integer status){
		String msg="未知状态";
		if(status==orderStatus_dai_fa_huo){
			msg="待发货";
		}else if(status==orderStatus_yi_fa_huo){
			msg="已发货";
		}else if(status==orderStatus_yi_shou_huo){
			msg="已收货";
		}else if(status==orderStatus_que_ren_zhi_fu){
			msg="已支付";
		}else if(status==orderStatus_jiao_yi_wan_cheng){
			msg="已完成";
		}
		return msg;
	}
	
	//交换类型
	/**以物换物 */
	public static final Integer CHANGEGOODS=10;  //以物换物 
	/**积分换物 */
	public static final Integer CHANGESCORE=20;  //积分换物
	
	private ReGoodsOfChangeMall  acceptGoods; // 被邀请方商品  
	
	private Integer acceptGoodsNum; //被邀请方商品数量
	
	private Users acceptUsers;   //被邀请方用户
	
	private Timestamp createTime; //创建时间
	
	private Integer id;
	
	private ReGoodsOfChangeMall inviteGoods; //发起方商品

	private Integer inviteGoodsNum; //发起方交换数量

	private Users inviteUsers;  //发起用户
	
	private Boolean isValid;
	
	private Timestamp sponsorTime; //发起换货时间

	private Integer status; //状态
	
	private Integer changeScore;
	
	private Integer changeType;
	
	
	private Timestamp inviteSendGoodsTime;
	
	private Boolean inviteRed;
	
	private Boolean acceptRed;
	
	public Boolean getInviteRed() {
		return inviteRed;
	}

	public void setInviteRed(Boolean inviteRed) {
		this.inviteRed = inviteRed;
	}

	public Boolean getAcceptRed() {
		return acceptRed;
	}

	public void setAcceptRed(Boolean acceptRed) {
		this.acceptRed = acceptRed;
	}

	public Timestamp getInviteSendGoodsTime() {
		return inviteSendGoodsTime;
	}

	public void setInviteSendGoodsTime(Timestamp inviteSendGoodsTime) {
		this.inviteSendGoodsTime = inviteSendGoodsTime;
	}

	public Timestamp getAcceptSendGoodsTime() {
		return acceptSendGoodsTime;
	}

	public void setAcceptSendGoodsTime(Timestamp acceptSendGoodsTime) {
		this.acceptSendGoodsTime = acceptSendGoodsTime;
	}

	private Timestamp acceptSendGoodsTime;
	
	

	public Integer getChangeScore() {
		return changeScore;
	}

	public void setChangeScore(Integer changeScore) {
		this.changeScore = changeScore;
	}

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}

	private Timestamp acceptConfirmTime;
	
	private Timestamp inviteConfirmTime;
	
	private Integer inviteStatus;
	
	public Timestamp getAcceptConfirmTime() {
		return acceptConfirmTime;
	}

	public void setAcceptConfirmTime(Timestamp acceptConfirmTime) {
		this.acceptConfirmTime = acceptConfirmTime;
	}

	public Timestamp getInviteConfirmTime() {
		return inviteConfirmTime;
	}

	public void setInviteConfirmTime(Timestamp inviteConfirmTime) {
		this.inviteConfirmTime = inviteConfirmTime;
	}

	public Integer getInviteStatus() {
		return inviteStatus;
	}

	public void setInviteStatus(Integer inviteStatus) {
		this.inviteStatus = inviteStatus;
	}

	public Integer getAcceptStatus() {
		return acceptStatus;
	}

	public void setAcceptStatus(Integer acceptStatus) {
		this.acceptStatus = acceptStatus;
	}

	private Integer acceptStatus;
	
	public ReGoodsOfChangeMall getAcceptGoods() {
		return acceptGoods;
	}

	public Integer getAcceptGoodsNum() {
		return acceptGoodsNum;
	}

	public Users getAcceptUsers() {
		return acceptUsers;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public Integer getId() {
		return id;
	}

	public ReGoodsOfChangeMall getInviteGoods() {
		return inviteGoods;
	}

	public Integer getInviteGoodsNum() {
		return inviteGoodsNum;
	}

	public Users getInviteUsers() {
		return inviteUsers;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public Timestamp getSponsorTime() {
		return sponsorTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setAcceptGoods(ReGoodsOfChangeMall acceptGoods) {
		this.acceptGoods = acceptGoods;
	}

	public void setAcceptGoodsNum(Integer acceptGoodsNum) {
		this.acceptGoodsNum = acceptGoodsNum;
	}

	public void setAcceptUsers(Users acceptUsers) {
		this.acceptUsers = acceptUsers;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInviteGoods(ReGoodsOfChangeMall inviteGoods) {
		this.inviteGoods = inviteGoods;
	}

	public void setInviteGoodsNum(Integer inviteGoodsNum) {
		this.inviteGoodsNum = inviteGoodsNum;
	}

	public void setInviteUsers(Users inviteUsers) {
		this.inviteUsers = inviteUsers;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public void setSponsorTime(Timestamp sponsorTime) {
		this.sponsorTime = sponsorTime;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChangeOrder [acceptGoods=" + acceptGoods + ", acceptGoodsNum="
				+ acceptGoodsNum + ", acceptUsers=" + acceptUsers
				+ ", createTime=" + createTime + ", id=" + id
				+ ", inviteGoods=" + inviteGoods + ", inviteGoodsNum="
				+ inviteGoodsNum + ", inviteUsers=" + inviteUsers
				+ ", isValid=" + isValid + ", sponsorTime=" + sponsorTime
				+ ", status=" + status + ", changeScore=" + changeScore
				+ ", changeType=" + changeType + ", inviteSendGoodsTime="
				+ inviteSendGoodsTime + ", inviteRed=" + inviteRed
				+ ", acceptRed=" + acceptRed + ", acceptSendGoodsTime="
				+ acceptSendGoodsTime + ", acceptConfirmTime="
				+ acceptConfirmTime + ", inviteConfirmTime="
				+ inviteConfirmTime + ", inviteStatus=" + inviteStatus
				+ ", acceptStatus=" + acceptStatus + "]";
	}


	
}
