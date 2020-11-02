package seleniumTests;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.indexeddb.model.Key;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {

    private WebDriver driver;
    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://google.com");
    }

    @Test
    void testURL() {
        driver.navigate().to("https://giphy.com/");
        assertEquals("GIPHY | Search All the GIFs & Make Your Own Animated GIF",
                driver.getTitle());
    }

    @Test
    void testBack(){
        driver.navigate().to("https://giphy.com/");
        driver.navigate().back();
        assertEquals("Google",
                driver.getTitle());
    }

    @Test
    void test(){
        WebElement gmailAnchor = driver.findElement(new By.ByClassName("gb_g"));
        String expected = "Gmail";
        String actual = gmailAnchor.getText();
        assertEquals(expected, actual);
    }

    @Test
    void testTypeInSearch(){
        driver.findElement(By.name("q")).sendKeys("fat cat");
        driver.findElement(By.cssSelector("center:nth-child(1) > .gNO89b")).click();
        String expected = "fat cat - Google Search";
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    void TestTypeInSearchThenClick(){
        driver.findElement(By.name("q")).sendKeys("fat cat");
        driver.findElement(By.cssSelector("center:nth-child(1) > .gNO89b")).click();
        driver.findElement(By.linkText("Images")).click();
        String expected = "fat cat - Google Search";
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    void testGoToWebpageAndSearch(){
        driver.navigate().to("https://giphy.com/");
        driver.findElement(By.cssSelector(".Input-sc-w75cdz")).sendKeys("fat cat");
        driver.findElement(By.cssSelector(".search-button__SearchIcon-ndudpy-0 > img")).click();
        String expected = "Fat cat GIFs - Find & Share on GIPHY";
        String actual = driver.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    void testAllAnchors() {
        driver.navigate().to("https://giphy.com/");
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        assertEquals(119,anchors.size());
    }


    @AfterEach
    void tearDown(){
        driver.close();
    }

}
