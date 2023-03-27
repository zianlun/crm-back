package zian.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName LocalDateUtil
 * @Description
 * @Author ljzhang
 * @Date 2023/3/23 18:28
 * @Version 1.0
 */
public class LocalDateUtil {

    /* **
       * @MethodName getNowDateTime
       * @Description  获取时间字符串 yyyy-MM-dd
       * @Author ljzhang
       * @Date
       */
    public static String formatterDate(LocalDateTime date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        return dateTimeFormatter.format(date);
    }

    /* **
       * @MethodName
       * @Description
       * @Author ljzhang
       * @Date
       */
}
