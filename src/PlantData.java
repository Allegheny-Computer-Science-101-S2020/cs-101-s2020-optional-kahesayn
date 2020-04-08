public class PlantData{
	public static void main(String[] args){
		// The line no 7 is added to get the current working directory. 
		// Note: this line was not part of the class discussion code. 
		// If someone is interested to learn about this line, then feel
		// free to talk to me regarding it. 
		String dir = System.getProperty("user.dir");
        FileUtility plant = new FileUtility(dir + "/data/plant.txt");
		int size = plant.size();
		System.out.println("File size is:" + size);
		String plantcontents = "";
		int marker = 0;
		for (int i = 0; i < size; i++){
			String line = plant.read();	
			if (line.contains("<PLANT>")){
				if (marker > 0)
					plantcontents += "\n";
				marker = i;
			}
			if ((i - marker) > 0 && (i - marker) <= 6)
				plantcontents += line;	
			
		}
		plant.reset();
		String[] listOfPlants = plantcontents.split("\n");
		
		/*
		for (String item: listOfPlants){
			System.out.println(item);
		} */ // This will simply print all the plant items.

		/*
		
		// Let us ask some questions:
		// q1 - show the plants that are priced less than 5$?
		// q2 - show the plants that needs sunlight?
		// q3 - show the plants that are from planting zone 3?


		// q1
		for (String plantinfo: listOfPlants){
			double price = Double.parseDouble(plantinfo.substring(plantinfo.indexOf("<PRICE>") 
										+ 8, 
										plantinfo.indexOf("</PRICE>")));
			if (price < 5.0)
				System.out.println(plantinfo.substring(plantinfo.indexOf("<COMMON>") 
					+ 8, 
					plantinfo.indexOf("</COMMON>")) 
					+ "; price is:" + price);
			
			
		}
		*/

		// q2
		for (String plantinfo: listOfPlants){
			String light = plantinfo.substring(plantinfo.indexOf("<LIGHT>") 
										+ 7, 
										plantinfo.indexOf("</LIGHT>"));



			if (light.contains("Sunny") ||  light.contains("Sun or Shade"))
				System.out.println(plantinfo.substring(plantinfo.indexOf("<COMMON>") 
					+ 8, 
					plantinfo.indexOf("</COMMON>")) 
					+ "; light is:" + light);
			
		}

		// q3
		for (String plantinfo: listOfPlants){
			String zoneAsStr = plantinfo.substring(plantinfo.indexOf("<ZONE>") 
										+ 6, 
											plantinfo.indexOf("</ZONE>"));
			int zone = 0;
			if (!zoneAsStr.contains("Annual") &&
				!zoneAsStr.contains("3 - 5"))
				 zone = Integer.parseInt(zoneAsStr);
			
			if (zone == 3)
				System.out.println(plantinfo.substring(plantinfo.indexOf("<COMMON>") 
					+ 8, 
					plantinfo.indexOf("</COMMON>")) 
					+ "; zone is:" + zone);


			
		}

		




		
	}
}