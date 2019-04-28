package UtilityPackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/*
 * Created by - SIVAKUMAR.V
 * This class created as Listener which is used to Retry a Test Case if its failed
 */

public class RetryListener implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annotation.setRetryAnalyzer(UtilityPackage.RetryAnalyzer.class);
		
	}

}
