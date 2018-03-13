package callcenteroperation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import com.almundo.callcenter.CallCenter;
import com.almundo.callcenter.Dispatcher;
import com.almundo.callcenter.EmployeeRole;

/**
 * Test for Call Center operation
 * @author jhon.londono
 *
 */
public class CallCenterTest {
	
	/** The executor to attend calls */
	private ExecutorService executorCalls;
	
	/**
	 * The object responsible to dispatch the incoming 
	 * calls to respective employee and attend the calls
	 */
	private Dispatcher dispatcher;
	
	/** The executor to start the operation of employees */
	private ExecutorService executorEmployees;
	
	/** The class to test */
	private CallCenter callCenter;
	
	/**
	 * Set up the number of call the dispatcher is able to process and the number of
	 * employees foe every role
	 */
	@Before
	public void setUp() {
		executorCalls = Executors.newFixedThreadPool(10);
		dispatcher = new Dispatcher(7, 3, 1);
		executorEmployees = Executors.newFixedThreadPool(11);
		
		List<EmployeeRole> employeeList = new ArrayList<EmployeeRole>();
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.OPERATOR);
		employeeList.add(EmployeeRole.SUPERVISOR);
		employeeList.add(EmployeeRole.SUPERVISOR);
		employeeList.add(EmployeeRole.SUPERVISOR);
		employeeList.add(EmployeeRole.DIRECTOR);
		
		callCenter = new CallCenter(executorCalls, dispatcher, executorEmployees);
		callCenter.setEmployeeList(employeeList);
	}
	
	/**
	 * Test the scenario when he call center receive 10 calls
	 */
	@Test
	public void startOperationTest() {
		
		callCenter.setNumberOfCalls(10);
		callCenter.startOperation();
	}
	
	/**
	 * Test the scenario when he call center receive more calls than the dispatcher
	 * is able to process at the same time
	 */
	@Test
	public void startOperationTestWithMoreThan10Calls() {
		
		callCenter.setNumberOfCalls(20);
		callCenter.startOperation();
	}

}
