package zian.example.constants;

/**
 * @EnumName StatusCode
 * @Description
 * @Author ljzhang
 * @Date 2023/3/20 10:01
 * @Version 1.0
 */
public enum StatusCode {

    FAILE("失败",0),

    SUCCESS("成功",1),

    HTTPSUCCESS("成功",200),

    HTTPFORBIDDEN("无权限",403),

    HTTPUNAUTHORIZED("未经授权", 401);

    private final String name;

    private final Integer id;

    private StatusCode(String name, Integer id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
