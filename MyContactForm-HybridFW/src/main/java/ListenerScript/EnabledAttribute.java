package ListenerScript;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


//Setting Up the Listeners 
@Listeners(ListenerScript.TestNgListeners.class)

public class EnabledAttribute  {
  @Test
  public void method1() {
	  
	  System.out.println("AAAAAAAAa");
  }
  
  @Test
  public void method2() {
	  System.out.println("BBBBBBBBBBBBB");
  }
  
  @Test
  public void method3() {
	  System.out.println("CCCCCCCCCCCCCCcc");
  }
  
  @Test
  public void method4() {
	  System.out.println("ddddddddddddddddddd");
  }
  
  @Test
  public void method5() {
	  
	  System.out.println("FFFFFFFFFFFFFFFFf");
  }
  
}
