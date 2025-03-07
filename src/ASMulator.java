import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ASMulator {

    public static void main(String[] args) {

        if(args.length != 1) { throw new IllegalArgumentException(); }

        String[] varNames = new String[0];
        int[] varValues = new int[0];

        BufferedReader br = null;

        try{

            br = new BufferedReader(new FileReader(args[0]));

            int numLines = Integer.parseInt(br.readLine());

            for(int i = 0; i < numLines; i++){

                String instruction = br.readLine();
                String[] instructionParts = instruction.split(" ");

                //add
                if(instructionParts[0].equals("add") && instructionParts.length == 3){

                    boolean find = false;
                    int index = -1;

                    for(int j = 0; j < varValues.length; j++){

                        if(varNames[j].equals(instructionParts[1])){

                            find = true;
                            index = j;
                            break;

                        }

                    }

                    if(find){

                        varValues[index] += Integer.parseInt(instructionParts[2]);

                    } else {

                        varNames = Arrays.copyOf(varNames, varNames.length + 1);
                        varNames[varNames.length - 1] = instructionParts[1];

                        varValues = Arrays.copyOf(varValues, varValues.length + 1);
                        varValues[varValues.length - 1] = Integer.parseInt(instructionParts[2]);

                    }

                }

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

}
