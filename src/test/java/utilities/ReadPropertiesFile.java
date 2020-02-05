package utilities;

import java.util.ResourceBundle;

    public class ReadPropertiesFile {
        public static String url = null;
        private static ResourceBundle rb = ResourceBundle.getBundle("config");

        public static String getUrl(String page) {
            switch(page) {
                case "homePage":
                    url = rb.getString("homePageUrl");
                    break;
                case "booking":
                    url = rb.getString("bookingUrl");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + page);
            }
            System.out.println(url);
            return url;
        }
    }
