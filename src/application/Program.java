package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		String filePath = "C:\\temp\\curso-java-udemy\\programacao-funcional-expressao-lambda\\support-material\\in.txt";
		
		try(BufferedReader bf = new BufferedReader(new FileReader(filePath))){

			List<Product> products = new ArrayList<>();
			
			String line = bf.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				products.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = bf.readLine();
			}
			
			double averagePrice = products.stream()
					.map(p -> p.getPrice())
					.reduce(0.0, (x,y) -> x + y) / products.size();
			
			System.out.println("Average Price: " + String.format("%.2f", averagePrice));
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> productames = products.stream()
					.filter(p -> p.getPrice() < averagePrice)
					.map(p -> p.getName())
					.sorted(comp.reversed()).toList();
			
			productames.forEach(System.out::println);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}
}