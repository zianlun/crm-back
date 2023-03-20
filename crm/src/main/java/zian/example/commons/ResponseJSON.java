package zian.example.commons;

import org.springframework.http.HttpStatus;

/**
 * @ClassName ResponseJSON
 * @Description
 * @Author ljzhang
 * @Date 2023/3/17 11:22
 * @Version 1.0
 */
public class ResponseJSON {

    private String code;

    private String message;

    private Object data;

    public ResponseJSON() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseJSON{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
