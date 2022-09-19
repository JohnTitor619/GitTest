package cn.yunhe.service.impl;

import cn.yunhe.mapper.DeptPMapper;
import cn.yunhe.mapper.ModulePMapper;
import cn.yunhe.mapper.UserPMapper;
import cn.yunhe.mapper.UserinfoPMapper;
import cn.yunhe.pojo.*;
import cn.yunhe.service.UserPService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPServiceImpl implements UserPService {
    @Autowired
    private UserPMapper userPMapper;
    @Autowired
    private DeptPMapper deptPMapper;
    @Autowired
    private UserinfoPMapper userinfoPMapper;
    @Autowired
    private ModulePMapper modulePMapper;

    @Override
    public UserP loginP(String username) {
        //创建UserPExample对象
        UserPExample userPExample = new UserPExample();
        UserPExample.Criteria criteria = userPExample.createCriteria();
        //通过criteria构建查询条件
        criteria.andUserNameEqualTo(username);
        //用户状态为启用:1  禁用:0
        criteria.andStateEqualTo(1);
        // where username=xxx and state=1
        List<UserP> list = userPMapper.selectByExample(userPExample);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public CurrentUser findUserInfoDeptByUser(UserP user) {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserP(user);
        //根据部门编号查询部门信息
        DeptP deptP = deptPMapper.selectByPrimaryKey(user.getDeptId());
        currentUser.setDeptP(deptP);
        //根据用户编号查询用户扩展信息
        UserinfoP userinfoP = userinfoPMapper.selectByPrimaryKey(user.getUserId());
        currentUser.setUserinfoP(userinfoP);
        return currentUser;
    }

    @Override
    public List<ModuleP> getPermissionsByUserId(String userid) {
        return modulePMapper.getPermissionsByUserId(userid);
    }

    @Override
    public PageBean listUserOfPage(PageBean pageBean) {
        //设置当前页和分页单位
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        //查询用户数据
        UserPExample  userPExample = new UserPExample();
        userPExample.setOrderByClause("UPDATE_TIME desc");
        List<UserP> list = userPMapper.selectByExample(userPExample);
        //创建PageInfo对象
        PageInfo pageInfo = new PageInfo(list);
        PageBean pb = new PageBean();
        pb.setData(pageInfo.getList());
        pb.setPageSize(pageInfo.getPageSize());
        pb.setCurPage(pageInfo.getPageNum());
        pb.setTotalPages(pageInfo.getPages());
        pb.setTotalRows(pageInfo.getTotal());
        return pb;
    }

    @Override
    public void createUser(UserP userP, UserinfoP userinfoP) throws Exception {

    }

    @Override
    public List<UserP> listUser() {
        return null;
    }
}
