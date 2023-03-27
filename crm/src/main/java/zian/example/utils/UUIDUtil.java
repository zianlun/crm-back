package zian.example.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtils
 * @Description
 * @Author ljzhang
 * @Date 2023/3/23 13:34
 * @Version 1.0
 */
public class UUIDUtil {
    /* **
       * @MethodName getUUID
       * @Description  获取一个uuid
       * @Author ljzhang
       * @Date
       */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
