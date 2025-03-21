import java.util.Random;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HashtableExperiment {

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
		System.out.println("HashtableExperiment: size of hash table is " + n);  // Assuming half the table is used based on load factor
		System.out.println("\tInserted " + linearHashtable.totalInserted() + " elements, of which " + linearHashtable.totalDuplicates() + " were duplicates");
		System.out.printf("\tAvg. no. of probes = %.2f\n", linearHashtable.getAverageProbes());
		if (debugLevel == 1) {
			System.out.println("HashtableExperiment: Saved dump of hash table");
			linearHashtable.dumpToFile("linear-dump.txt");
		}

		//Double Hashing Statistics
		System.out.println("\n\tUsing Double Hashing");
		System.out.println("HashtableExperiment: size of hash table is " + n);  // Assuming half the table is used based on load factor
		System.out.println("\tInserted " + doubleHashtable.totalInserted() + " elements, of which " + doubleHashtable.totalInserted() + " were duplicates");
		System.out.printf("\tAvg. no. of probes = %.2f\n", doubleHashtable.getAverageProbes());
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
		int m = TwinPrimeGenerator.generateTwinPrime(95500, 96000);			//Table Capacity (m)
		double n = (int) Math.ceil(loadFactor * m);									//Number Of Elements To Insert (n)

		//Two Tables
		linearHashtable = new LinearProbing(m, loadFactor);
		doubleHashtable = new DoubleHashing(m, loadFactor);

		//Debug Stats
		int totalInserted = 0;
		int totalDuplicates = 0;
		//double totalProbesLinear = 0;
		//double totalProbesDouble = 0;


		//User Input
		switch(dataSource) {
			case 1: nameDataSource = "Random";
					Random random = new Random();
					for (int i = 0; i < n; i++) {
						int randomInteger = random.nextInt();
						HashObject hashObject = new HashObject(randomInteger);
		
						// Inserting Into Both Tables
						if (hashObject.getFrequencyCount() == 1) { // Only count probes for new insertions
							linearHashtable.insert(hashObject);
							doubleHashtable.insert(hashObject);
							totalInserted++;
						} else {
							totalDuplicates++;
							if (debugLevel == 2) {
								System.out.println("Linear Probing: Duplicate insertion for key: " + randomInteger);
							}
						}
		
						if (debugLevel == 2) {
							if (hashObject.getFrequencyCount() == 1) {
								System.out.println("Linear Probing: Successfully inserted key: " + randomInteger);
							}
						}
		
						// Similar for Double Hashing
						if (hashObject.getFrequencyCount() == 1) {
							linearHashtable.insert(hashObject);
							doubleHashtable.insert(hashObject);
							totalInserted++;
						} else {
							totalDuplicates++;
							if (debugLevel == 2) {
								System.out.println("Double Hashing: Duplicate insertion for key: " + randomInteger);
							}
						}
		
						totalInserted++;
					}
					break;
			case 2: nameDataSource = "Long";
					long current = new Date().getTime();
					for (int i = 0; i < n; i++) {
						current += 1000;
						Date date = new Date(current);
						HashObject hashObject = new HashObject(date);
		
						if (hashObject.getFrequencyCount() == 1) { // Only count probes for new insertions
							linearHashtable.insert(hashObject);
							doubleHashtable.insert(hashObject);
							totalInserted++;
						} else {
							totalDuplicates++;
							if (debugLevel == 2) {
								System.out.println("Linear Probing: Duplicate insertion for date: " + date);
							}
						}
		
						if (debugLevel == 2) {
							if (hashObject.getFrequencyCount() == 1) {
								System.out.println("Linear Probing: Successfully inserted date: " + date);
							}
						}
		
						totalInserted++;
					}
					break;
			case 3: nameDataSource = "Word-List";
					String fileName = "word-list.txt";
					int wordsInserted = 0;
					System.out.println("N is: " + n);
					//System.out.println("Load Factor is: " + linearHashtable.loadFactor());

					try (Scanner scanner = new Scanner(new File(fileName))) {


						while (scanner.hasNextLine() && wordsInserted < m) {
							String word = scanner.nextLine().trim();
							HashObject hashObject = new HashObject(word);
		
								linearHashtable.insert(hashObject);
								doubleHashtable.insert(hashObject);
								wordsInserted++;
								//totalInserted++;
								//totalDuplicates++;
								if (debugLevel == 2) {
									System.out.println("Linear Probing: Duplicate insertion for word: " + word);
								}
		
							if (debugLevel == 2) {
									System.out.println("Linear Probing: Successfully inserted word: " + word);
							}

							//System.out.println("Load Factor?: " + linearHashtable.loadFactor());
							//System.out.println("Word # Inserted: " + wordsInserted);
		
							//totalInserted++;
						}
					} catch (FileNotFoundException e) {
						System.err.println("Error: Words List File Not Found.");
						return;
					}		
					break;
			default: nameDataSource = "Invalid Data Source";
					printUsage();
					return;
		}

		System.out.println("HashtableExperiment: Found a twin prime table capacity: " + m);
		System.out.println("HashtableExperiment: Input: " + nameDataSource + "   LoadFactor: " + loadFactor);

		printDebug(n ,nameDataSource, debugLevel);
    }
}
