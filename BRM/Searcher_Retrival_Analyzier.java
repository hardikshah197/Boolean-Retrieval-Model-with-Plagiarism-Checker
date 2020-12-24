import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Plagiarism_Checker {

    private static final String FOLDER_PATH = "C:\\Users\\user\\Downloads\\eclipse-jee-mars-R-win32-x86_64\\eclipse\\files\\file\\";
    private static final String SEARCH_FILE_PATH = "C:\\Users\\user\\Downloads\\eclipse-jee-mars-R-win32-x86_64\\eclipse\\files\\searchFile\\search.txt";
    private static int i = 0 ;
    private static String d = "";
    private static int counter = 0;
    private static Dictionary word;
    private static String fn;
    
    public static void main(String[] args) {
    	
        Path folderPath = Paths.get(FOLDER_PATH);

        // prepare a data structure for a file's name and content
        Map<String, List<String>> linesOfFiles = new TreeMap<String, List<String>>();
        //create a data structure to store searcher words result cooresponding to files
        Map<String, Dictionary> freqOfWords = new TreeMap<String, Dictionary>();
        word = new Hashtable();

        // retrieve a list of the files in the folder
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
            for (Path path : directoryStream) {
                fileNames.add(path.toString());
            }
        } catch (IOException ex) {
            System.err.println("Error reading files");
            ex.printStackTrace();
        }
        	
        // go through the list of files
        for (String file : fileNames) {
            try {
                // put the file's name and its content into the data structure
                List<String> lines = Files.readAllLines(folderPath.resolve(file), StandardCharsets.ISO_8859_1);
                linesOfFiles.put(file, lines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        FileReader f2;
        BufferedReader f2s;
        List<String> st = new ArrayList<String>();
        
        //adding searchable words to a list
        try{
        	f2 = new FileReader(SEARCH_FILE_PATH);
			f2s = new BufferedReader(f2);
        	String temp;
        	while((temp = f2s.readLine()) != null){
        		st.add(temp);
        	}
        }
        catch(FileNotFoundException e1){
        	e1.printStackTrace();
        } catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
        
        // finally, searching the required words
        linesOfFiles.forEach((String fileName, List<String> lines) -> {
        	word = new Hashtable();
        	for(String temp: st){
        		d=temp.toLowerCase();
        		i=0;
	            lines.forEach((String line) -> {
	            	line = " " + line;
	            	line = line.toLowerCase();
	            	for(int k=0;k<line.length();k++){
	            		char ch=line.charAt(k);
	            		if((ch >=65 && ch <=90) || (ch >=97 && ch <= 122) || (ch >= 48 && ch <= 57));
	            		else{
	            			line.replace(ch, ' ');
	            		}
	            	}
	            	while(line.contains(" "+d+" ")){
	            		int index = line.indexOf(d) + d.length();
	        			line=line.substring(index);
	                	i++;
	                }
		        });
	            word.put(d, i);
        	}
//	        System.out.print(fileName+"   -- "+d+" -- "+i+" --- "+word+"\n");
//	        System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————");
           freqOfWords.put(fileName, word); 
        });
        int[] countNode = new int[word.size()];
        Arrays.fill( countNode, 0);
        i=0;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1;
        Date date2;
        float difference = 0;
        
        //Counting searching words along directory and printing the answer Simultaneously
        System.out.println(" File Name "+" 		"+" Word "+" 		"+" No. of Nodes "+" 		"+"Starting Time "+" 	"+" End Time "+" 		"+" Total Time");
        System.out.println("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
        for(String t: st){
        	d=t.toLowerCase();
        	float time1 = Float.parseFloat(LocalTime.now().toString().substring(6));
	        linesOfFiles.forEach((String filename, List<String> lines) -> {
	        	fn=filename.substring(filename.lastIndexOf('\\')+1);
	        	counter = 0;
	    		for(String line: lines) {
	    			line = " " + line + " ";
	            	line = line.toLowerCase();
	            	for(int k=0;k<line.length();k++){
	            		char ch=line.charAt(k);
	            		if((ch >=65 && ch <=90) || (ch >=97 && ch <= 122) || (ch >= 48 && ch <= 57));
	            		else{
	            			line.replace(ch, ' ');
	            		}
	            	}
	            	while(line.contains(" "+d+" ")){
	            		counter ++;
	            		break;
	                }
	    		};
	    		if(counter > 0){
	    			countNode[i] += 1;
	    		}
	    	});
	        float time2 = Float.parseFloat(LocalTime.now().toString().substring(6));
	        difference = (time2 - time1)*1000;
	        System.out.println(fn+" 		 "+d + " 		 "+ countNode[i]+"			"+ time1+ " 		 "+ time2+ " 		 "+difference);
	        i++;
    	}
        System.out.println("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
    }
}
