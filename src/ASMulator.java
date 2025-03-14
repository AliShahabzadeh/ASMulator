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
        
        String[] labNames = new String[0];
        int[] labLines = new int[0];

        BufferedReader br = null;

        try{

            br = new BufferedReader(new FileReader(args[0]));

            int numLines = Integer.parseInt(br.readLine());

            for(int i = 0; i < 10; i++){

                String instruction = br.readLine();
                String[] instructionParts = instruction.split("\\s+");

                // add <var> <num>
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

                // sub <var> <num>
                else if(instructionParts[0].equals("sub") && instructionParts.length == 3){

                    for(int j = 0; j < varValues.length; j++){

                        if(varNames[j].equals(instructionParts[1])){

                            varValues[j] -= Integer.parseInt(instructionParts[2]);

                        }

                    }

                }

                // mul <var> <num>
                else if(instructionParts[0].equals("mul") && instructionParts.length == 3){

                    for(int j = 0; j < varValues.length; j++){

                        if(varNames[j].equals(instructionParts[1])){

                            varValues[j] *= Integer.parseInt(instructionParts[2]);

                        }

                    }

                }
                
                // lab <name>
                else if(instructionParts[0].equals("lab") && instructionParts.length == 2){
                    
                    labNames = Arrays.copyOf(labNames, labNames.length + 1);
                    labNames[labNames.length - 1] = instructionParts[1];

                    labLines = Arrays.copyOf(labLines, labLines.length + 1);
                    labLines[labLines.length - 1] = i;
                    
                }
                
                // jmp <var> <num> <name>
                else if(instructionParts[0].equals("jmp") && instructionParts.length == 4){
                    
                    for(int j = 0; j < varNames.length; j++){
                        
                        if(varNames[j].equals(instructionParts[1]) && varValues[j] == Integer.parseInt(instructionParts[2])){
                            
                            for(int k = 0; k < labNames.length; k++){
                                
                                if(labNames[k].equals(instructionParts[3])){
                                    
                                    i = labLines[k];
                                    
                                }
                                
                            }
                            
                        }
                        
                    }
                    
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        
        int temp = 0;
        
        for(int i = 0; i < varValues.length; i++)
            temp+=varValues[i];
        
        System.out.println(temp + "");

    }

}
