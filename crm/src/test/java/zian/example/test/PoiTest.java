package zian.example.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zian.example.pojo.Activity;
import zian.example.service.ActivityService;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PoiTest
 * @Description
 * @Author ljzhang
 * @Date 2023/3/26 14:24
 * @Version 1.0
 */
public class PoiTest {
    @Test
    public void test1() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        ActivityService activityService = (ActivityService) applicationContext.getBean("activityService");
        List<Activity> activityList = activityService.queryAllActivity();
        //创建一个excel文件
        HSSFWorkbook work = new HSSFWorkbook();
        //创建一个工作表
        HSSFSheet sheet = work.createSheet("活动列表");
        for(int i = 0 ; i < 8; i++)
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
        for(int i = 0; i < activityList.size(); i++){
            HSSFRow r = sheet.createRow(i + 1);
            r.createCell(0).setCellValue(activityList.get(i).getName());
            r.createCell(1).setCellValue(activityList.get(i).getOwner());
            r.createCell(2).setCellValue(activityList.get(i).getStartDate());
            r.createCell(3).setCellValue(activityList.get(i).getEndDate());
            r.createCell(4).setCellValue(activityList.get(i).getCost());
            r.createCell(5).setCellValue(activityList.get(i).getDescription());
            String createDate = activityList.get(i).getCreateTime();
            r.createCell(6).setCellValue(createDate);
            r.createCell(7).setCellValue(activityList.get(i).getCreateBy());
        }

        File file = new File("C:\\Users\\Administrator\\Desktop\\activities.xls");
        OutputStream os = new FileOutputStream(file);
        work.write(os);
        os.close();
    }
}
