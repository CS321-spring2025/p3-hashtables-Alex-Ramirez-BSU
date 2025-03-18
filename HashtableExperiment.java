import java.util.Random;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HashtableExperiment {

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
		//System.out.println("\t\t\t\t Square brackets denote an optional argument.");
    }

	public static void printDebug(int n, String nameDataSource, int totalInserted, int totalDuplicates, double totalProbesLinear, double totalProbesDouble, int debugLevel, Hashtable linearHashtable, Hashtable doubleHashtable) {
		
		// Linear Probing statistics
		double avgProbesLinear = totalProbesLinear / totalInserted;
		System.out.println("\n\tUsing Linear Probing");
		System.out.println("HashtableExperiment: size of hash table is " + n);  // Assuming half the table is used based on load factor
		System.out.println("\tInserted " + totalInserted + " elements, of which " + totalDuplicates + " were duplicates");
		System.out.printf("\tAvg. no. of probes = %.2f\n", avgProbesLinear);
		if (debugLevel == 1) {
			System.out.println("HashtableExperiment: Saved dump of hash table");
			linearHashtable.dumpToFile("linear-dump.txt");
		}

		// Double Hashing statistics
		double avgProbesDouble = totalProbesDouble / totalInserted;
		System.out.println("\n\tUsing Double Hashing");
		System.out.println("HashtableExperiment: size of hash table is " + n);  // Assuming half the table is used based on load factor
		System.out.println("\tInserted " + totalInserted + " elements, of which " + totalDuplicates + " were duplicates");
		System.out.printf("\tAvg. no. of probes = %.2f\n", avgProbesDouble);
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
		int n = (int) Math.ceil(loadFactor * m);									//Number Of Elements To Insert (n)

		//Two Tables
		Hashtable linearHashtable = new LinearProbing(m, loadFactor);
		Hashtable doubleHashtable = new DoubleHashing(m, loadFactor);

		//Debug Stats
		int totalInserted = 0;
		int totalDuplicates = 0;
		double totalProbesLinear = 0;
		double totalProbesDouble = 0;


		//Printing User Input
		switch(dataSource) {
			case 1: nameDataSource = "Integer";
					Random random = new Random();
					for (int i = 0; i < n; i++) {
						int randomInteger = random.nextInt();
						HashObject hashObject = new HashObject(randomInteger);

						//Inserting Into Both Tables
						totalProbesLinear += linearHashtable.insert(hashObject);
						totalProbesDouble += doubleHashtable.insert(hashObject);

						if (hashObject.getFrequencyCount() > 1) {
							totalDuplicates++;
							if (debugLevel == 2) {
								System.out.println("Linear Probing: Duplicate insertion for key: " + randomInteger);
							}
						} else {
							if (debugLevel == 2) {
								System.out.println("Linear Probing: Successfully inserted key: " + randomInteger);
							}
						}

						//totalInserted++;
		
						if (hashObject.getFrequencyCount() > 1) {
							totalDuplicates++;
							if (debugLevel == 2) {
								System.out.println("Double Hashing: Duplicate insertion for key: " + randomInteger);
							}
						} else {
							if (debugLevel == 2) {
								System.out.println("Double Hashing: Successfully inserted key: " + randomInteger);
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

						//Inserting Into Both Tables
						totalProbesLinear += linearHashtable.insert(hashObject);
						totalProbesDouble += doubleHashtable.insert(hashObject);

						if (hashObject.getFrequencyCount() > 1) {
							totalDuplicates++;
							if (debugLevel == 2) {
								System.out.println("Linear Probing: Duplicate insertion for date: " + date);
							}
						} else {
							if (debugLevel == 2) {
								System.out.println("Linear Probing: Successfully inserted date: " + date);
							}
						}

						//totalInserted++;
		
						if (hashObject.getFrequencyCount() > 1) {
							totalDuplicates++;
							if (debugLevel == 2) {
								System.out.println("Double Hashing: Duplicate insertion for date: " + date);
							}
						} else {
							if (debugLevel == 2) {
								System.out.println("Double Hashing: Successfully inserted date: " + date);
							}
						}

						totalInserted++;
					}
					break;
			case 3: nameDataSource = "Word-List";
					String fileName = "word-list.txt";
					int wordsInserted = 0;

					try(Scanner scanner = new Scanner(new File(fileName))) {

						while (scanner.hasNextLine() && wordsInserted < n) {

							String word = scanner.nextLine().trim();
							HashObject hashObject = new HashObject(word);

							totalProbesLinear += linearHashtable.insert(hashObject);
							totalProbesDouble += doubleHashtable.insert(hashObject);

							if (hashObject.getFrequencyCount() > 1) {
								totalDuplicates++;
								if (debugLevel == 2) {
									System.out.println("Linear Probing: Duplicate insertion for word: " + word);
								}
							} else {
								if (debugLevel == 2) {
									System.out.println("Linear Probing: Successfully inserted word: " + word);
								}
							}

							//totalInserted++;
		
							if (hashObject.getFrequencyCount() > 1) {
								totalDuplicates++;
								if (debugLevel == 2) {
									System.out.println("Double Hashing: Duplicate insertion for word: " + word);
								}
							} else {
								if (debugLevel == 2) {
									System.out.println("Double Hashing: Successfully inserted word: " + word);
								}
							}

							wordsInserted++;
							totalInserted++;
						}
					} catch (FileNotFoundException e) {
						System.err.println("Error: Words List File Not Found.");;
						return;
					}
					
					
					break;
			default: nameDataSource = "Invalid Data Source";
					printUsage();
					return;
		}

		System.out.println("HashtableExperiment: Found a twin prime table capacity: " + m);
		System.out.println("HashtableExperiment: Input: " + nameDataSource + "   LoadFactor: " + loadFactor);

		printDebug(n, nameDataSource, totalInserted, totalDuplicates, totalProbesLinear, totalProbesDouble, debugLevel, linearHashtable, doubleHashtable);
    }
}
