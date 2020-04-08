public class Examples {
	public static void main(String[] args) {
		
		//create a FileUtility that uses a file called "dummy.txt"
		String dir = System.getProperty("user.dir");
        FileUtility dummy = new FileUtility(dir + "/data/dummy.txt");
		
		//read the first line from "dummy.txt" and store it
		String first_line = dummy.read();
		
		//read the second line from "dummy.txt" and store it
		String second_line = dummy.read();
		
		//append a line to the end of "dummy.txt"
		dummy.write("Thus ends this file");
		
		//update the first line to be "This is now the first line"
		dummy.update(0, "This is now the first line");
		
		//get how many lines there are in the file and store that
		int lines = dummy.size();
		
		//update the last line to be "This is now the last line"
		dummy.update(lines-1, "This is now the last line");
		
		//reset the reader to the beginning of the file
		dummy.reset();
		
		//read the first line again, and print it
		String line = dummy.read();
		System.out.println(line);
		
	}
}
