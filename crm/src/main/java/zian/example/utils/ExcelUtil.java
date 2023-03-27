package zian.example.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import zian.example.pojo.Activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @ClassName ExcelUtil
 * @Description
 * @Author ljzhang
 * @Date 2023/3/26 15:42
 * @Version 1.0
 */
public class ExcelUtil {

    public static HSSFWorkbook getByteActivityExcel(List<Activity> activities) {
        //创建一个excel文件
        HSSFWorkbook work = new HSSFWorkbook();
        //创建一个工作表
        HSSFSheet sheet = work.createSheet("活动列表");
        for (int i = 0; i < 8; i++)
            sheet.autoSizeColumn(i);
        //创建第一行名称
        HSSFRow row = sheet.createRow(0);
        //创建第一行的各列名称
        row.createCell(0).setCellValue("活动名称");
        row.createCell(1).setCellValue("负责人");
        row.createCell(2).setCellValue("开始时间");
        row.createCell(3).setCellValue("结束时间");
        row.createCell(4).setCellValue("活动成本");
        row.createCell(5).setCellValue("活动描述");
        row.createCell(6).setCellValue("创建时间");
        row.createCell(7).setCellValue("创建人员");
        for (int i = 0; i < activities.size(); i++) {
            HSSFRow r = sheet.createRow(i + 1);
            r.createCell(0).setCellValue(activities.get(i).getName());
            r.createCell(1).setCellValue(activities.get(i).getOwner());
            r.createCell(2).setCellValue(activities.get(i).getStartDate());
            r.createCell(3).setCellValue(activities.get(i).getEndDate());
            r.createCell(4).setCellValue(activities.get(i).getCost());
            r.createCell(5).setCellValue(activities.get(i).getDescription());
            String createDate = activities.get(i).getCreateTime();
            r.createCell(6).setCellValue(createDate);
            r.createCell(7).setCellValue(activities.get(i).getCreateBy());
        }
        return work;
    }
}
