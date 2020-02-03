package com.example.swagger.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName KaptchaController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/2
 * @Version V1.0
 **/
@RestController
@Slf4j
public class KaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    //获取验证码
    @RequestMapping("/getCode")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        //生产验证码字符串并保存到session中
        String text = defaultKaptcha.createText();
        httpServletRequest.getSession().setAttribute("vrifyCode", text);
        //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage image = defaultKaptcha.createImage(text);
        try {
            ImageIO.write(image,"jpg",jpegOutputStream);
        } catch (IOException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream  = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    //验证验证码
    @RequestMapping("/checkCode")
    public boolean imgvrifyControllerDefaultKaptcha(String code,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String vrifyCode = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        if(! vrifyCode.equals(code)){
            log.info("错误的验证码");
            return false;
        }else {
            return true;
        }
    }
    //验证码
    @RequestMapping("/newCode")
    public void captcha(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //生成随机字符串
        String s = VerifyCodeUtils.generateVerifyCode(4);
        request.getSession().setAttribute("vrifyCode",s.toLowerCase());
        //生成图片
        int w = 125,h = 40;
        // 将验证码存储在session以便登录时校验

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), s);
    }
}
