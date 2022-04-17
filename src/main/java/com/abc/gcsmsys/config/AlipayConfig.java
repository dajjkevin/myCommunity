package com.abc.gcsmsys.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author surface
 * @Date 2022-03-31 20:48
 * @Description
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000118640821";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCM8qxiRIct4RPStUWQuWWMsCOd9Y7d4RgsTCWZZH43QvJXnb/TWcx1a7jnXl/WLnpAWBwK0yPzQu9FBgKIusYISVobjTPf1N4JhnbEiT+q8FOJ+hq5+TfCoEeLysZ8/Bmoe3Nng7VVxrpWZpS9MupAUC9q/dIrXUULbo9e50qa6oYqxKWIPfLykD7F09ZOTt4RAX1MlwICbxzpQwfu37ZEVOT8rn8dAE4iaJWsc4wzy0JywKak05NT+neOnJ7LkW+27h1SgN3B+AgCbfaRxUHyw6nFFdcvb/uBuRb8psBnrbzmQx2FQUeTjS86SwKcCJ/wpABFSasvZg3PAK6+n7WZAgMBAAECggEAPRFrxQvh8eviNdoacPES4ZjzZZ4eL142pgK8C7Dxyqmz7GhJ053Fq15n9775EQiEKVrDk6EVo3Ftz98hu/IImVCGvQc54ZIOPdxHmzHIJx0HZPG7ILatBSOCvDu4MjDcQRZz0vbK24ZF9+dsLGlh+0mCsf2VUPqRTrmxXrIhreql1YXs8J9zHGFScClM03ty+psuuWWvB1wiv4y2ESnT1E96cRouTR5tGPaA2vsbMwmj1VIn2/UoOSn8qss8qHUFOmU+ExYhj4WMlObFgQM84N59DChchttCOTVowEPgv5nDn87sbgvMXYDWnb/Ih+jyrYCDKSZcJKHQ1qq2uzO1AQKBgQDis1ydaMPTrnU2BxclFoVaDZUZONM24E+ygLL5n1UDtOo4Y9grnORSLQROFztDPsmHmUe323jdUuMUWz2JnCn9/sNWmdXbLIMjSrHc6EyePXa8yzCyqZ7/TsAGfUXG0IneDSF92UWVUoJsK7bl3W8V6fYOwfnQt48AKrwQ/0qQeQKBgQCfKhcTaiZ8dBwy5eKvNZpXbi0wJpX7JC+SHcYJQ1gzZuXCJfQzUh9xvFy3lIj28LCQHZX9F+hhr9J7dG3rITv91AVyd4l0+lBeuQVUtSPNkSdCS23b8dEn3eeC5C+2lOP+EHSg/QXjqo9Dohbhz9VaVEHynatHV2k6qV2tGhJGIQKBgQDRIpxfDqYRY1D4LaRZb6RHxl307apUZqy/ROFXTXjVOZIVwW5MFanAcY0WKXJff83VDT/PSmX+75dTUTS8/Vbz3ZIBE1vpnO/BoAfjfl4oYRVn558eQ0LNzq1xjyHO3tVBByYNfmIl9f8hoVZSSmxPcA2x4Zr/xEbk5YenuovkKQKBgEn0MaKouNcs0AlqPUixvFQRclekJh3580yOHv0g2HORxQHPxsatysR7BA2cq4Mp1LAxvwZJPIVfP4pzT7tnxssq35kPJ8Q9pmhMychRHiCe6EHqcFI46rj+Z1qvEehhCAp9tOM0ja76CfUf9EfZ3MVKzxWe4v2UBOXn2gnbSlBhAoGAYark2g13DuKyi5kBCFdF9yJ+QhNEx4+usveobsR0r2OGA03k3SuWasCFfB9a+HyClRrhRnX13iBHt2w823MyGGuUnCYc37e5Bq1rzzVqt69RB7eytrIpZ7iyAFrRAWOQnv8ckFQ52hnnh77FB7dfqPGlPAMKDLTFB+lg9VfDYII=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjnaElBtIb4r7N6PS7xOtkMjFCLcSAV966THFM3gmpN9++lbjBor85dEbSW705oJqdFS4Mu8OTpFMEyKTH0g2Nk7lsCrIgovIRCtcSWN005ipTN6ii6IX3ThcsxGbeNTyh+7jovXwTljY0UNElc/6ID+7FPYvmQx8kdBc5xT4QpVgJ+y75LnCa7Lilnn6ATWGezYd6ofmyK9OKUNPuUsjs38CfPu0x6kzAJwCiyVd+lsS5OCONlv4VN4YPOjvCZ6V39+32WIYSBcbsvbwOMF75Wy+MeAXCRGQ45TT4J3m/9VvtrP2BU0n9uKN7qK4DpxgNLvyjFmnqj6DRiXS6hRCKQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://oxl.vaiwan.com/user/returnNotifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/user/returnUserOrders";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\AlipayLogInfo\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
