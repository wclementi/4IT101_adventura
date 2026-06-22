package cz.vse.adventura.logika;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Pomocný spouštěč JUnit 5 testů pro spuštění z příkazové řádky (CLI).
 * Používá reflexi pro vyhledání metod označených anotacemi @BeforeEach a @Test.
 *
 * @author Kajetan Franciszek Wielgosz
 */
public class TestRunner {
    /**
     * Hlavní spouštěcí metoda testovacího runneru.
     * @param args parametry příkazové řádky (nevyužito)
     */
    public static void main(String[] args) {
        Class<?>[] testClasses = {
            HraTest.class,
            BatohTest.class,
            ProstorTest.class,
            SeznamPrikazuTest.class
        };

        int totalTests = 0;
        int passedTests = 0;
        int failedTests = 0;
        List<String> failures = new ArrayList<>();

        System.out.println("========================================");
        System.out.println("Spouštění JUnit 5 testů pro Adventuru...");
        System.out.println("========================================");

        for (Class<?> testClass : testClasses) {
            System.out.println("\nSpouštím testy ve třídě: " + testClass.getSimpleName());
            
            List<Method> beforeEachMethods = new ArrayList<>();
            List<Method> testMethods = new ArrayList<>();
            
            for (Method method : testClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(org.junit.jupiter.api.BeforeEach.class)) {
                    beforeEachMethods.add(method);
                }
                if (method.isAnnotationPresent(org.junit.jupiter.api.Test.class)) {
                    testMethods.add(method);
                }
            }

            for (Method testMethod : testMethods) {
                totalTests++;
                String testName = testClass.getSimpleName() + "." + testMethod.getName();
                System.out.print("  - " + testMethod.getName() + " ... ");
                try {
                    Object testInstance = testClass.getDeclaredConstructor().newInstance();
                    
                    // Spuštění inicializačních metod (@BeforeEach)
                    for (Method beforeEach : beforeEachMethods) {
                        beforeEach.invoke(testInstance);
                    }
                    
                    // Spuštění samotného testu (@Test)
                    testMethod.invoke(testInstance);
                    
                    System.out.println("OK");
                    passedTests++;
                } catch (Throwable t) {
                    System.out.println("CHYBA");
                    failedTests++;
                    Throwable cause = t.getCause() != null ? t.getCause() : t;
                    failures.add(testName + ": " + cause.toString());
                    cause.printStackTrace(System.out);
                }
            }
        }

        System.out.println("\n========================================");
        System.out.println("Shrnutí testovacího běhu:");
        System.out.println("  Celkem testů:    " + totalTests);
        System.out.println("  Úspěšných:       " + passedTests);
        System.out.println("  Chybných:        " + failedTests);
        System.out.println("========================================");

        if (failedTests > 0) {
            System.out.println("\nChyby v testech:");
            for (String failure : failures) {
                System.out.println("  - " + failure);
            }
            System.exit(1);
        } else {
            System.out.println("\nVšechny testy proběhly úspěšně!");
            System.exit(0);
        }
    }
}
