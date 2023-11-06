package dat.ErrorHandling;

public class MyApplication {
    public static void main(String[] args) {
        // Log HTTP-related information with an "INFO" level message
        MyLogger.logger.info("HTTP Status Code: 200, Method: GET, Path: /example, Client IP: 192.168.1.1");

        try {
            // Your code that may raise exceptions
        } catch (Exception e) {
            // Log exceptions with a "SEVERE" level message, including the exception message
            MyLogger.logger.severe("Exception occurred: " + e.getMessage());
        }
    }
}