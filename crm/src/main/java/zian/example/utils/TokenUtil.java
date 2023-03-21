package zian.example.utils;

import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName TokenUtil
 * @Description
 * @Author ljzhang
 * @Date 2023/3/20 13:03
 * @Version 1.0
 */
public class TokenUtil {
    // jti：jwt的唯一身份标识
	// jti(JWT ID)：jwt的唯一身份标识，根据业务需要，可以设置为一个不重复的值，主要用来作为一次性token，从而回避重放攻击
    public static final String JWT_ID = UUID.randomUUID().toString();
    /*
     access_token过期时间 15分钟
     */
    private static final long ACCESS = 15 * 60 * 1000;

    /*
     * refresh_token 过期时间 一个星期
     * */
    private static final long REFRESH = 7 * 24 * 60 * 60 * 1000;
    /*
    私钥 --- 一串uuid

     */
    private static final String TOKEN_SECRET = "0A6B7FAF-3DBE-A719-71E0-04EED4A421E4";
    private static final String JWT_SECRET = "ljzhang";
    private static final String ISSUER = "ljzhang";


    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    private static JwtBuilder Sign(String username, String password, String audience ){
        //头部信息：token类型和加密算法，一般是固定的
        Map<String, Object> header = new HashMap<String,Object>();
        header.put("typ","JWT");
        //签名算法 默认是 HMAC SHA256（写成 HS256）
        header.put("alg","HS256");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String jwtId = JWT_ID;
        //设置payload的私有声明  邮箱账户和密码
        Map<String, Object> claims = new HashMap<>();
        claims.put("username",username);
        claims.put("password",password);
        //这里可以选择用字符串还是固定的私钥
        //不能外泄  --- 生成签名所用的密钥，泄露给客户端会被伪造
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .setId(jwtId)
                .setIssuer(ISSUER)
                .signWith(signatureAlgorithm, key)
                .setAudience(audience)
                .setClaims(claims)
                .setSubject(username);
        return builder;
    }

    public static String refreshSign(String username, String password, String audience ){
        JwtBuilder builder = Sign(username, password, audience);
        //生成JWT时间
        long nowDateTime = System.currentTimeMillis();
        Date issuedAt = new Date(nowDateTime);
        builder.setExpiration(new Date(nowDateTime + REFRESH))
                .setIssuedAt(issuedAt);
        return builder.compact();
    }

    public static String accessSign(String username, String password, String audience){
        JwtBuilder builder = Sign(username,password, audience);
        //生成JWT时间
        long nowDateTime = System.currentTimeMillis();
        Date issuedAt = new Date(nowDateTime);
        builder.setExpiration(new Date(nowDateTime + ACCESS))
                .setIssuedAt(issuedAt);
        return builder.compact();
    }

    //解密
    public static Claims pareseJWT(String jwt){
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
