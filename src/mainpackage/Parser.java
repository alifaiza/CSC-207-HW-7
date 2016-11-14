package mainpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parser {

	public ArrayList<Monster> listMonsters(File file) throws FileNotFoundException {
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		String monster = null;
		String type = null;
		String level = null;
		String treasure = null;
		Scanner scan = new Scanner(file);
		//http://stackoverflow.com/questions/6244670/java-using-multiple-delimiters-in-a-scanner
		scan.useDelimiter("\t|\r");
		while (scan.hasNext()) {
			monster = scan.next();
			type = scan.next();
			level = scan.next();
			treasure = scan.next();
			monsters.add(new Monster(monster, type, level, treasure));
		}
		scan.close();
		return monsters;
	}

	public Map<String, ArrayList<String>> listTreasure(File file) throws FileNotFoundException {
		Map<String, ArrayList<String>> treasures = new HashMap<String, ArrayList<String>>();
		String treasure = null;
		Scanner scan = new Scanner(file);
		//http://stackoverflow.com/questions/6244670/java-using-multiple-delimiters-in-a-scanner
		scan.useDelimiter("\t|\r");
		while (scan.hasNext()) {
			treasure = scan.next();
			ArrayList<String> items = new ArrayList<String>();
			for (int i = 0; i < 3; i++) {
				items.add(scan.next());
			}
			treasures.put(treasure, items);
		}
		scan.close();
		return treasures;
	}
	
	public ArrayList<BaseItem> listBaseItems(File file) throws FileNotFoundException {
		ArrayList<BaseItem> baseItems = new ArrayList<BaseItem>();
		String baseItem = null;
		String minac = null;
		String maxac = null;
		Scanner scan = new Scanner(file);
		//http://stackoverflow.com/questions/6244670/java-using-multiple-delimiters-in-a-scanner
		scan.useDelimiter("\t|\r");
		while (scan.hasNext()) {
			baseItem = scan.next();
			minac = scan.next();
			maxac = scan.next();
			baseItems.add(new BaseItem(baseItem, minac, maxac));
		}
		scan.close();
		return baseItems;
	}
	
}
