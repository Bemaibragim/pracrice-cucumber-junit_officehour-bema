package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    /*
Creating a private constructor, so we are closing access to the object of this class
from outside  any classes
*/
    private Driver(){}

    /*
    Making our 'driver' instance private, so that it is not reachable from outside of any class
    We make it static, because we want it to run before anyting else,
    also we will use it in static method
     */
    private static WebDriver driver;

    /*
    Create re-usable utility method which will return same driver instance when we call it.
    - If an instance doesn't exist, it will create first, and then it will always return same instance.
     */
    public static WebDriver getDriver(){


        if(driver == null){  // if driver/browser was never opened
             /*
            We will read our browserType from configuration.properties file.
            This way, we can control which browser is opened from outside our code.
             */

            String browserType = ConfigurationReader.getProperty("browser");

            /*
            Depending on the browserType returned from the configuration.properties
            switch statement will determine the "case", and open the matching browser.
             */


            switch(browserType){
                case "chrome":
                    //WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                   // WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

            }
        }

// Same driver instance will be returned every time we call Driver.getDriver() method
        return driver;

    }
     /*
    Create a new Driver.closeDriver(); it will use .quit() method to quit browsers, and then set the driver value back to null.
     */


    public static void closeDriver(){
        if(driver!=null) {
            /*
            This line will terminate the currently existing driver completely. It will not exist going forward.
             */
            driver.quit(); // this line will kill the session. value will not be null
            /*
            We assign the value back to "null" so that my "singleton" can create a newer one if needed.
             */
            driver = null;
        }
    }
}
