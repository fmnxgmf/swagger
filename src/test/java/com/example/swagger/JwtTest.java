package com.example.swagger;

import com.example.swagger.jwt.commons.entity.UserInfo;
import com.example.swagger.jwt.commons.utils.JwtUtils;
import com.example.swagger.jwt.commons.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/23 12:37
 * @version:
 * @modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    String jar_parent = new File(ResourceUtils.getURL("classpath:").getPath())
            .getParentFile()
            .getParentFile()
            .getParent();
    private  final String pubKeyPath = jar_parent+"\\rsa\\rsa.pub";

    private  final String priKeyPath = jar_parent+"\\rsa\\rsa.pri";

   // private static final String pubKeyPath = "D:\\rsa\\rsa.pub";


    private PublicKey publicKey;

    private PrivateKey privateKey;

    public JwtTest() throws FileNotFoundException {
    }

    @Test
    //生成公钥和私钥文件
    public void testRsa()throws Exception{
        RsaUtils.generateKey(pubKeyPath,priKeyPath,"234");
    }
    @Before
    public void testGetRsa() throws Exception {
        PrivateKey privateKey = RsaUtils.getPrivateKey(priKeyPath);
        System.out.println("privateKey = " + privateKey);
        PublicKey publicKey = RsaUtils.getPublicKey(pubKeyPath);
        System.out.println("publicKey = " + publicKey);
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @Test
    public void demo() throws FileNotFoundException {
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//        System.out.println(path);

//        String path = ResourceUtils.getURL("classpath:").getPath();
//        System.out.println("path = " + path);
        String jar_parent = new File(ResourceUtils.getURL("classpath:").getPath())
                .getParentFile()
                .getParentFile()
                .getParent();
        System.out.println("pubKeyPath = " + pubKeyPath);

    }
    @Test
    //生成token
    public void testToken() throws Exception {
        //生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "gmf"), privateKey, 5);
        System.out.println("token = " + token);
        //解析tonken
        UserInfo into = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println(into.getId());
        System.out.println(into.getUsername());
    }
}
