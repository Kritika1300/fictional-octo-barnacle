import java.util.ArrayList;
import java.util.Collections;
import java.util.function.*;

public class LearnLambdas {

	public static void main(String[] args) {
		System.out.println("\n### Expressions and Types ###");

		// no lambdas
		HelloWorldGreeter helloWorldGreeter = new HelloWorldGreeter();
		greet(helloWorldGreeter);

		// instance of Greeter with an anonymous inner class
		Greeter greeter1 = new Greeter() {
			// anonymous inner class
			@Override
			public void perform() {
				System.out.println("Instance of Greeter interface by an anonymous class");
			}
		};
		greet(greeter1);

		// implementation with a lambda expression
		Greeter greeter2 = () ->
				System.out.println("Implementation of Greeter interface with a lambda expression");
		greet(greeter2);

		// Java 8 functional interface examples
		BiConsumer<String, Integer> printNTimes = (String s, Integer n) -> {
			for (int i = 0; i < n; i++)
				System.out.println(s);
		};
		printNTimes.accept("I'm getting printed 3 times", 3);

		BiFunction<String, Integer, Character> getCharAt = (String s, Integer n) -> s.charAt(n);
		System.out.println(getCharAt.apply("Luís Tovar", 2));

		DoubleToIntFunction metersToCentimeters = (double d) -> {
			double cpy = d * 100;
			return (int) cpy;
		};
		System.out.println(metersToCentimeters.applyAsInt(1.85));

		System.out.println("\n### Method References ###");
		// in this case, we call a method with no input or output
		Runnable callPrintSomething = () -> printSomething();
		// this lambda expression can be replaced by:
		callPrintSomething = LearnLambdas::printSomething;
		callPrintSomething.run();

		// in this case, we call a method that has input but no output. this can happen
		// because the printString "method" will use its only input as the input of the
		// called println method. the object upon which the method is called is the
		// System.out object, a global variable in the System class
		Consumer<String> printString = (String s) -> System.out.println(s);
		printString = System.out::println;
		printString.accept("A consumer is printing this through a method reference of System.out.println");
		// another example with input but no output
		ObjDoubleConsumer<ArrayList<Double>> multiplyAllElements
				= (ArrayList<Double> arraylist, double dValue) -> multiplyArrayElements(arraylist, dValue);
		// can be replaced by:
		multiplyAllElements = LearnLambdas::multiplyArrayElements;
		// let's test it
		ArrayList<Double> doubleArrayList = new ArrayList<>();
		Collections.addAll(doubleArrayList, 4.6, 5.123, 0.543451, 20123.476123);
		multiplyAllElements.accept(doubleArrayList, 0.8);

		// in this case the method has input and output
		Function<ArrayList<String>, String> getLastElement = (ArrayList<String> arrayList) -> lastElement(arrayList);
		getLastElement = LearnLambdas::lastElement;
		ArrayList<String> stringArrayList = new ArrayList<>();
		Collections.addAll(stringArrayList, "a", "b", "c");
		System.out.println(getLastElement.apply(stringArrayList));
	}

	public static void greet(Greeter greeter) {
		greeter.perform();
	}

	@FunctionalInterface
	public interface Greeter {
		void perform();
	}

	public static class HelloWorldGreeter implements Greeter {
		@Override
		public void perform() {
			System.out.println("Hello, World!");
		}
	}

	public static void printSomething() {
		System.out.println("A Runnable is printing this through a method reference");
	}

	public static void multiplyArrayElements(ArrayList<Double> arr, double d) {
		arr.forEach((Double element) -> System.out.println(element * d));
	}

	private static String lastElement(ArrayList<String> strings) {
		return strings.get(strings.size() - 1);
	}
}
