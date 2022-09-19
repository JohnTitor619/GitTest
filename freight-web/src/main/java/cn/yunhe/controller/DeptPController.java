package cn.yunhe.controller;

import cn.yunhe.pojo.DeptP;
import cn.yunhe.pojo.DeptVo;
import cn.yunhe.pojo.PageBean;
import cn.yunhe.service.DeptPService;
import cn.yunhe.util.ExportExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptPController {

    @Autowired
    private DeptPService deptPService;

    @RequestMapping("/list")
    public String listDeptAndParent(PageBean pageBean,Model model){
        System.out.println("curPage:"+pageBean.getCurPage());
        PageBean pb = deptPService.listDeptOfPage(pageBean);
        model.addAttribute("pb",pb);
        return "sysadmin/dept/jDeptList";
    }


    @RequestMapping("tocreate")
    public String toCreate(Model model){
        List<DeptP> depts = deptPService.listDept();
        model.addAttribute("depts",depts);
        return "sysadmin/dept/jDeptCreate";
    }


    @RequestMapping("create")
    public String create(DeptP deptP){
        deptPService.createDept(deptP);
        return "redirect:/dept/list";
    }

    @RequestMapping("toupdate")
    public String toUpdate(String deptId,Model model){
        DeptP dept = deptPService.findByDeptId(deptId);
        model.addAttribute("dept",dept);
        List<DeptP> depts = deptPService.listDept();
        model.addAttribute("depts",depts);
        return "sysadmin/dept/jDeptUpdate";
    }


    @RequestMapping("update")
    public String update(DeptP deptP){
        deptPService.updateDept(deptP);
        return "redirect:/dept/list";
    }


    @RequestMapping("export")
    public void export(PageBean pageBean, HttpServletRequest request, HttpServletResponse response)throws Exception{
        PageBean pb = deptPService.listDeptOfPage(pageBean);
        List<DeptVo> data = (List<DeptVo>) pb.getData();
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String filePath = "/tpl/dept_export.xlsx";
        ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
        File excelTplFile = exportExcelUtil.getExcelTplFile(realPath, filePath);
        Workbook workbook = exportExcelUtil.getWorkbook(excelTplFile);
        Sheet sheet = exportExcelUtil.getSheet(workbook, "部门信息");
        for(DeptVo deptVo:data){
            Row row = exportExcelUtil.createRow(sheet);
            Cell cell0 = exportExcelUtil.createCell(row, 0);
            cell0.setCellValue(deptVo.getDeptNo());

            Cell cell1 = exportExcelUtil.createCell(row, 1);
            cell1.setCellValue(deptVo.getParentName());

            Cell cell2 = exportExcelUtil.createCell(row, 2);
            cell2.setCellValue(deptVo.getDeptName());

            Cell cell3 = exportExcelUtil.createCell(row, 3);
            cell3.setCellValue(deptVo.getState()==1?"启用":"禁用");

        }
        String fileName="dept_list.xlsx";
        response.setContentType("application/ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        ServletOutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
