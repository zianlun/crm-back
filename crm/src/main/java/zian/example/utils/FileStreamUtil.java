package zian.example.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName FileSendUtil
 * @Description
 * @Author ljzhang
 * @Date 2023/3/16 10:55
 * @Version 1.0
 */
public class FileStreamUtil {
    public static byte[] getStream(HttpSession session, String fileName) throws IOException {
        //1、获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //2、获取下载文件路径
        String path = "static/img" + File.separator + fileName;
        String realPath = servletContext.getRealPath(path);
        //3、创建输入流
        InputStream is = new FileInputStream(realPath);
        //4、创建字节数组 is.available()获取输入流对应文件的字节数
        byte[] bytes = new byte[is.available()];
        //4、将字节流读入到字节数组中
        is.read(bytes);
        is.close();
        return bytes;
    }
}
