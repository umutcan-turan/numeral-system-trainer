package numeral_system_trainer;

class NumeralSystemTrainerApp {
	public static void main(String [] args)
	{
		run();
	}
	
	public static void run()
	{
		
	}
}

class BinaryUtil {
	public static boolean isValidBinaryNumber(String str)
	{
		for (int i = 0; i < str.length(); i++)
			if (!isValidBinaryDigit(str.charAt(i)))
				return false;
		return true;
	}
	
	public static boolean isValidBinaryDigit(char ch)
	{
		return ch == '1' || ch == '0';
	}
	
	public static long binaryToDecimal(String str)
	{
		
	}
}
