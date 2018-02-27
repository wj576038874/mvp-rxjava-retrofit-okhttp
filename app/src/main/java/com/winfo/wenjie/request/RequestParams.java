package com.winfo.wenjie.request;

/**
 * @ProjectName: MvpRxjavaRetrofitDemo
 * @PackageName: com.winfo.wenjie.request
 * @FileName: com.winfo.wenjie.request.RequestParams.java
 * @Author: wenjie
 * @Date: 2017-10-19 11:10
 * @Description:
 * @Version:
 */
public class RequestParams {
    private String Tel;
    private String TenancyName;
    private String UsernameOrEmailAddress;
    private String Password;

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTenancyName() {
        return TenancyName;
    }

    public void setTenancyName(String tenancyName) {
        TenancyName = tenancyName;
    }

    public String getUsernameOrEmailAddress() {
        return UsernameOrEmailAddress;
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        UsernameOrEmailAddress = usernameOrEmailAddress;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
