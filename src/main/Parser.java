
import java.io.*;
import java.util.*;

public class Parser extends LaboratoryTestResult {
    private String file_path;
    
    
    Parser(String file_path){
       super();
       this.file_path = file_path;
    }
    
    
    public static void mapped_results(String file_path) throws FileNotFoundException{
        
    
        HashMap<String, Object> hm = new HashMap<>();
        
        File file = new File(file_path);
        Scanner sc = new Scanner(file);
        
        while(sc.hasNext()){
            String fileCount="";
          fileCount = fileCount.concat(sc.nextLine());
          String[] mapped = fileCount.split("\\|");
          
          
          if(mapped[0].equals("OBX")){
                hm = calculate(mapped);
          }
          
          if(mapped[0].equals("NTE")){
              hm.put("comment", mapped[2]);         //comment
             // System.out.println(hm);
              LaboratoryTestResult ltr = new LaboratoryTestResult(hm);          //ltr object contains HashMap hm
             
          }
          
        }
        
          
    }
        
      public static HashMap<String, Object> calculate(String[] mapped){
           HashMap<String, Object> res = new HashMap<>(); 
           
            res.put("code" , mapped[2]);                        //code
            
            
             if(mapped[3].equals("NEGATIVE")){                 //result
                  res.put("result" , -1.0);
              }
              else if(mapped[3].equals("POSITIVE")){
                  res.put("result" , -2.0);
              }
              else if(mapped[3].equals("NIL")){
                  res.put("result" , -1.0);
              }
              else if(mapped[3].equals("+")){
                  res.put("result" , -2.0);
              }
              else if(mapped[3].equals("++")){
                  res.put("result" , -2.0);
              }
              else if(mapped[3].equals("+++")){
                  res.put("result" , -3.0);
              }
             else if(mapped[3].equals("20")){
                 res.put("result" , 20.0);
             }
             else {
                 res.put("result" , mapped[3]);
             }
             
             
             if(mapped[2].equals("C100")){                      //format
                 res.put("format" , "float");
             }
             else if(mapped[2].equals("C200")){
                 res.put("format" , "float");
             }
             else if(mapped[2].equals("A250")){
                 res.put("format" , "boolean");
             }
            else {
                 res.put("format" , "nil_3plus");
             }
            
            return res;
        }
        

    
    public static void main(String args[]) throws FileNotFoundException {
        
        Parser parser = new Parser("src/main/lab1.txt");
        parser.mapped_results(parser.file_path);
      
    }
}

class LaboratoryTestResult{
    protected String code;
    protected float result;
    protected String format;
    protected String comment;

    
    LaboratoryTestResult(HashMap<String, Object> res){
        System.out.println(res);
    }
    
    LaboratoryTestResult(){
    
    }
}