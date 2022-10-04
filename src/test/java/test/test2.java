package test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class test2 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Geeks");
		list.add("for");
		list.add("Geeks");
		list.add(1,"Hi");
		System.out.println(list);
		ArrayList<String> arrList = new ArrayList<>() ;
		arrList.add("Geeks");
		arrList.add("for");
		arrList.add("Geeks");
		System.out.println(arrList);
	}

}
