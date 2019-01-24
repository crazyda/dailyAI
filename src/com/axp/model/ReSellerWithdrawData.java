package com.axp.model;

/**
 * 商家体现记录表；
 */
public class ReSellerWithdrawData extends ReBaseDomain {

    private Seller seller;//提现的商家；

    private String name;//真实姓名；

    private String zhiFuBao;//支付宝账号；
    private String weiXin;//微信账号；
    private String yinHang;//银行账号；
    private String kaiHuHang;//开户行；

    private String zhengMianTu;//身份证正面图；
    private String fanMianTu;//身份证反面图；

    private Integer checkStatus = 0;//提现资料的审核状态；
    private String checkInfo;//审核信息；
    //=========== 提现资料审核状态开始 ========
    public static final Integer unCheck = 0;//未审核；
    public static final Integer pass = 1;
    public static final Integer unPass = 2;
    //=========== 提现资料审核状态结束 ========

    //============= getter and setter =====================

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhiFuBao() {
        return zhiFuBao;
    }

    public void setZhiFuBao(String zhiFuBao) {
        this.zhiFuBao = zhiFuBao;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public String getYinHang() {
        return yinHang;
    }

    public void setYinHang(String yinHang) {
        this.yinHang = yinHang;
    }

    public String getKaiHuHang() {
        return kaiHuHang;
    }

    public void setKaiHuHang(String kaiHuHang) {
        this.kaiHuHang = kaiHuHang;
    }

    public String getZhengMianTu() {
        return zhengMianTu;
    }

    public void setZhengMianTu(String zhengMianTu) {
        this.zhengMianTu = zhengMianTu;
    }

    public String getFanMianTu() {
        return fanMianTu;
    }

    public void setFanMianTu(String fanMianTu) {
        this.fanMianTu = fanMianTu;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }
}
