import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.SortedMap;

/**
 *
 * Below class has functions
 * to initialize database
 */

public class Catalog {
	static int pageSize = 512;
	
	public static void main(String[] args) {
		initializeDataStore();
	}
		static void initializeDataStore() {

			File directory = new File("data");

			 if(!directory.exists()){
			             directory.mkdir();
			 }

	        /** Create davisbase_tables system catalog */
	        try {
	            
	            String table_file = "data/davisbase_tables.tbl";
	            File fileTemp = new File(table_file);
		        if (!fileTemp.exists()){
		            RandomAccessFile davisbaseTablesCatalog= new RandomAccessFile(table_file, "rw");
		            
				/* Initialize the file size to be zero */
				   davisbaseTablesCatalog.setLength(0);            // Initialize the file size to be zero (in case it already exists)
				   davisbaseTablesCatalog.setLength(pageSize);
				   davisbaseTablesCatalog.writeByte(0x0D);  
				   davisbaseTablesCatalog.writeByte(02);
				   davisbaseTablesCatalog.writeShort(0x01CF);       // offset address of start of content area
					/* The array of cell locations */
				   davisbaseTablesCatalog.writeShort(0x01E8);   // offset address of record rowid=1
				   davisbaseTablesCatalog.writeShort(0x01CF);   // offset address of record rowid=2
					
	
					/* Record rowid=1 at offset 0x01E8 */
				   davisbaseTablesCatalog.seek(0x1E8);			 // Offset address to begin writing record rowid=1
				   davisbaseTablesCatalog.writeShort(17);           // Size of payload
				   davisbaseTablesCatalog.writeInt(1);              // rowid=1 (is also column_1)
				   davisbaseTablesCatalog.writeByte(1);             // number of columns in addition to rowid column_1
				   davisbaseTablesCatalog.writeByte(28);            			// column_1 data type byte STRING 12B + 16 ASCII characters
				   davisbaseTablesCatalog.writeBytes("davisbase_tables");   // column_1 value
				   
				   /* Record rowid=2 at offset 0x01CF */
				   davisbaseTablesCatalog.seek(0x1CF);			 // Offset address to begin writing record rowid=1
				   davisbaseTablesCatalog.writeShort(18);           // Size of payload
				   davisbaseTablesCatalog.writeInt(2);              // rowid=2 (is also column_1)
				   davisbaseTablesCatalog.writeByte(1);             // number of columns in addition to rowid column_1
				   davisbaseTablesCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseTablesCatalog.writeBytes("davisbase_columns");   // column_1 value
				   davisbaseTablesCatalog.close();
		
		        }
	        }
	        
		catch (Exception e) {
			System.out.println("Unable to open " + "davisbase_tables");
		}
	        
	     try {
	            
	            String column_file = "data/davisbase_columns.tbl";
	            File fileTemp = new File(column_file);
		        if (!fileTemp.exists()){
		        	RandomAccessFile davisbaseColumnsCatalog= new RandomAccessFile(column_file, "rw");
	           
				/* Initialize the file size to be zero */
				   davisbaseColumnsCatalog.setLength(0);            // Initialize the file size to be zero (in case it already exists)
				   davisbaseColumnsCatalog.setLength(pageSize);
				   davisbaseColumnsCatalog.writeByte(0x0D);  
				   davisbaseColumnsCatalog.writeByte(9);
				   davisbaseColumnsCatalog.writeShort(0x2E);       // offset address of start of content area
					/* The array of cell locations */
				   davisbaseColumnsCatalog.writeShort(0x01D3);   // offset address of record rowid=1
				   davisbaseColumnsCatalog.writeShort(0x01A0);	// offset address of record rowid=2
				   davisbaseColumnsCatalog.writeShort(0x0172);	// offset address of record rowid=3
				   davisbaseColumnsCatalog.writeShort(0x013E);   // offset address of record rowid=4
				   davisbaseColumnsCatalog.writeShort(0x0109);   // offset address of record rowid=5
				   davisbaseColumnsCatalog.writeShort(0xD4);   // offset address of record rowid=6
				   davisbaseColumnsCatalog.writeShort(0xA1);   // offset address of record rowid=7
				   davisbaseColumnsCatalog.writeShort(0x63);   // offset address of record rowid=8
				   davisbaseColumnsCatalog.writeShort(0x2E);   // offset address of record rowid=9
	
					/* Record rowid=1 at offset 0x01D3 */
				   davisbaseColumnsCatalog.seek(0x1D3);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(38);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(1);              			// rowid=1 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(28);            			// column_1 data type byte STRING 12B + 16 ASCII characters
				   davisbaseColumnsCatalog.writeByte(18);						// column_2 data type byte STRING 12B + 6 ASCII characters
				   davisbaseColumnsCatalog.writeByte(15);						// column_3 data type byte STRING 12B + 3 ASCII characters
				   davisbaseColumnsCatalog.writeByte(15);						// column_4 data type byte STRING 12B + 3 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_tables");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("row_id"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("PRI"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("INT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(1);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");					// column_6 value
				   
				   
				   /* Record rowid=2 at offset 0x01A0 */
				   davisbaseColumnsCatalog.seek(0x1A0);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(44);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(2);              			// rowid=2 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(28);            			// column_1 data type byte STRING 12B + 16 ASCII characters
				   davisbaseColumnsCatalog.writeByte(22);						// column_2 data type byte STRING 12B + 10 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_3 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_4 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_tables");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("table_name"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("NULL"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("TEXT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(2);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");					// column_6 value
				   
				   /* Record rowid=3 at offset 0x0172 */
				   davisbaseColumnsCatalog.seek(0x172);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(39);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(3);              			// rowid=3 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseColumnsCatalog.writeByte(18);						// column_2 data type byte STRING 12B + 6 ASCII characters
				   davisbaseColumnsCatalog.writeByte(15);						// column_3 data type byte STRING 12B + 3 ASCII characters
				   davisbaseColumnsCatalog.writeByte(15);						// column_4 data type byte STRING 12B + 3 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_columns");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("row_id"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("PRI"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("INT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(1);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");					// column_6 value
				   
				   
				   /* Record rowid=4 at offset 0x013E */
				   davisbaseColumnsCatalog.seek(0x13E);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(45);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(4);              			// rowid=3 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseColumnsCatalog.writeByte(22);						// column_2 data type byte STRING 12B + 10 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_3 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_4 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_columns");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("table_name"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("NULL"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("TEXT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(2);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");					// column_6 value
				   
				   
				   /* Record rowid=5 at offset 0x0109 */
				   davisbaseColumnsCatalog.seek(0x109);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(46);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(5);              			// rowid=3 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseColumnsCatalog.writeByte(23);						// column_2 data type byte STRING 12B + 11 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_3 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_4 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_columns");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("column_name"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("NULL"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("TEXT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(3);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");					// column_6 value
				   
				   /*/Record rowid=6 at offset 0xD4 */
				   davisbaseColumnsCatalog.seek(0xD4);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(46);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(6);              			// rowid=3 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseColumnsCatalog.writeByte(22);						// column_2 data type byte STRING 12B + 10 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_3 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_4 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(15);						// column_6 data type byte STRING 12B + 3 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_columns");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("column_key"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("NULL"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("TEXT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(4);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("YES");					// column_6 value
				  
				   
				   /* Record rowid=7 at offset 0xA1 */
				   davisbaseColumnsCatalog.seek(0xA1);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(44);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(7);              			// rowid=3 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseColumnsCatalog.writeByte(21);						// column_2 data type byte STRING 12B + 9 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_3 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_4 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_columns");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("data_type"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("NULL"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("TEXT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(5);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");					// column_6 value
				   
				
				   /* Record rowid=8 at offset 0x63 */
				   davisbaseColumnsCatalog.seek(0x63);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(55);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(8);              			// rowid=3 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseColumnsCatalog.writeByte(28);						// column_2 data type byte STRING 12B + 16 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_3 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(20);						// column_4 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_columns");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("ordinal_position"); 		// column_2 value
				   davisbaseColumnsCatalog.writeBytes("NULL"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("SMALLINT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(6);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");
				   
				   /* Record rowid=9 at offset 0x2E */
				   davisbaseColumnsCatalog.seek(0x2E);			 				// Offset address to begin writing record rowid=1
				   davisbaseColumnsCatalog.writeShort(46);           			// Size of payload
				   davisbaseColumnsCatalog.writeInt(9);              			// rowid=3 (is also column_1)
				   davisbaseColumnsCatalog.writeByte(6);             			// number of columns in addition to rowid column_1
				   davisbaseColumnsCatalog.writeByte(29);            			// column_1 data type byte STRING 12B + 17 ASCII characters
				   davisbaseColumnsCatalog.writeByte(23);						// column_2 data type byte STRING 12B + 11 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_3 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(16);						// column_4 data type byte STRING 12B + 4 ASCII characters
				   davisbaseColumnsCatalog.writeByte(5);						// column_5 data type SMALL INT
				   davisbaseColumnsCatalog.writeByte(14);						// column_6 data type byte STRING 12B + 2 ASCII characters
				   davisbaseColumnsCatalog.writeBytes("davisbase_columns");      // column_1 value
				   davisbaseColumnsCatalog.writeBytes("is_nullable"); 			    // column_2 value
				   davisbaseColumnsCatalog.writeBytes("NULL"); 				    // column_3 value
				   davisbaseColumnsCatalog.writeBytes("TEXT"); 				   // column_4 value
				   davisbaseColumnsCatalog.writeShort(7);						// column_5 value
				   davisbaseColumnsCatalog.writeBytes("NO");					// column_6 value
				   
				   davisbaseColumnsCatalog.close();
		     }
	     }
	     catch (Exception e) {
				System.out.println("Unable to open " + "davisbase_columns");
			}
	    
		}
		
}