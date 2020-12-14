import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Plagiarism_Checker {

    private static final String FOLDER_PATH = "C:\\Users\\user\\Downloads\\eclipse-jee-mars-R-win32-x86_64\\eclipse\\files\\file\\";
    private static final String SEARCH_FILE_PATH = "C:\\Users\\user\\Downloads\\eclipse-jee-mars-R-win32-x86_64\\eclipse\\files\\searchFile\\search.txt";
    private static int i =0 ;
    private static String d="";
    private static Dictionary word;
    
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
                List<String> lines = Files.readAllLines(folderPath.resolve(file));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // finally, searching the required words
	        linesOfFiles.forEach((String fileName, List<String> lines) -> {
	        	word = new Hashtable();
	        	for(String temp: st){
            		d=temp.toLowerCase();
            		i=0;
		            lines.forEach((String line) -> {
		            	while(line.contains(d)){
		            		int index = line.indexOf(d) + d.length();
		        			line=line.substring(index);
		                	i++;
		                }
			        });
		            word.put(d, i);
	        	}
//	            System.out.print(fileName+"   -- "+d+" -- "+i+" --- "+word+"\n");
//	            System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————");
	           freqOfWords.put(fileName, word); 
	        });
//    	}
        freqOfWords.forEach((String fileName, Dictionary t) -> {
        	System.out.println(fileName+" has "+ t +" words comman");
        	System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————");
        });
        
    }
}