package com.example.swagger;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
class SwaggerApplicationTests {

    @Test
    void contextLoads() {
        long l = System.currentTimeMillis();
        l = (l / 1000) /60;
        System.out.println("l = " + l);
        System.out.println(System.currentTimeMillis());

    }
    @Test
    public void demo(){
        ///获得系统的时间，单位为毫秒,转换为妙
        long totalMilliSeconds = System.currentTimeMillis();

        DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);//格式化输出
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区 这句加上，很关键。
        dateFormatterChina.setTimeZone(timeZoneChina);//设置系统时区
        long totalSeconds = totalMilliSeconds / 1000;

        //求出现在的秒
        long currentSecond = totalSeconds % 60;

        //求出现在的分
        long totalMinutes = totalSeconds / 60;
        long currentMinute = totalMinutes % 60;

        //求出现在的小时
        long totalHour = totalMinutes / 60;
        long currentHour = totalHour % 24;

        //显示时间
        System.out.println("总毫秒为： " + totalMilliSeconds);
        System.out.println(currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");


        Date nowTime = new Date(System.currentTimeMillis()+3600*1000);
        System.out.println(System.currentTimeMillis()+3600*1000);
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);

        System.out.println(retStrFormatNowDate);
    }



}
