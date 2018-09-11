
public class SplashScreen{
/**
	 *  Display the welcome "splash screen"
	 */
		public static void main(String[] args) {
			splashScreen();
		}
		
		public static void splashScreen() {
			System.out.println(line("*",80));
			System.out.println("Welcome to DavisBaseLite"); // Display the string.
			version();
			System.out.println("Type \"help;\" to display supported commands.");
			System.out.println(line("*",80));
		}
	
		/**
		 * @param s The String to be repeated
		 * @param num The number of time to repeat String s.
		 * @return String A String object, which is the String s appended to itself num times.
		 */
		public static String line(String s,int num) {
			String a = "";
			for(int i=0;i<num;i++) {
				a += s;
			}
			return a;
		}
	
		/**
		 * @param num The number of newlines to be displayed to <b>stdout</b>
		 */
		public static void newline(int num) {
			for(int i=0;i<num;i++) {
				System.out.println();
			}
		}
	
		public static void version() {
			System.out.println("DavisBaseLite v1.0\n");
		}

}
