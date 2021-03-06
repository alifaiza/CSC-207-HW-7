package mainpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class LootGenerator {

	public Parser parser;
	ArrayList<Monster> monsterList;
	ArrayList<BaseItem> baseItemList;
	ArrayList<Prefix> prefixList;
	ArrayList<Suffix> suffixList;
	Random rand = new Random();
	Map<String, ArrayList<String>> treasureMap;

	public LootGenerator() {
		parser = new Parser();
	}

	public Monster pickMonster(File file) throws FileNotFoundException {
		monsterList = parser.listMonsters(file);
		int index = rand.nextInt(monsterList.size());
		Monster randomMonster = monsterList.get(index);
		return randomMonster;

	}

	public String fetchTreasureClass(Monster monster)  {
		return monster.getTreasure();
	}

	private String generateBaseItemHelper(String chosenItem, ArrayList<String> itemsList)  {


		if (treasureMap.get(chosenItem) != null) {
			itemsList = treasureMap.get(chosenItem);
			chosenItem = itemsList.get(rand.nextInt(3));
			return generateBaseItemHelper(chosenItem, itemsList);
		}
		else {
			return chosenItem;
		}
	}

	public String generateBaseItem(Monster monster, File file) throws FileNotFoundException  {
		treasureMap = parser.listTreasure(file);
		ArrayList<String> itemsList = treasureMap.get(monster.getTreasure());
		String chosenItem = itemsList.get(rand.nextInt(3));
		return generateBaseItemHelper(chosenItem, itemsList);


	}

	//http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
	public String generateBaseStats(String baseItem, File file) throws FileNotFoundException  {
		baseItemList = parser.listBaseItems(file);
		for(BaseItem item : baseItemList)  {
			if(baseItem.equals(item.getBaseItem()))  {
				int minac = Integer.parseInt(item.getMinac());
				int maxac = Integer.parseInt(item.getMaxac());
				int randomNum = rand.nextInt(maxac - minac + 1) + minac;
				return "" + randomNum;
			}
		}
		return "";
	}

	public Affix generateAffix(File prefixFile, File suffixFile) throws FileNotFoundException {
		Prefix prefix = null;
		Suffix suffix = null;
		int prefixChance = rand.nextInt(2);
		int suffixChance = rand.nextInt(2);
		if(prefixChance == 1) {
			prefixList = parser.listPrefix(prefixFile);
			int prefixIndex = rand.nextInt(prefixList.size() - 1);
			prefix = prefixList.get(prefixIndex);
		}
		if(suffixChance == 1) {
			suffixList = parser.listSuffix(suffixFile);
			int suffixIndex = rand.nextInt(suffixList.size() - 1);
			suffix = suffixList.get(suffixIndex);
		}
		return new Affix(prefix, suffix);
	}
	
	

}
