package com.chong.bys.bystest.okhttp.test;

import okhttp3.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

//http://square.github.io/okhttp/3.x/okhttp/    okhttp官方api
//http://square.github.io/okhttp/#examples      官方例子

public class TestOkHttp {

    // 日志
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * get请求
     */
    @Test
    public void testGetOkHttp() {

        // 创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        // 请求地址
        String url = "http://localhost:8090/login?id=304";

        // 构造请求
        Request request = new Request.Builder().url(url).build();

        // 发起请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            logger.info("请求是否成功------->>>" + response.isSuccessful());
            logger.info("请求的状态码-------》》》" + response.code());
            String string = response.body().string();
            logger.info("返回值---》》》" + string);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * post请求(form表单类型以及设置请求编码为GBK)
     *
     * @throws Exception
     */
    @Test
    public void testPostFrom() throws Exception {
        // 创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        // 请求地址
        String url = "http://localhost:8090/smartYJ/test/testpost";

        // 设置编码请求格式，在request中设置不管用，服务器端是UTF-8的在此处设置GBK返回值就乱码了
        Charset charset = Charset.forName("GBK");// 获得charset对象

        // 构造表单类型的请求，在构造中传入charset编码
        FormBody formBody = new FormBody.Builder(charset).add("name", "dsd").add("param1", "你好").add("param2", "333")
                .build();

        Request request = new Request.Builder().url(url).post(formBody).build();

        // 得到响应对象
        Response response = okHttpClient.newCall(request).execute();

        logger.info("响应状态码：-------》》》" + response.code());

        logger.info("" + response.message().toString());

        // 响应体，响应体可以用fastjson或者jackon等json工具直接转成对象
        String string = response.body().string();

        logger.info("返回值--------》》》》" + string);

    }

    /**
     * application/JSON类型格式的请求
     *
     * @throws IOException
     */
    public void testPostJson() throws IOException {
        // 创建okhttp对象
        OkHttpClient okHttpClient = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // 请求地址
        String url = "";

        // json格式的字符串，可以用json工具将对象进行转换：如fastjson和jackon都可以
        String content = "{}";

        // 构建请求体
        RequestBody body = RequestBody.create(JSON, content);

        // 请求构建
        Request request = new Request.Builder().url(url).post(body).build();

        // 发起请求，获取响应体
        Response response = okHttpClient.newCall(request).execute();

        logger.info("响应状态码：---------》》》》" + response.code());

        logger.info("响应内容：---------》》》》" + response.body().string());

    }

    /**
     * post请求，获取小程序二维码
     */
    @Test
    public void testGetOkHttpsendmsg() {

        String sceneStr = "asdfasdf";
        String accessToken = "5_Yw4D0iVh9_RsF6YhUQPaKHP1KXUybuWXs66KYzNLOjE-dAu3MyNSV0p2Ar-\r\n" +
                "\r\n" +
                "tuFzFgAoUNdNwk4sb13ORVi84Baeu_aTcE0OZiVKu3T0U9xERLLTgPBHGlXLdwrycZjGvwPz92Liwv-GyDmWDTMSfAHABUA";

        // 创建okhttp对象
        OkHttpClient okHttpClient = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        //参数封装
        Map<String, Object> param = new HashMap<>();
        param.put("scene", sceneStr);
        param.put("page", "pages/index/index");
        param.put("width", 430);
        param.put("auto_color", false);
        Map<String, Object> line_color = new HashMap<>();
        line_color.put("r", 0);
        line_color.put("g", 0);
        line_color.put("b", 0);
        param.put("line_color", line_color);

        // 请求地址
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;

        // json格式的字符串，可以用json工具将对象进行转换：如fastjson和jackon都可以
        String content = com.alibaba.fastjson.JSON.toJSONString(param);

        // 构建请求体
        RequestBody body = RequestBody.create(JSON, content);

        // 请求构建
        Request request = new Request.Builder().url(url).post(body).build();

        InputStream inputStream = null;

        OutputStream outputStream = null;


        // 发起请求获取响应
        try {
            // 发起请求，获取响应体
            Response response = okHttpClient.newCall(request).execute();

            logger.info("响应状态码：---------》》》》" + response.code());
            logger.info("响应是否成功：---------》》》》" + response.isSuccessful());

            inputStream = response.body().byteStream();

            File file = new File("E:/xiaocx/image/test1.png");
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();

            String string = response.body().string();
            logger.info("返回值---》》》" + string);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
