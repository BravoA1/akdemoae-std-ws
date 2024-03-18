package af.cmr.indyli.akdemia.ws.boot;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import af.cmr.indyli.akdemia.business.service.test.CompanyServiceTest;
import af.cmr.indyli.akdemia.business.service.test.EmployeeServiceTest;
import af.cmr.indyli.akdemia.business.service.test.ParticularServiceTest;
import af.cmr.indyli.akdemia.business.service.test.RequirementServiceTest;
import af.cmr.indyli.akdemia.business.service.test.SubThemeServiceTest;
import af.cmr.indyli.akdemia.business.service.test.ThemeServiceTest;
import af.cmr.indyli.akdemia.business.service.test.TrainingServiceTest;
import af.cmr.indyli.akdemia.business.service.test.UserServiceTest;

public class TestRunner {

    @SuppressWarnings("rawtypes")
	public static void main(String[] args) {
    	
        Class[] testClasses = {
        	UserServiceTest.class,
        	ThemeServiceTest.class,
        	RequirementServiceTest.class,
        	ParticularServiceTest.class,
        	CompanyServiceTest.class,
        	EmployeeServiceTest.class,
        	TrainingServiceTest.class,
        	SubThemeServiceTest.class
        };

        Result result = JUnitCore.runClasses(testClasses);

        for (Failure failure : result.getFailures()) {
            System.out.println("Test failed: " + failure.getTestHeader());
            System.out.println("Failure message: " + failure.getMessage());
            System.out.println("Exception: " + failure.getException());
            System.out.println("------------------------");
        }

        System.out.println("Total tests run: " + result.getRunCount());
        System.out.println("Total tests failed: " + result.getFailureCount());
        System.out.println("Total tests ignored: " + result.getIgnoreCount());
        System.out.println("Total execution time: " + result.getRunTime() + "ms");
    }
}
