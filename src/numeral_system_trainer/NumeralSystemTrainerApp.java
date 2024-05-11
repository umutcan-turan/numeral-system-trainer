package numeral_system_trainer;

class NumeralSystemTrainerApp {
	public static void main(String [] args)
	{
		run();
	}
	
	public static void run()
	{
		java.util.Scanner kb = new java.util.Scanner(System.in);
		
		System.out.println(Character.getNumericValue('a'));
		while (true) {
			String s = kb.nextLine();
			System.out.println(NumberUtil.decimalToBase(s, 16));
		}
	}
}

class NumberUtil {
	public static boolean isValidNumber(String str, int base)
	{
		for (int i = 0; i < str.length(); i++)
			if (!isValidDigit(str.charAt(i), base))
				return false;
		return true;
	}
	
	public static boolean isValidDigit(char ch, int base)
	{
		return switch (base) {
			case 0, 10 -> isValidDecimalDigit(ch);
			case 2     -> isValidBinaryDigit(ch);
			case 16    -> isValidHexDigit(ch);
			default    -> isValidDecimalDigit(ch);
		};
	}
	
	public static boolean isValidHexDigit(char ch)
	{
		return 'a' <= ch && ch <= 'f' || 'A' <= ch && ch <= 'F' || isValidDecimalDigit(ch);
	}
	
	public static boolean isValidBinaryDigit(char ch)
	{
		return ch == '0' || ch == '1';
	}
	
	public static boolean isValidDecimalDigit(char ch)
	{
		return '0' <= ch && ch <= '9';
	}
	
	public static String baseToDecimal(String str, int base)
	{
		return Integer.toString(Integer.parseInt(str, base), 10);
	}
	
	public static String decimalToBase(String str, int base)
	{
		return Integer.toString(Integer.parseInt(str, 10), base);
	}
}


