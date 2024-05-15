package numeral_system_trainer;

import java.util.Random;
import java.util.Scanner;

class NumeralSystemTrainerApp {
	public static void main(String [] args)
	{
		run();
	}
	
	public static void run()
	{
		Random rand = new Random();
		java.util.Scanner kb = new java.util.Scanner(System.in);
		long min = 0;
		long max = 15;
		boolean running = true;

		do {
			printMenu(min, max);
			String s = kb.nextLine();
			if (!NumberUtil.isValidNumber(s, 10))
				continue;
			switch (Integer.parseInt(s)) {
			case 1 -> min = readVal(kb);
			case 2 -> max = readVal(kb);
			case 3 -> doQuestionLoop(min, max, rand, kb);
			case 4 -> running = false;
			}	
		} while (running);
	}
	
	public static void printMenu(long min, long max)
	{
		System.out.println("Aşağıdaki komutlar mevcuttur:");
		System.out.printf("1) Gösterilecek minimum sayı değerini giriniz (Geçerli değer: %d)%n", min);
		System.out.printf("2) Gösterilecek maksimum sayı değerini giriniz (Geçerli değer: %d)%n", max);
		System.out.println("3) Başla");
		System.out.println("4) Çıkış");
		System.out.println("'q' Girerek menüye geri dönebilirsiniz.");
	}
	
	public static long readVal(Scanner kb)
	{
		System.out.print("Değer giriniz: ");
		return Long.parseLong(kb.nextLine());
	}
	
	public static boolean doQuestion(long min, long max, Random rand, Scanner kb)
	{
		int source = rand.nextInt(2) == 0 ? 2 : 16;
		int target = source == 2 ? 16 : 2;
		long number = rand.nextLong(max - min + 1) + min;
		
		askQuestion(source, target, number);
		String answer = kb.nextLine();
		if (!StringUtil.isValidNumber(answer, target)) {
			if ("q".equalsIgnoreCase(answer))
				return false;
			displayError();
			displayCorrectAnswer(number, target);
			return true;
		}
		boolean isCorrect = Long.parseLong(answer, target) == number;
		displayResult(isCorrect);
		if (!isCorrect)
			displayCorrectAnswer(number, target);
		return true;
	}
	
	public static void doQuestionLoop(long min, long max, Random rand, Scanner kb)
	{
		while (doQuestion(min, max, rand, kb))
			System.out.println();
	}
	
	public static void askQuestion(int source, int target, long number)
	{
		System.out.printf("'%d' sisteminde '%s' şeklinde gösterilen sayıyı '%d' sisteminde giriniz: ",
				source, Long.toString(number, source), target);
	}
	
	public static void displayError()
	{
		System.out.println("Sayı sistemine ait olmayan bir karakter girdiniz!");
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


