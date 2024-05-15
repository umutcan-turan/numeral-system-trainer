package numeral_system_trainer;

class NumeralSystemTrainerApp {
	public static void main(String [] args)
	{
		run();
	}
	
	public static void run()
	{
		java.util.Random rand = new java.util.Random();
		java.util.Scanner kb = new java.util.Scanner(System.in);
		long min = 0;
		long max = 15;
		boolean running = true;

		do {
			IOUtil.printMenu(min, max);
			switch ((int)readLong(kb, 10)) {
				case 1 -> min = readLong(kb, 10);
				case 2 -> max = readLong(kb, 10);
				case 3 -> { while (doQuestion(min, max, rand, kb)) System.out.println(); }
				case 0 -> running = false;
			}	
		} while (running);
	}

	
	public static long readLong(java.util.Scanner kb, int base)
	{
		while (true) {
			System.out.print("Değer giriniz: ");
			String str = kb.nextLine();
			if (StringUtil.isValidNumber(str, base))
				return Long.parseLong(str, base);
			IOUtil.displayError(str);
		}
		// Unreachable
	}
	
	public static boolean doQuestion(long min, long max, java.util.Random rand, java.util.Scanner kb)
	{
		int source = rand.nextInt(2) == 0 ? 2 : 16;
		int target = source == 2 ? 16 : 2;
		long number = rand.nextLong(max - min + 1) + min;
		
		IOUtil.askQuestion(source, target, number);
		long answer = readLong(kb, target);
		if (answer == -1)
			return false;
		boolean isCorrect = answer == number;
		IOUtil.displayResult(isCorrect);
		if (!isCorrect)
			IOUtil.displayCorrectAnswer(number, target);
		return true;
	}
}

class IOUtil {
	public static long readLong(java.util.Scanner kb, int base)
	{
		while (true) {
			System.out.print("Değer giriniz: ");
			String str = kb.nextLine();
			if (StringUtil.isValidNumber(str, base))
				return Long.parseLong(str, base);
			displayError(str);
		}
	}
	
	public static void printMenu(long min, long max)
	{
		System.out.println("Aşağıdaki komutlar mevcuttur:");
		System.out.printf("1) Gösterilecek minimum sayı değerini giriniz (Geçerli değer: %d)%n", min);
		System.out.printf("2) Gösterilecek maksimum sayı değerini giriniz (Geçerli değer: %d)%n", max);
		System.out.println("3) Başla");
		System.out.println("0) Çıkış");
		System.out.println("'-1' Girerek menüye geri dönebilirsiniz.");
	}
	
	public static void askQuestion(int source, int target, long number)
	{
		System.out.printf("'%d' sisteminde '%s' şeklinde gösterilen sayıyı '%d' sisteminde nasıl gösterilir?%n",
				source, Long.toString(number, source), target);
	}
	
	public static void displayError(String str)
	{
		System.out.printf("Geçersiz değer girdiniz: '%s'%n", str);
	}
	
	public static void displayResult(boolean isCorrect)
	{
		System.out.printf("%s%n", isCorrect ? "Tebrikler! Doğru cevap!" : "Bir dahaki sefere..!");
	}
	
	public static void displayCorrectAnswer(long number, int target)
	{
		System.out.printf("Doğru cevap: %s%n", Long.toString(number, target));
	}
}

class StringUtil {
	public static boolean isValidNumber(String str, int base)
	{
		int i = 0;
		
		if (str.isEmpty())
			return false;
		if (str.length() > 1 && (str.charAt(i) == '-' || str.charAt(i) == '+'))
			i++;
		for (; i < str.length(); i++)
			if (Character.digit(str.charAt(i), base) == -1)
				return false;
		return true;
	}
}


