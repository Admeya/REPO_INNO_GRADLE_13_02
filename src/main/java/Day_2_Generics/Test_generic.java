package Day_2_Generics;

public class Test_generic<T> {
	private T val;

	public Test_generic(T arg) {
		val = arg;
	}

	public String toString() {
		return "{" + val + "}";
	}

	public T getValue() {
		return val;
	}
}

class  Test{
	public static void main(String[] args) {
		Test_generic<Integer> value1 = new Test_generic<Integer>(new Integer(10));
		System.out.println(value1);
		
		Integer intValue1 = value1.getValue();
		Test_generic<String> value2 = new Test_generic<String>("Hello world");
		System.out.println(value2);
		
		String stringValue = value2.getValue();
		System.out.println("intValue1 = " + intValue1);
		System.out.println("stringValue = " + stringValue);
	}

}
