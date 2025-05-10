1. Create the Object (SUT – System Under Test)
What is it?
The System Under Test (SUT) is the main class whose behavior you want to test.
Why use it?
To ensure that the class behaves as expected, independent of the rest of the system.
Example:
java
CalculatorService service = new CalculatorService();
Here, CalculatorService is the class under test.
________________________________________
 2. Identify the Dependency
What is it?
A dependency is any external class or service that the SUT uses.
Why use it?
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
Why use it?
To isolate the class being tested. We don’t want the real MathLibrary doing real math—just returning a predefined response.
Example using Mockito:
java
MathLibrary mockMathLib = mock(MathLibrary.class);
This creates a mock version of MathLibrary.
________________________________________
 4. Inject the Mocked Dependency into the SUT
What is it?
Inject the mock into your main class, so it uses the mocked dependency instead of the real one.
Why use it?
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
Why use it?
It makes your test setup cleaner and improves flexibility. Constructor injection is often preferred when the dependency is required.
Setter Example:
java
CopyEdit
service.setMathLibrary(mockMathLib);
Constructor Example:
java
CalculatorService service = new CalculatorService(mockMathLib);
________________________________________
 6. Stub the Response Using when and then
What is it?
Use Behavior-Driven Development (BDD) style to define how the mock should behave when a specific method is called.
Why use it?
To simulate different conditions (e.g., success or failure) and verify how your SUT responds.
Example:
java
when(mockMathLib.add(5, 3)).thenReturn(8);
Now, whenever mockMathLib.add(5, 3) is called, it will return 8—regardless of the real implementation.

