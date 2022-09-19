package cn.yunhe.pojo;

import java.io.Serializable;


public class CurrentUser implements Serializable {
    private UserP userP;
    private UserinfoP userinfoP;
    private DeptP deptP;

    public UserP getUserP() {
        return userP;
    }

    public void setUserP(UserP userP) {
        this.userP = userP;
    }

    public UserinfoP getUserinfoP() {
        return userinfoP;
    }

    public void setUserinfoP(UserinfoP userinfoP) {
        this.userinfoP = userinfoP;
    }

    public DeptP getDeptP() {
        return deptP;
    }

    public void setDeptP(DeptP deptP) {
        this.deptP = deptP;
    }
}
