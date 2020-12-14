import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private static int i =0 ;
    private static String d="";
    private static Dictionary word;
    
    public static void main(String[] args) {
    	
    	String time1 = LocalTime.now().toString();
    	
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
        freqOfWords.forEach((String fileName, Dictionary t) -> {
        	int count =0;
        	for (Enumeration i = t.elements(); i.hasMoreElements();) {
        		if((int)(i.nextElement()) != 0){
        			count++;
        		}
            } 
        	System.out.println(fileName+" has "+ count +" words comman \n"+t);
        	System.out.println("———————————————————————————————————————————————————————————————————————————————————————————————————————————————");
        });
        
        String time2 = LocalTime.now().toString();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1;
        Date date2;
        long difference = 0;
		try {
			date1 = format.parse(time1);
			date2 = format.parse(time2);
			difference = date2.getTime() - date1.getTime(); 
		} catch (ParseException e) {
		 //TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Time your code takes up -- "+ time1+ " seconds "+ time2);
    }
}