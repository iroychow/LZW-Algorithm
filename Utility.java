/*author Ishita*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utility {

	//reading from file
		public static String readInputFile(String fileName) 
		{
			
	        String line = null;
	        try {
	        	URL url = LzwEncoder.class.getClassLoader().getResource(fileName);
	    		File file = new File(url.getPath());
	    	    FileReader fileReader = new FileReader(file);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            line = bufferedReader.readLine();
	            bufferedReader.close();         
	        }
	        catch(Exception ex) {
	            System.out.println( "Error in reading file data");                
	        }
	        
	        return line;
		}
		
		//write to file
		public static void writeToFile(String fileName,String content){
			try{
	            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
	            writer.println(content);
	            writer.close();
	        } catch (IOException e) {
	           System.out.println("Failed to write to the file");
	        }  
		}
		
		//generate file name
		public static String generateOutputFileName(String input,String extensionName,String formattedName){
			
			String output = "";
			int position = input.indexOf(".");
			if(position != -1){
				output = input.substring(0, position)+formattedName+"."+extensionName;
			}
			return output;
		}
		
		//conversion of integer values to binary
		public static String  getBinary(int value){
			String result = "";
			try {
				String str = Character.toString((char)value);
				byte[] tmp= str.getBytes("UTF-16BE");
					for(int i=0;i<tmp.length;i++)
						result = result + (String.format("%8s", Integer.toBinaryString(tmp[i])).replace(' ', '0')+" ");
				} catch (UnsupportedEncodingException e) {
				System.err.println("Error in converting to binary");
			}
			return result;
		}
		
		//conversion of binary values to integer
		public static List<Integer> getIntegerValueFromBinary(String str){
			String[] strSplit = str.split(" ");
			List<Integer>  result = new ArrayList<>();
			for(int i= 0;i<strSplit.length-1;i=i+2){
				String temp = strSplit[i] +strSplit[i+1];
				result.add(Integer.parseInt(temp,2));
				}
			return result;
			}
		
		//Test method for initial run
		public static List<Integer> stringToIntegerArray(String input){
			List<Integer> result = new ArrayList<>();
			input = input.replace("[", "").replaceAll("]", "");
			String[] temp =  input.split(",");
			for(String s : temp){
				result.add(Integer.parseInt(s.trim()));
			}
			return result;
		}
}
