package zian.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtils
 * @Description 日期格式化处理工具类
 * @Author ljzhang
 * @Date 2023/3/20 9:47
 * @Version 1.0
 */
public class DateUtils {

    /* **
       * @MethodName formateDateTime
       * @Description   格式化日期 yyyy-MM-dd HH:mm:ss
       * @Author ljzhang
       * @Date
       */
    public static String formateDateTime(Date date){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /* **
       * @MethodName
       * @Description 格式化日期 yyyy-MM-dd
       * @Author ljzhang
       * @Date
       */
    public static String formateDate(Date date){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /* **
       * @MethodName
       * @Description  格式化日期类型：HH:mm:ss
       * @Author ljzhang
       * @Date
       */
    public static String formateTime(Date date){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
