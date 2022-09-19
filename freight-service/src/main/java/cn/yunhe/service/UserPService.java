package cn.yunhe.service;

import cn.yunhe.pojo.*;

import java.util.List;

public interface UserPService {
    public UserP loginP(String username);

    /**
     * 扩展信息和部门信息
     * @param user
     * @return
     */
    public CurrentUser findUserInfoDeptByUser(UserP user);

    /**
     * 根据用编号查询权限信息
     * @param userid
     * @return
     */
    public List<ModuleP> getPermissionsByUserId(String userid);

    public PageBean listUserOfPage(PageBean pageBean);

    public void createUser(UserP userP, UserinfoP userinfoP) throws Exception;

    public List<UserP> listUser();
}
