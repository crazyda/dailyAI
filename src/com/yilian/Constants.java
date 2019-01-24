package com.yilian;

/**
 * @author
 */
/**
 * @author
 */
public class Constants {
  //----商户信息：商户根据对接的实际情况对下面数据进行修改； 以下数据在测试通过后，部署到生产环境，需要替换为生产的数据----
  //商户编号，由易联产生，邮件发送给商户
  //public static final String MERCHANT_ID = "502053000185";//502053000121 //302020000058   //内部测试商户号，商户需要替换该参数
	 public static final String MERCHANT_ID ="502050002695";//正式商户号
//  public static final String MERCHANT_ID = "002020000008";     //互联网金融行业的商户号
  //商户接收订单通知接口地址（异步通知）；
  
	 public static final String MERCHANT_NOTIFY_URL = "http://jph.aixiaoping.cn:8080/jupinhuiAPI/invoke/order/yiLianNotify";
  //商户接收订单通知接口地址(同步通知),H5版本对接需要该参数；
  public static final String MERCHANT_RETURN_URL = "http://127.0.0.1:8080/ReturnH5.do";
  //商户RSA私钥，商户自己产生（可采用易联提供RSA工具产生）
 // public static final String MERCHANT_RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOAqNu0SFh5Ksz8Mp/vzm1kxiMYoSREXNXGajCHkKJIVwTaxtPaPYq3JiASZbCALrjp9UM0jLsqayDzF51paUt5lbBDRgqabAClUos3X4c7v2uUt98ILDi8ABQV8BrMZE5RKaLcvvr3mhu/JhabXBz2SBflSCSG3K8HQRTDjjp7nAgMBAAECgYBg01suQ6WyJ+oMzdaxiaQMfszpavVEoJXBIFRvPzIXB7aRfWkBJyYkkuxhsDN4FBOJyB9ivFO1x+298m3gJSutfXfSRA9Kq9XrEIQDjJB4PBx8yTVmVckgCJlsWnhuySHf7gapLkfLHQ+GgiUpYUPW0MJczsu7juuMUZdKHJ6rIQJBAPVLJAxXQYI2e8WMfTPR1jqeZXSQ5r4XI0d8wKFMDa68gq3Y3B2CKmWO16faxafJ8oUWJtJJwRQT6YItBVA3DWUCQQDp8vymxQkLCVpyQ+SfG0Ab9mw2G7p2Y3pAYwms7SIOILoADUbJl2UxpyROj9N9Lq2ndZ0rNIWw4iJXigwRuaxbAkEAkiN7TZLqp25YXUCvEyFwFapq3YC6yAO29A9CIJbUDAepf3OU6Eu1gJ4So6F2YtmxEFM7O8vPKWwXkYPLB5hU9QJAHLjWR+s81vwI/KpVMSt5TXWNh38T/2DrK2h9UZuzaKSf8U2v+SP7KoNos7R4tI+8hiisaReDqlm4+aJbJPn0rQJBAK0EQLyG8iks7Ppclq9UBgEx2iKSsg9y60aSt1YwI73wEdW18paBtoUMjQ5GAqCyVmEb01IY6Kn1si43zqHct4o=";
 //	public static final String MERCHANT_RSA_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJgzUFz1eK/C/TAC0nI3LIm987w26WLpNN1aqH0lQdVWAtk/58IlZaeq0N3T91JBMi3ACryBoPsvLI78ngQkkHvyhxKyQFOEdCEO/ZgjrFUZYxymEb26PGhzE8EaTqZ+9mzVycGJYZPB8z3aUC0JxNIOXyJON1u6Ds3g53kaLFX3AgMBAAECgYEAgCcEzaDq46NdKGXX6M/IMpq8dYgO73fJSXCiWe1bB1SKxX5nmDOA6rKLd5yYrKu0oo0G/T5w288Wx7axHm/jLw3DOHW04rudX/8FncEcyzC7s6aW6ZUuFDlCQ+8qX84mBGmYA5EqF2Q6/8FTy+AbUnAjV4vqNGWQ8h30kHJR7FECQQDJxKFJEZjDXKANEr9sPiHdrPCflTMwNgdjOPnDawvlDVUvHA8WzMQNDWdBt0OahfIJ3es6yMBqFpIuVLD2LhYtAkEAwRwBmhxKk4QQGNF3cEM/wuJht8/7HQiEFOZX4Xqa7GyOJa3Zcs45M04EHUVkOaXotsV8qtHDbIW2JkfuYct3MwJAAmE8WcVIXP2Jsb3H3jn5Ykj46Zjz6pyoh6YTZBeuIzx+Bbk6AFXX9iNzVVYZCQiNa0pfzOizRE0R2lQLZkvy3QJBAKzp4flX2eNLPoqqjXNllCNySCOqROWTaYm5U/mfqjeHYKUWjX5CcG+jLQX0y7DfAYkfPulKGN4EwLx4blj9MocCQQC7VsVolGzoZSwQEXzHvPUopGQFeGWT7EdV/OwBQBS1LB3bEPnsUZByFVLj23/IGm8vJNakH2utiO/7Fx/FBgDi";
  
  
  public static final String MERCHANT_RSA_PRIVATE_KEY="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANMozp4vrvgNp5Xl"
		    +"WGa+34Sp2O5GpuYIpynnoySaHPit9EyKWhkpdGUvQ95wjsElN4yJvBUD8LyuCxfG"
		    +"Q/ScHOmPoSfYqrEq2TKzTSpkx0f1CH2s8mKO2cDOQFIcnI+qgJVMNyaL/E4H4iRO"
		    +"O+XFziR8EV3FDDRtuX2+y6VxOoyBAgMBAAECgYEApQe7k/4IbW2ekJRSZtq+rlxg"
		    +"YrltL1OL8kBLTQv1oJWz3S40BH5Vrc8m5+5oY6PGqlvrVzFhMNWFbetSqRZpsOVU"
		    +"9AGRLcKANf/HY+FaWxfkqASsMQhE7Q8gwsA3RyM8WGXr7dIOvOO67wMIN+/pCmmR"
		    +"OidFWZbOCn8M5RqEjAECQQDqM172CgjYlSsISt50dYBeqghkBT4rqT868pFh+lI5"
		    +"CFKNMN6PPbKNarkGTAJcAd0aBAiBXp3aPjj+Qqj8rTSRAkEA5tBmRWxNI3li+O58"
		    +"b6N3KuRoAN+ZUclW/eSawkz04Qdd3UBHozJeDM9pNkSPdyXaLDHmcfZlPA65dgb5"
		    +"5jMQ8QJBAOff0KccrEFy/tYI+lKne18+TWxp3HHx8Y51VweAhSO+X602s0NyvHNT"
		    +"NLlNTBC4L6ZwU9NyUCsh69+hNBpnimECQDKfpq63UDvllcWPWQ+LRMnNitoWMKR1"
		    +"inTpPOA8zMDGQtoSDSRIGcSpgSP76ZNyY+WplCeOqSmA+UBfy1OoA6ECQQCvfTJG"
		    +"iZ4LoCgKzmJH2XM4baZ0dUwVSs0TsxITceL3Wu2nRFO1JCHdJcehLqwgig3ET1Oy"
		    +"wFh+fcRKuy5gnnRN";
  //---互联网金融测试商户RSA私钥（从证书文件导出）
//  public static final String MERCHANT_RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKpZJ9Rbd9LKg5jM4byOjLfGV2kFqctWDyAQNy+b0rFWOq8D+okjvLRGaRzUjuX28B9a03OmEwL7CW6WloCxr/g9t9WP5aGg1DKEb6biw/bsEDzG5681P39bv/ZlWTjfbg1KjDBaRqqjXK5l7XBAxxWFE7PaH6DuP+5kPR+IKiRbAgMBAAECgYAfDloAkRxrRZhwRwnwglyNNI/DCdFGzM29Hrew6kujIQFZ3vPSBL3mb9/B7c6PhlGIpdpe/ywAIxw5GSMfG0XlQ6umgPSsxF6TaRCXkBE1B1QYn5L4jVgHkszTRMCXkTybtaxEqEh6nhA6Krj4Y5ki1wpDpwHToTUYwz3RHuxdgQJBAN8hkxIhQ0ERALsrOWRZoishT9Ci5BxUtCYwKKw4Und1w3ywvxT28kDO2tp8aZ9/JVcHcRW04I+MmS0ZEPzGYNECQQDDcRpeVL6DLC/+fWhsUK6PixSmfH+roZURpJXlRPmQlxQwluoaQ2b/KUouujycnsphXIIpWHCZenfrJrS1yB1rAkBgU/lPOWb0fyempil3xi55mj7/3mLGTFcdqWrVttb7Va7YdOF5Zob9LZBUBKQAxH5VTRQn/9d2gYdbbdfkmKwRAkEAljVaP7/AAE64wE4gMIc98kLBZ0duVDnGuR2WuvPtHuyObt2+JNtC0L8qLYmjRfhgsL2JqD85oyvV+Jvx7XhU6wJBALIT5T+T3HdFRXlRAH12X74VXOnfHZ79sU/NNDBBtRN2AKfNo/I9g9xV7hZiVGTWEuDK8NImWYBU33PejWCZdS8="; //互联网金融



