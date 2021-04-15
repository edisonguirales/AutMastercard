package com.mastercard.lts.rewards.pwrautomation.configuration;

import com.mastercard.lts.rewards.pwrautomation.pages.AbstractHomePage;
import com.mastercard.lts.rewards.pwrautomation.utils.DeviceType;
import io.appium.java_client.AppiumDriver;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToastMessageReader extends AbstractHomePage {

 private static Logger logger = LoggerFactory.getLogger(ToastMessageReader.class);

 @Override
 protected List<ExpectedCondition<WebElement>> initConditions() {
  return Collections.emptyList();
 }

 public ToastMessageReader(AppiumDriver<WebElement> driver, DeviceType deviceType, int timeout) {
  super(driver,deviceType,timeout);
 }

  private static String scrShotDir = "screenshots";
  private File scrFile;
  private static File scrShotDirPath = new java.io.File("./"+ scrShotDir+ "//");
  private String destFile;
 private static final int NUMBER_OF_SCREENSHOTS =10;


 public boolean readAndVerifyToastMessage(String toastMessage) {
  boolean toastExists=false;
  Tesseract instance = new Tesseract();
  File tessDataFolder = LoadLibs.extractTessResources("tessdata");
  instance.setDatapath(tessDataFolder.getAbsolutePath());
  for(int i=0;i<NUMBER_OF_SCREENSHOTS;i++)
  {
   String imgName=this.takeScreenShot(driver);
   File imageFile = new File(scrShotDirPath, imgName);
   try {
    String result = instance.doOCR(imageFile);
    String updatedString = result.replace("ﬁ","fi");
    if(!updatedString.equals("") && updatedString.contains(toastMessage))
    {
     System.out.println("UpdatedString"+updatedString);
      toastExists = true;
      break;
    }
    logger.info(updatedString);
   } catch (TesseractException e) {
    logger.error(e.getMessage());
   }
  }
  return toastExists;
 }

 /**
  * Takes screenshot of active screen
  * @return ImageFileName
  */
 private String takeScreenShot(WebDriver driver) {
  scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
  new File(scrShotDir).mkdirs();
  destFile = dateFormat.format(new Date()) + ".png";
  try {
   FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile));
  } catch (IOException e) {
   logger.info("Image not transferred to screenshot folder");
  }
  return destFile;
 }
}
