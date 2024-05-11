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
			if (s.isEmpty())
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
		System.out.println("-1 Girerek menüye geri dönebilirsiniz.");
	}
	
	public static long readVal(Scanner kb)
	{
		System.out.print("Değer giriniz: ");
		return Long.parseLong(kb.nextLine());
	}
	
	public static boolean doQuestion(long min, long max, Random rand, Scanner kb)
	{
		int source = generateBase(0, rand);
		int target = generateBase(source, rand);
		long number = rand.nextLong(max - min + 1) + min;
		
		askQuestion(source, target, number);
		String answer = kb.nextLine();
		if (answer.isEmpty() || Long.parseLong(answer, target) == -1)
			return false;
		if (NumberUtil.isValidNumber(answer, target)) {
			answer = NumberUtil.baseToDecimal(answer, target);
			boolean isCorrect = Long.parseLong(answer) == number;
			displayResult(isCorrect);
			if (!isCorrect)
				displayCorrectAnswer(number, target);
		} else {
			displayError();
			displayCorrectAnswer(number, target);
		}
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
				source, NumberUtil.decimalToBase(Long.toString(number), source), target);
	}
	
	public static void displayError()
	{
		System.out.println("Sayı sistemine ait olmayan bir karakter girdiniz!");
	}
	
	public static void displayResult(boolean isCorrect)
	{
		if (isCorrect)
			System.out.println("Tebrikler! Doğru cevap!");
		else
			System.out.println("Bir daha ki sefere..!");
	}
	
	public static void displayCorrectAnswer(long number, int target)
	{
		System.out.printf("Doğru cevap: %s%n", NumberUtil.decimalToBase(Long.toString(number), target));
	}
	
	public static int generateBase(int num, Random rand)
	{
		int result;
		
		do {
			result = switch (rand.nextInt(3)) {
			case 0 -> 2;
			case 1 -> 10;
			case 2 -> 6;
			default -> 2;
			};
		} while (result == num);
		return result;
	}
}

class NumberUtil {
	public static boolean isValidNumber(String str, int base)
	{
		if (str.isEmpty())
			return false;
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


