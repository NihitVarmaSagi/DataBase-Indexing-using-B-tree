import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Deleterow {
	
	public static int pagesize = 512;
	
	public static void deleterow(String usercommand) {
		try {
			String[] token = usercommand.split(" ");
			String table_name = token[2];
			String row_id;
			if(token[4].equals("row_id")) {
				row_id = token[6];
			}
			else {
				token = token[4].split("=");
				row_id = token[1];
			}
			String filename = "data/"+table_name.toLowerCase()+".tbl";
			File fileTemp = new File(filename);
			if(fileTemp.exists()) {
				nullvalue(table_name,row_id);
			}
			else {
				System.out.println("No such file exists");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void nullvalue(String table_name, String rowid) {
		try {
		String table_file = "data/"+table_name+".tbl";
		RandomAccessFile TableFile = new RandomAccessFile(table_file, "rw");
		File f = new File(table_file);
        long fileLength = f.length();
        int len = (int) fileLength;
        
        int no_of_pages = len/pagesize;
        for(int y=0;y<no_of_pages;y++) {
        	int page_start=y*512;
        	TableFile.seek(page_start);
           int type = TableFile.readByte();
           if(type == 13) {
        	   TableFile.seek(page_start+1);
        	   int row_count = TableFile.readByte();
        	   TableFile.seek(page_start+4);
        	   int[] array = new int[row_count];
        	   for(int i=0;i<row_count;i++) {
        		   array[i] = TableFile.readShort();
        		   
        	   }
        	  
        	   for(int j=0;j<array.length;j++) {
        		   if(array[j]!=1){
	        	       TableFile.seek(array[j]+5);
	        	       int rowidcheck = TableFile.readByte();
	        	       int rowidnumber = Integer.parseInt(rowid);
	        		   if(rowidcheck==rowidnumber) {
	        			   TableFile.seek(page_start+4+2*(j));
	        			   TableFile.writeShort(0x01);
	        			   System.out.println("Row deleted");
        		   }
        		   }
        	   }
           }
        }
	}
        catch(Exception e) {
        	e.printStackTrace();
        }
	}
}
