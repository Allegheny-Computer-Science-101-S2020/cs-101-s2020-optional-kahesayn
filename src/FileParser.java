// This work is mine, unless otherwise cited - Natnael Kahesay
import java.util.Scanner;
public class FileParser{
  public static String[] fileToArray(String fileName){
    String dir = System.getProperty("user.dir");
        FileUtility menu = new FileUtility(dir + "/data/" + fileName +".txt");
		int size = menu.size();
		String foodmenu = "";
		int marker = 0;
		for (int i = 0; i < size; i++){
			String line = menu.read();
			if (line.contains("<food>")){
				if (marker > 0)
					foodmenu += "\n";
				marker = i;
			}
			if ((i - marker) > 0 && (i - marker) <= 6)
				foodmenu += line;
		}
		menu.reset();
		String[] menulist = foodmenu.split("\n");
    return menulist;
  }

  public static void recommedFoodByBudget(String[] listofmenu, double limit){
    for (String foodinfo: listofmenu){
			double price = Double.parseDouble(foodinfo.substring(foodinfo.indexOf("<price>")
										+ 8,
										foodinfo.indexOf("</price>")));
			if (price < limit){
        System.out.println(foodinfo.substring(foodinfo.indexOf("<name>")
					+ 6,
					foodinfo.indexOf("</name>"))
					+ "; price is:" + price);
      }
		}
  }

  public static void recommedFoodByCalorie(String[] listofmenu, double limit){
    for (String foodinfo: listofmenu){
			double calories = Double.parseDouble(foodinfo.substring(foodinfo.indexOf("<calories>")
										+ 10,
										foodinfo.indexOf("</calories>")));
			if (calories < limit){
        System.out.println(foodinfo.substring(foodinfo.indexOf("<name>")
					+ 6,
					foodinfo.indexOf("</name>"))
					+ "; calorie is:" + calories);
      }
		}
  }

  public static void recommedFoodByLikeness(String[] listofmenu, String likeness){
    for (String foodinfo: listofmenu){
			String desc = foodinfo.substring(foodinfo.indexOf("<description>") + 13, foodinfo.indexOf("</description>"));
      likeness = likeness.toLowerCase();
      desc = desc.toLowerCase();
			if (desc.contains(likeness)){
        System.out.println(foodinfo.substring(foodinfo.indexOf("<name>")
					+ 6,
					foodinfo.indexOf("</name>")));
      }
		}
  }

  public static void main(String[] args){
    String[] listofmenu = fileToArray("foodmenu");
    Scanner scan = new Scanner(System.in);
    System.out.println("Do you want the data to be displayed in the console? (y/n)");
    String res1 = scan.next();
    if (res1.equalsIgnoreCase("y")){
      for (String item: listofmenu){
  			System.out.println(item);
  		}
    }

    System.out.println("Hey, welcome to the restaurant! Do you want me to recommend you some food choices?");
    String res2 = scan.next();
    if (res2.equalsIgnoreCase("y")){
      System.out.println("How would you want me to recommend. I can recommend"
      + " based on 1) your budget, 2) your calories if you are a calorie watcher"
      + ", 3) your food item likeness. Specify a number, so I can get started!");
      int res3 = scan.nextInt();
      if (res3 == 1){
        System.out.println("What is your budget for the breakfast?");
        double res4 = scan.nextDouble();
        recommedFoodByBudget(listofmenu, res4);
      } else if (res3 == 2){
        System.out.println("What is your expected calories intake for the breakfast?");
        double res5 = scan.nextDouble();
        recommedFoodByCalorie(listofmenu, res5);
      } else if (res3 == 3){
        System.out.println("What kind of breakfast do you have in mind to eat?");
        System.out.println("Please choose from <Waffles, Eggs, Bread, Hash Browns>:");
        String res6 = scan.next();
        recommedFoodByLikeness(listofmenu, res6);
      }
    }
  }
}
