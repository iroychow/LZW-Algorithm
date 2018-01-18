/*author Ishita*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class LzwEncoder {

	public static int bitLength ;
	static HashMap<String, Integer> table=new HashMap<>();

	public static void main(String[] args) {

		//variable declarations
		String inputFileName ="";
		String outputFileName ="";
		String inputString="";
		List<Integer> output;

		//input verification
		if(args== null ||args.length == 0 || args.length==1 ){
			System.out.println("Invalid number of arguments passed");
			return;
		}
		else
		{
			 inputFileName= args[0];
			try{
				bitLength= Integer.parseInt(args[1]);
				System.out.println("Input File is = " + " " +inputFileName +"\nInput Bit length is" +" " + bitLength);
			}
			catch (NumberFormatException e) {
				System.out.println("Provide valid bit length as second parameter");
			}
		}

		//calling encoding logic and methods from Utility class.
		inputString=Utility.readInputFile(inputFileName);

		if (inputString==null){
			System.out.println("Empty file!");
			return;
		}
		else{
			LzwEncoder lzwEncoder= new LzwEncoder();
			output = lzwEncoder.encodingLogic(inputString);
			System.out.println("Encoded output \n"+output);

			outputFileName = Utility.generateOutputFileName(inputFileName,"lzw","");
			if(!outputFileName.equalsIgnoreCase("")){
				String finalResult = lzwEncoder.convertToBinary(output);
				Utility.writeToFile(outputFileName,finalResult);
				System.out.println("Binary output printed in file "+outputFileName);
			}
		}
	}

	//Encoding logic
	public  List<Integer> encodingLogic(String inputString){
		Integer max_table_size=(int)Math.pow(2,bitLength);
		int counter=0;
		String tempString="";
		char symbol;
		List<Integer> output=new ArrayList<>();
		int currentSize=256;
		try{
			for(int i=0;i<currentSize;i++)
				table.put(String.valueOf((char)i), i);

			while(counter<inputString.length())
			{
				symbol=inputString.charAt(counter);
				if(table.containsKey(tempString+symbol)){
					tempString=tempString+symbol;
				}
				else
				{
					output.add(table.get(tempString));
					if(table.size()<max_table_size)
					{

						table.put(tempString+symbol, currentSize++);
					}
					tempString=String.valueOf(symbol);

				}
				counter++;
			}
			output.add(table.get(tempString));
		}
		catch (Exception e) {
			System.out.println("Error in Encoding logic method");
		}

		return output;
	}

	//conversion to binary
	public String convertToBinary(List<Integer> inputValue){
		String temp = "";
		for(Integer i :inputValue){
			temp = temp + Utility.getBinary(i);
		}
		return temp;
	}



	}


