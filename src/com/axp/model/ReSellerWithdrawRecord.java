package com.axp.model;


/**
 * 商家体现记录表；
 */
public class ReSellerWithdrawRecord extends ReBaseDomain {

    private Double money;//提现金额；
    private AdminUser adminUser;//提现的后台用户；
    private Seller seller;//提现的商家；
    private Integer style;//提现的类型；
    private String account;//支付的账号；
    private String realName;//真实姓名；
    private String bank;//开户行；
    private Integer status = 1;//提现状态；
    private String checkMessage;//审核信息；

    //提现类型；
    public static final Integer zhi_fu_bao = 1;//支付宝支付；
    public static final Integer wei_xin = 2;//微信支付；
    public static final Integer yin_hang_ka = 3;//银行卡支付；

    //提现状态；
    public static final Integer shen_qing = 1;//申请状态（等待审核）；
    public static final Integer shen_he_tong_guo = 2;//审核通过状态；
    public static final Integer shen_he_bu_tong_guo = 3;//审核不通过状态；
    public static final Integer ti_xian_wan_cheng = 4;//提现完成状态；
    public static final Integer yi_zhi_fu=4; //已支付;
    public static final Integer zhi_fu_cheng_gong=5;// 支付成功
    public static final Integer zhi_fu_shi_bai=6; //支付失败
    /**
     * 获取状态字符串；
     *
     * @return
     */
    public String getStatusString() {
        if (status == shen_qing) {
            return "申请状态";
        } else if (status == shen_he_tong_guo) {
            return "审核通过";
        } else if (status == shen_he_bu_tong_guo) {
            return "审核不通过";
        } else if (status == ti_xian_wan_cheng) {
            return "提现完成";
        }
        return "位置状态";
    }

    /**
     * 获取支付方式；
     *
     * @return
     */
    public String getStyleString() {
        if (style == zhi_fu_bao) {
            return "支付宝";
        } else if (style == wei_xin) {
            return "微信";
        } else if (style == yin_hang_ka) {
            return "银行卡";
        }
        return "未知支付方式";
    }

    /**
     * 获取账号；
     *
     * @return
     */
    public String getAccountString() {
        if (account.length() > 6) {
            StringBuilder sb = new StringBuilder(20);
            sb.append(account.substring(0, 3));
            for (int i = 0; i < account.length() - 6; i++) {
                sb.append("*");
            }
            sb.append(account.substring(account.length() - 3, account.length()));
            return sb.toString();
        }
        return account;
    }

    /**
     * 获取账号字符串；
     * 1，如果是支付宝和微信，则原样输出；
     * 2，如果是银行卡，则输出银行卡和开户行；
     *
     * @return
     */
    public String getAccountString2() {
        return style == yin_hang_ka ? account + "(" + bank + ")" : account;
    }

    //=======================getter and setter==========================

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCheckMessage() {
        return checkMessage;
    }

    public void setCheckMessage(String checkMessage) {
        this.checkMessage = checkMessage;
    }
}
