package utilities;

import java.util.ResourceBundle;

    public class ReadPropertiesFile {

        private static ResourceBundle rb = ResourceBundle.getBundle("config");

        public static String getUrl(String args) {
            String url = rb.getString("url");
            System.out.println(url);
            return url;
        }
    }
