package teneras.david.autogegresstest;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import android.view.KeyEvent;

import junit.framework.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import teneras.david.autogegresstest.MainActivity;

/**
 * Created by lidecai on 2015/11/26.
 */
public class AbstractTestCase {

    //打开app
    public void openApp(String appName, int apptype) throws UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();
        UiObject appObject = device.findObject(new UiSelector().text(appName));
        appObject.clickAndWaitForNewWindow();
        String packageNameString = device.getCurrentPackageName();

        if (apptype == 0)
            Assert.assertEquals("Opened Apps:", "com.qihoo.browser", packageNameString);
        if (apptype == 1)
            Assert.assertEquals("Opened Apps:", "com.qihoo.freebrowser", packageNameString);
    }

    //输入网址,apptype=0 is browser,=1 is freebrowser
    public void enterAddr(String netAddr, int apptype) throws UiObjectNotFoundException {
        if (apptype == 0) {
            UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            UiObject editobject = device.findObject(new UiSelector().resourceId("com.qihoo.browser:id/urlbar_urltext"));
            editobject.click();
            UiObject edittmpObject = device.findObject(new UiSelector().resourceId("com.qihoo.browser:id/input_view"));
            edittmpObject.click();
            while (!edittmpObject.getText().equals("搜索或输入网址")) {
                device.pressKeyCode(KeyEvent.KEYCODE_DEL);
            }
            edittmpObject.click();
            edittmpObject.setText(netAddr);
            device.pressEnter();
        }

    }
    //从java角度判断能否访问网址,1可以,0不行,-1出错
    public static int isWebsiteCanAccess(String netAddr) {
        int count = 0;
        int returnvalue=0;
        while (count < 3) {
            try {
                URL url = new URL(netAddr+"/");
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                int returncode = urlconnection.getResponseCode();

                if (returncode == 404) {
                    returnvalue = 0;
                    Log.i("isWebsiteCanAccess: ", "404");
                } else {
                    returnvalue=1;
                    Log.i("isWebsiteCanAccess2: ","200");
                    break;
                }

            } catch (Exception e) {
                returnvalue = -1;
                Log.i("isWebsiteCanAccess: ",e.getMessage());
                continue;
            }
            count++;
        }
        return returnvalue;
    }
    //试着在js页面中访问网址
    public String tryaccess(String netAddr) throws UiObjectNotFoundException
    {
        int initialaccess=isWebsiteCanAccess("www.baidu.com");
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        if(initialaccess == 1)
        {
            enterAddr("10.18.60.140/opentest",0);
            UiObject jsPromote=device.findObject(new UiSelector().resourceId("com.qihoo.browser:id/jsprompt_content"));
            jsPromote.click();
            jsPromote.setText(netAddr);

            return "1";
        }
       else if(initialaccess==0)
        {
            return netAddr+" "+"NOTFOUND";
        }
        else
        {
            return netAddr+" "+"ERROR";
        }
    }

    public void EnterTrainPlugin() throws Exception
    {

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.freezeRotation();
        int startx,starty,endx,endy,steps;
        startx=device.getDisplayWidth()/2;
        starty=device.getDisplayHeight()/2;
        endx=0;
        endy=device.getDisplayHeight()/2;
        for(int i=1;i<=3;i++) {
            device.swipe(startx, starty, endx, endy, 100);
        }
        UiObject traininfo=device.findObject(new UiSelector().className("android.view.View").index(14));

    }





}
