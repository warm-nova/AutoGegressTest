package teneras.david.autogegresstest;

import android.app.UiAutomation;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.internal.statement.UiThreadStatement;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;

import teneras.david.autogegresstest.MainActivity;
/**
 * Created by lidecai on 2015/11/26.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest extends AbstractTestCase{

    //打开浏览器
    @Test
    public void test1OpenBrowser() throws UiObjectNotFoundException
    {
        openApp("360浏览器",0);
    }
    //测试天气位置
    @Test
    public void test2WeatherPosition() throws Exception
    {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        UiObject WeatherPlace = device.findObject(new UiSelector().resourceId("com.qihoo.browser:id/a5x"));
        WeatherPlace.click();
        UiObject Refreashbtn = device.findObject(new UiSelector().resourceId("com.qihoo.browser:id/m3"));
        Refreashbtn.click();
        UiObject WeatherPlace2 = device.findObject(new UiSelector().resourceId("com.qihoo.browser:id/m4"));
        Assert.assertEquals("Opened Apps:", "朝阳区", WeatherPlace2.getText());
        WeatherPlace2.click();
        Assert.assertEquals("Opened Apps:", "朝阳", WeatherPlace.getText());
    }






}