  //----易联信息： 以下信息区分为测试环境和生产环境，商户根据自己对接情况进行数据选择----
  //易联服务器地址_测试环境
  //public static final String PAYECO_URL = "https://testmobile.payeco.com";
  //易联服务器地址_生产环境
  public static final String PAYECO_URL = "https://mobile.payeco.com";

  //订单RSA公钥（易联提供）_测试环境
 //public static final String PAYECO_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRxin1FRmBtwYfwK6XKVVXP0FIcF4HZptHgHu+UuON3Jh6WPXc9fNLdsw5Hcmz3F5mYWYq1/WSRxislOl0U59cEPaef86PqBUW9SWxwdmYKB1MlAn5O9M1vgczBl/YqHvuRzfkIaPqSRew11bJWTjnpkcD0H+22kCGqxtYKmv7kwIDAQAB";
 // public static final String PAYECO_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYM1Bc9Xivwv0wAtJyNyyJvfO8Nuli6TTdWqh9JUHVVgLZP+fCJWWnqtDd0/dSQTItwAq8gaD7LyyO/J4EJJB78ocSskBThHQhDv2YI6xVGWMcphG9ujxocxPBGk6mfvZs1cnBiWGTwfM92lAtCcTSDl8iTjdbug7N4Od5GixV9wIDAQAB";
  //订单RSA公钥（易联提供）_生产环境
  public static final String PAYECO_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoymAVb04bvtIrJxczCT/DYYltVlRjBXEBFDYQpjCgSorM/4vnvVXGRb7cIaWpI5SYR6YKrWjvKTJTzD5merQM8hlbKDucxm0DwEj4JbAJvkmDRTUs/MZuYjBrw8wP7Lnr6D6uThqybENRsaJO4G8tv0WMQZ9WLUOknNv0xOzqFQIDAQAB";

}
