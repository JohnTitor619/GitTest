package cn.yunhe.service.impl;

import cn.yunhe.mapper.UserinfoPMapper;
import cn.yunhe.pojo.UserP;
import cn.yunhe.pojo.UserinfoP;
import cn.yunhe.service.UserinfoPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;

@Service
public class UserinfoPServiceImpl implements UserinfoPService {
    @Autowired
    private UserinfoPMapper userinfoPMapper;

    @Override
    public List<UserinfoP> listUserInfo() throws Exception {
        return userinfoPMapper.selectByExample(null);
    }


}
