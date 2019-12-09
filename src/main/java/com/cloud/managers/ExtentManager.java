package com.cloud.managers;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.cloud.core.Utils;

import javax.rmi.CORBA.Util;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentManager
{
    private static ExtentReports extent;
    public static ExtentReports extent_Report;
    public static ExtentTest extent_Logger;
    public static Markup markup;
    private static String mongoHost;
    private static Integer mongoPort;

    public static ExtentReports getInstance()
    {

        if (extent == null) {
            DateFormat d = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = new Date();
            String todayDate = String.valueOf(d.format(date));
            //System.out.println(todayDate);
            //System.out.println(d.toLocalTime());
            File folderName = new File( "./TestReport/" + todayDate);
            if (!folderName.exists()) {
                if (folderName.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }

            String fileName = Utils.getCurrentTime()
                    .replace(' ','_')
                    .replace('/','_')
                    .replace(':','_')
                    .replace('.','_');

            String reportPath = "./TestReport/" + todayDate + "/" + fileName+".html";
            System.out.println(reportPath);

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
            htmlReporter.config().setChartVisibilityOnOpen(false);


            //htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
            //htmlReporter.config().setChartVisibilityOnOpen(true);

            /*htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle(reportPath);
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setReportName(reportPath);*/
            htmlReporter.loadConfig("./extent.xml");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            //extent.attachReporter(klovReporter());
        }
        return extent;
    }



    private static boolean isMongoPortHostProvided()
    {
        // if (configFileManager.getProperty("MONGODB_SERVER") != null
        //       && configFileManager.getProperty("MONGODB_PORT") != null) {
        setMongoHost("localhost");//configFileManager.getProperty("MONGODB_SERVER"));
        setMongoPort(Integer.parseInt("27017"));//configFileManager.getProperty("MONGODB_PORT")));
        return true;
    } /*else {
            setMongoHost("localhost");
            setMongoPort(27017);
            return true;
        }*/

    //}

    private static KlovReporter klovReporter() {
        KlovReporter klov = new KlovReporter();
        if (isMongoPortHostProvided()) {
            klov.initMongoDbConnection(getMongoHost(), getMongoPort());
            String klovProjectName = System.getenv("projectname");
            String klovReportName = System.getenv("reportname");
            String projectname = klovProjectName;
            String reportname = klovReportName;
            if (klovProjectName == null || klovReportName == null) {
                projectname = "AppiumTestDistribution";
                reportname = "ExtentReports";
            }
            klov.setProjectName(projectname);
            klov.setReportName(reportname);
            klov.setKlovUrl("http://" + getMongoHost() + ":1337");
        }
        return klov;
    }

    private static String getMongoHost() {
        return mongoHost;
    }

    private static void setMongoHost(String mongo) {
        mongoHost = mongo;
    }

    private static Integer getMongoPort() {
        return mongoPort;
    }

    private static void setMongoPort(Integer port) {
        mongoPort = port;
    }

    public static void main(String[] args)
    {
        ExtentManager extentManager = new ExtentManager();
        ExtentReports extentReports = extentManager.getInstance();
        ExtentTest test =  extentReports.createTest("Omkar First test", "seeee");
        test.pass("Omkar is pass");
        test.assignCategory("Regression ");
        System.out.println( test.getStatus());
/*

        String[][] data = {
                { "Header1", "Header2", "Header3" },
                { "Content.1.1", "Content.2.1", "Content.3.1" },
                { "Content.1.2", "Content.2.2", "Content.3.2" },
                { "Content.1.3", "Content.2.3", "Content.3.3" },
                { "Content.1.4", "Content.2.4", "Content.3.4" }
        };


        Markup m = MarkupHelper.createTable(data);

        test.pass(m);
// or
        test.log(Status.PASS, m);
*/




        extentReports.flush();
    }
}
