package zian.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zian.example.utils.FileStreamUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName IndexController
 * @Description
 * @Author ljzhang
 * @Date 2023/3/16 10:45
 * @Version 1.0
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @GetMapping("/getImage")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(HttpSession session, HttpServletResponse response) throws IOException {
        byte[] bytes = FileStreamUtil.getStream(session, "work.png");
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        return responseEntity;
    }
}
