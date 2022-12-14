package cn.yunhe.service;

import cn.yunhe.pojo.DeptP;
import cn.yunhe.pojo.PageBean;

import java.util.List;

public interface DeptPService {
    public PageBean listDeptOfPage(PageBean pageBean);

    /**
     * 查询部门列表
     * @return
     */
    public List<DeptP> listDept();

    /**
     * 添加部门信息
     * @param deptP
     */
    public void createDept(DeptP deptP);

    /**
     * 根据部门编号查询详情信息
     * @param deptId
     * @return
     */
    public DeptP findByDeptId(String deptId);

    /**
     * 修改
     * @param deptP
     */
    public void updateDept(DeptP deptP);

}
