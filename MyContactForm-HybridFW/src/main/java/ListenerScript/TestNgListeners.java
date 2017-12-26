package ListenerScript;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgListeners implements ITestListener {


	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
	
		System.out.println(" Testcase Failed  "+result.getName());
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult result) {
		System.out.println(" Testcase started "+result.getName());
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(" Testcase passed "+result.getName());
		
	}

	public void onFinish(ITestContext result) {
		System.out.println(" Testcase Finished "+result.getName());
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
