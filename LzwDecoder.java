/*author Ishita*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LzwDecoder {
	public static int bitLength ;

	public static void main(String[] args) {
		String inputFileName =" ";
		String encodedString = "";
		String output = "";
		List<Integer> numberList = new ArrayList<>();
		String outputFileName = "";

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
					System.out.println("Input file name is = " + " " +inputFileName +"\nInput bit length is" +" " + bitLength);
				}
				catch (NumberFormatException e) {
					System.out.println("Provide valid bit length as second parameter");
				}
			}

			//calling decoding logic and methods from Utility class
			encodedString=Utility.readInputFile(inputFileName);
			System.out.println("Binary value from file ="+encodedString);

			if (encodedString==null){
				System.out.println("Empty file!");
				return;
			}
			else{
				numberList = Utility.getIntegerValueFromBinary(encodedString);
				LzwDecoder lzwDecoder= new LzwDecoder();
				output = lzwDecoder.decodingLogic(numberList);
				System.out.println("Printing the Output for decoding ="+output);

				outputFileName = Utility.generateOutputFileName(inputFileName,"txt","_decoded");
				System.out.println("outputFileName" +" " + outputFileName);
				if(!outputFileName.equalsIgnoreCase("")){
					Utility.writeToFile(outputFileName,output);
				}
			}

	}

			//decoding logic
			public String decodingLogic(List<Integer> input){

				Integer max_table_size=(int)Math.pow(2,bitLength);
				HashMap<Integer, String> table=new HashMap<>();
				int currentSize=256;
				int code = 0;
				String string = null ;
				String output="";
				String newString = "";
				try{

					for(int i=0;i<currentSize;i++)
						table.put(i,String.valueOf((char)i));


					for(int i = 0;i<input.size();i++){

						code = input.get(i);

						if(i == 0){
							string = table.get(code);
							output = output+string;
						}else{
							if(table.containsKey(code))
								newString= table.get(code);
							else
								newString = string + string.charAt(0);
							output = output +newString;
							if(table.size()< max_table_size)
								table.put(currentSize++, string+newString.charAt(0));
							string = newString;
						}
					}
				}
				catch (Exception e) {
					System.out.println("Error in Decoding logic method");
				}

				return output;
			}
}
