1. Create the Object (FUT –Function Under Test)
What is it?
The Function Under Test (FUT) is the main class whose behavior you want to test.
Why?
To ensure that the class behaves as expected, independent of the rest of the system.
Example:
java
CalculatorService service = new CalculatorService();
Here, CalculatorService is the class under test.
________________________________________

 2. Identify the Dependency
What is it?
A dependency is any external class or service that the SUT uses.
Why?
We don’t want to test external systems (like databases or services)—only the behavior of the CalculatorService.
Example:
java
// CalculatorService depends on MathLibrary
public class CalculatorService {
    private MathLibrary mathLib;

    public int addNumbers(int a, int b) {
        return mathLib.add(a, b);  // dependency call
    }
}
In this case, MathLibrary is a dependency of CalculatorService.
________________________________________

 3. Mock the Dependency
What is it?
Mocking means creating a fake version of the dependency that behaves in a controlled way.
Why?
To isolate the class being tested. We don’t want the real MathLibrary doing real math—just returning a predefined response.
Example using Mockito:
java
MathLibrary mockMathLib = mock(MathLibrary.class);
This creates a mock version of MathLibrary.
________________________________________

 4. Inject the Mocked Dependency into the FUT
What is it?
Inject the mock into your main class, so it uses the mocked dependency instead of the real one.
Why?
This ensures consistent and predictable behavior for testing.
Example:
java
CalculatorService service = new CalculatorService();
service.setMathLibrary(mockMathLib);
________________________________________

 5. Use Setter or Constructor Injection
What is it?
There are two common ways to inject dependencies into your class:
•	Setter Injection: Use a setter method.
•	Constructor Injection: Pass the dependency through the constructor.
Why?
It makes your test setup cleaner and improves flexibility. Constructor injection is often preferred when the dependency is required.
Setter Example:
java
service.setMathLibrary(mockMathLib);
Constructor Example:
Java
CalculatorService service = new CalculatorService(mockMathLib);
________________________________________

 6. Stub the Response Using when and then
What is it?
Use Behavior-Driven Development (BDD) style to define how the mock should behave when a specific method is called.
Why?
To simulate different conditions (e.g., success or failure) and verify how your SUT responds.
Example:
java
when(mockMathLib.add(5, 3)).thenReturn(8);
Now, whenever mockMathLib.add(5, 3) is called, it will return 8—regardless of the real implementation.
________________________________________

7.What is assert?
🔹 Definition:
assert statements are test validations. They check if the actual output matches the expected output.
🔹 Why?
To verify correctness of code during unit testing. If an assertion fails, the test fails.
 Common Assert Methods in JUnit:
Method	Purpose
assertEquals(expected, actual)	Checks if two values are equal
assertTrue(condition)	Checks if the condition is true
assertFalse(condition)	Checks if the condition is false
assertNotNull(object)	Ensures the object is not null


 Example:
java
int actual = calculator.add(2, 3);
assertEquals(5, actual);  // Passes if actual == 5
________________________________________

8. What is an invocation?
 Definition:
In Mockito, an invocation is a call to a method on a mock object.
 Why?
To verify that:
•	A method on a dependency (mock) was called
•	It was called the expected number of times
•	It was called in the correct order (optional)
Common verify() Invocation Patterns:
Method	Purpose
verify(mock).method()	Checks method was called once
verify(mock, times(n)).method()	Checks method was called n times
verify(mock, never()).method()	Ensures method was never called
mockingDetails(mock).getInvocations()	Lists all calls made to the mock
 Example:
java
MyService mockService = mock(MyService.class);
mockService.doSomething();

verify(mockService).doSomething(); // Invocation check
verify(mockService, times(1)).doSomething();
________________________________________
 Summary
Concept	Used For	Example
assert	Validating test results	assertEquals(5, result)
invocation	Verifying mock method calls	verify(mock).method()

