package cn.yunhe.service.impl;

import cn.yunhe.mapper.DeptPMapper;
import cn.yunhe.pojo.DeptP;
import cn.yunhe.pojo.DeptVo;
import cn.yunhe.pojo.PageBean;
import cn.yunhe.service.DeptPService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptPServiceImpl implements DeptPService {
    @Autowired
    private DeptPMapper deptPMapper;
    @Override
    public PageBean listDeptOfPage(PageBean pageBean) {
        //设置当前页和分页单位
        PageHelper.startPage(pageBean.getCurPage(),pageBean.getPageSize());
        //调用DeptpMapper查询部门信息
        List<DeptVo> list = deptPMapper.listDeptAndParent();
        //创建Pageinfo对象
        PageInfo<DeptVo> pageinfo = new PageInfo(list);
        //创建PageBean对象封装数据
        PageBean pb = new PageBean();
        pb.setData(pageinfo.getList()); //设置分页数据
        pb.setCurPage(pageinfo.getPageNum());//当前页
        pb.setPageSize(pageinfo.getPageSize());//分页单位
        pb.setTotalRows(pageinfo.getTotal()); //设置总记录数
        pb.setTotalPages(pageinfo.getPages());//设置总页数
        return pb;
    }

    @Override
    public List<DeptP> listDept() {
        return deptPMapper.selectByExample(null);
    }

    @Override
    public void createDept(DeptP deptP) {
        //设置deptId
        deptP.setDeptId(UUID.randomUUID().toString());
        //设置deptNo
        deptP.setDeptNo(String.valueOf(System.currentTimeMillis()));
        deptP.setState(1);
        deptPMapper.insertSelective(deptP);
    }

    @Override
    public DeptP findByDeptId(String deptId) {
        return deptPMapper.selectByPrimaryKey(deptId);
    }

@Override
public void updateDept(DeptP deptP) {
        deptPMapper.updateByPrimaryKey(deptP);
        }
        }
