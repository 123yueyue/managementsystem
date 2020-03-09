package com.aynu.data.common.bean;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;


/**
 * @Auther: zhangyue
 * @Date: 2020/3/8
 * @Description:
 */
@Component
public class ConstantMsgUtil {

        private static Properties pros = new Properties();

        public ConstantMsgUtil() {
            this.init();
        }

        private void init() {
            InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("config/constantMsg.properties");

            try {
                if (inStream != null) {
                    pros.load(inStream);
                    inStream.close();
                } else {
                    System.out.println("config/constantMsg.properties没有找到,无法启动项目,请重新配置!");
                    System.exit(0);
                }
            } catch (Exception var11) {
                System.exit(0);
                var11.printStackTrace();
            } finally {
                if (inStream != null) {
                    try {
                        inStream.close();
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }

            }

        }

        public static String getProperty(String key) {
            return pros.getProperty(key);
        }

        public static Properties getPros() {
            return pros;
        }

        public void update() {
            this.init();
        }

        public static String getInsertSuccMsg() {
            return getProperty("insertSuccMsg");
        }

        public static String getInsertFailMsg() {
            return getProperty("insertFailMsg");
        }

        public static String getUpdateSuccMsg() {
            return getProperty("updateSuccMsg");
        }

        public static String getUpdateFailMsg() {
            return getProperty("updateFailMsg");
        }

        public static String getSaveSuccMsg() {
            return getProperty("saveSuccMsg");
        }

        public static String getSaveFailMsg() {
            return getProperty("saveFailMsg");
        }

        public static String getDeleteSuccMsg() {
            return getProperty("deleteSuccMsg");
        }

        public static String getDeleteFailMsg() {
            return getProperty("deleteFailMsg");
        }

        public static String getQuerySuccMsg() {
            return getProperty("querySuccMsg");
        }

        public static String getQueryFailMsg() {
            return getProperty("queryFailMsg");
        }

        public static int getSuccStatus() {
            return Integer.valueOf(getProperty("succStatus"));
        }

        public static String getSuccMsg() {
            return getProperty("succMsg");
        }

        public static int getFailStatus() {
            return Integer.valueOf(getProperty("failStatus"));
        }

        public static String getFailMsg() {
            return getProperty("failMsg");
        }

        public static int getSessionTimeOutStatus() {
            return Integer.valueOf(getProperty("sessionTimeOutStatus"));
        }

        public static String getSessionTimeOutMsg() {
            return getProperty("sessionTimeOutMsg");
        }

        public static int getInternetStopStatus() {
            return Integer.valueOf(getProperty("internetStopStatus"));
        }

        public static String getInternetStopMsg() {
            return getProperty("internetStopMsg");
        }

        public static int getAccountControlStatus() {
            return Integer.valueOf(getProperty("accountControlStatus"));
        }

        public static String getAccountControlMsg() {
            return getProperty("accountControlMsg");
        }


}
