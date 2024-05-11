package numeral_system_trainer;

class NumeralSystemTrainerApp {
	public static void main(String [] args)
	{
		run();
	}
	
	public static void run()
	{
		java.util.Scanner kb = new java.util.Scanner(System.in);

		while (true) {

		}
	}
}

class NumberUtil {
	public static boolean isValidNumber(String str, int base)
	{
		for (int i = 0; i < str.length(); i++)
			if (Character.digit(str.charAt(i), base) == -1)
				return false;
		return true;
	}
	
	public static String baseToDecimal(String str, int base)
	{
		return Long.toString(Long.parseLong(str, base), 10);
	}
	
	public static String decimalToBase(String str, int base)
	{
		return Long.toString(Long.parseLong(str, 10), base);
	}
}


