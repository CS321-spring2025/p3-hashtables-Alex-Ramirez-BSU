import java.util.Random;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;

public class HashtableExperiment {

	//Declaring Both Hashtables
	private static Hashtable linearHashtable;
	private static Hashtable doubleHashtable;

    public static void printUsage() {

        System.out.print("\n");
		System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
		System.out.println("\t<dataSource>:\t 1 ==> random numbers");
		System.out.println("\t\t\t 2 ==> date value as a long");
		System.out.println("\t\t\t 3 ==> word list");
		System.out.println("\t<loadFactor>:\t The ratio of objects to table size,");
		System.out.println("\t\t\t     denoted by alpha = n/m");
        System.out.println("\t[<debugLevel>]:\t 0 ==> print summary of experiment");
		System.out.println("\t\t\t 1 ==> save the two hash tables to a file at the end");
		System.out.println("\t\t\t 2 ==> print debugging output for each insert");
	}

	public static void printDebug(double n, String nameDataSource, int debugLevel) {
		
		//Linear Probing Statistics
		System.out.println("\n\tUsing Linear Probing");
		System.out.println("HashtableExperiment: size of hash table is " + (int)n);
		System.out.println("\tInserted " + linearHashtable.totalInserted() + " elements, of which " + linearHashtable.totalDuplicates() + " were duplicates");
		System.out.printf("\tAvg. no. of probes = %.2f\n", (double)linearHashtable.getTotalProbes()/n);
		if (debugLevel == 1) {
			System.out.println("HashtableExperiment: Saved dump of hash table");
			linearHashtable.dumpToFile("linear-dump.txt");
		}

		//Double Hashing Statistics
		System.out.println("\n\tUsing Double Hashing");
		System.out.println("HashtableExperiment: size of hash table is " + (int)n);
		System.out.println("\tInserted " + doubleHashtable.totalInserted() + " elements, of which " + doubleHashtable.totalDuplicates() + " were duplicates");
		System.out.printf("\tAvg. no. of probes = %.2f\n", (double)doubleHashtable.getTotalProbes()/n);
		if (debugLevel == 1) {
			System.out.println("HashtableExperiment: Saved dump of hash table");
			doubleHashtable.dumpToFile("double-dump.txt");
		}
	}

    public static void main(String[] args) {
		
		//Declaring User-Input Variables
        int dataSource;
		String nameDataSource;
        double loadFactor;
        int debugLevel = 0;		//Default
		
        //Getting User Input From Command Line
		if (args.length <= 1) {
			printUsage();
			return;
		} else if (args.length < 4) {
			dataSource = Integer.parseInt(args[0]);
			loadFactor = Double.parseDouble(args[1]);
			
            if (args.length == 3) {
				debugLevel = Integer.parseInt(args[2]);
            }
		} else {
			printUsage();
			return;
		}

		//Twin Prime Table Capacity is 95791
		int tableCapacity = TwinPrimeGenerator.generateTwinPrime(95500, 96000);				//Table Capacity (m)
		double numberToInsert = (int) Math.ceil(loadFactor * tableCapacity);						//Number Of Elements To Insert (n)
		
		//Two Tables
		linearHashtable = new LinearProbing(tableCapacity, loadFactor);
		doubleHashtable = new DoubleHashing(tableCapacity, loadFactor);
		
		//Debug Stats
		int totalInserted = 0;
		
		//User Input
		switch(dataSource) {
			case 1: nameDataSource = "Random";
					Random random = new Random();

					while ((totalInserted - linearHashtable.totalDuplicates()) < numberToInsert) {
						int randomInteger = random.nextInt();
						HashObject linearHashObject = new HashObject(randomInteger);
						HashObject doubleHashObject = new HashObject(randomInteger);

						int linearDuplicate = linearHashtable.totalDuplicates();
						int doubleDuplicate = doubleHashtable.totalDuplicates();

						linearHashtable.insert(linearHashObject);
						doubleHashtable.insert(doubleHashObject);
						totalInserted++;

						if (debugLevel == 2) {

							if (linearHashtable.totalDuplicates() > linearDuplicate) {
								System.out.println("Linear Probing: Duplicate insertion for random: " + randomInteger );
							} else {
								System.out.println("Linear Probing: Successfully inserted random: " + randomInteger);
							}

							if (doubleHashtable.totalDuplicates() > doubleDuplicate) {
								System.out.println("Double Hashing: Duplicate insertion for random: " + randomInteger );
							} else {
								System.out.println("Double Hashing: Successfully inserted random: " + randomInteger);
							}
						}
					}
					break;
					
			case 2: nameDataSource = "Long";
					long current = new Date().getTime();

					while ((totalInserted - linearHashtable.totalDuplicates()) < numberToInsert) {
						
						current += 1000;
						Date date = new Date(current);
						HashObject linearHashObject = new HashObject(date);
						HashObject doubleHashObject = new HashObject(date);

						int linearDuplicate = linearHashtable.totalDuplicates();
						int doubleDuplicate = doubleHashtable.totalDuplicates();
		
						linearHashtable.insert(linearHashObject);
						doubleHashtable.insert(doubleHashObject);
						totalInserted++;

						if (debugLevel == 2) {

							if (linearHashtable.totalDuplicates() > linearDuplicate) {
								System.out.println("Linear Probing: Duplicate insertion for Date: " + current );
							} else {
								System.out.println("Linear Probing: Successfully inserted Date: " + current);
							}

							if (doubleHashtable.totalDuplicates() > doubleDuplicate) {
								System.out.println("Double Hashing: Duplicate insertion for Date: " + current );
							} else {
								System.out.println("Double Hashing: Successfully inserted Date: " + current);
							}
						}
					}
					break;

			case 3: nameDataSource = "Word-List";
					String fileName = "word-list.txt";
					int wordsInserted = 0;
	
					try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {

						String line = buffer.readLine();
				
						while (line != null && (wordsInserted - linearHashtable.totalDuplicates()) < numberToInsert) {
						
							HashObject linearHashObject = new HashObject(line);
							HashObject doubleHashObject = new HashObject(line);
				
							int linearDuplicate = linearHashtable.totalDuplicates();
							int doubleDuplicate = doubleHashtable.totalDuplicates();

							linearHashtable.insert(linearHashObject);
							doubleHashtable.insert(doubleHashObject);


							wordsInserted++;
				
							if (debugLevel == 2) {

								if (linearHashtable.totalDuplicates() > linearDuplicate) {
									System.out.println("Linear Probing: Duplicate insertion for Word: " + line);
								} else {
									System.out.println("Linear Probing: Successfully inserted Word: " + line);
								}

								if (doubleHashtable.totalDuplicates() > doubleDuplicate) {
									System.out.println("Double Hashing: Duplicate insertion for Word: " + line);
								} else {
									System.out.println("Double Hashing: Successfully inserted Word: " + line);
								}
							}

							line = buffer.readLine();
						}
				
					} catch (Exception e) {
						System.err.println("Error: Words List File Not Found.");
						return;
					}
					break;

			default: nameDataSource = "Invalid Data Source";
					printUsage();
					return;
		}

		System.out.println("HashtableExperiment: Found a twin prime table capacity: " + tableCapacity);
		System.out.println("HashtableExperiment: Input: " + nameDataSource + "   LoadFactor: " + loadFactor);

		printDebug(numberToInsert, nameDataSource, debugLevel);
    }
}
