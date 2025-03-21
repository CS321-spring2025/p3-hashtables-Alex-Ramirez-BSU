# Project 3: Experiments With Hashing

* Author: Alex Ramirez
* Class: CS321
* Semester: Spring 2025

## Overview

The program will implement an array of HashObjects as a Hashtable using two different methods for hashing. One table will implement linear probing while the other will implement double hashing.
The aim of this program is to identify the differences in the hashing methods, explore several debugging levels, and implement various data sources while using the hashCode method.

## INCLUDED FILES:

A listing of the included files...

- DoubleHashing.java
- HashObject.java
- Hashtable.java
- HashtableExperiment.java
- LinearProbing.java
- TwinPrimeGenerator.java
- generate-results.sh (Do Not Modify)
- p3-rubric.txt (Do Not Modify)
- README.md
- run-tests.sh (Do Not Modify)

## Reflection

I have struggled greatly with this project. It looks like I initally may have overcomplicated things and ended up tangling myself in a mess.
Creating all the java files and their respective methods without having a framework already prepare was a struggle because I felt the details
where a bit too limited. I missed having several resources as to how to implement and visualize the project overall.

## Compiling and Using

The driver program will be the HashtableExperiment.java file which allows three command-line arguments with the third being the optional.
This first argument is <dataSource> which allows for three options: Integer (random numbers), Long (date value as a long), and String (word list).
The second argument is <loaFactor> which allows the user to set the desired ratio of object to table size.
The final and optional argument is [<debugLevel>] which allows for three variations of output: A summary of the experiement, two .txt files with the summary, and 
a debugging option which prints an output for each insert made.
After deciding upon these arguments an array of hashObjects will be made with their respective results demonstrating the size, number of elements, number of duplicated
elements, and the average number of probes taken by its respective hashing method.

## Results

|results-random.txt|
|--------------------------------------------------------------------------------	|
|HashtableExperiment: Found a twin prime table capacity: 95791				|
|HashtableExperiment: Input: Random   LoadFactor: 0.5					|
|											|
|	Using Linear Probing								|
|HashtableExperiment: size of hash table is 47896.0					|
|	Inserted 191582 elements, of which 95791 were duplicates			|
|	Avg. no. of probes = 0.00							|
|											|
|	Using Double Hashing								|
|HashtableExperiment: size of hash table is 47896.0					|
|	Inserted 191580 elements, of which 191580 were duplicates			|
|	Avg. no. of probes = 0.00							|
|--------------------------------------------------------------------------------	|

|results-date.txt|
|--------------------------------------------------------------------------------	|
|HashtableExperiment: Found a twin prime table capacity: 95791				|
|HashtableExperiment: Input: Long   LoadFactor: 0.5					|
|											|
|	Using Linear Probing								|
|HashtableExperiment: size of hash table is 47896.0					|
|	Inserted 95791 elements, of which 0 were duplicates				|
|	Avg. no. of probes = 0.00							|
|											|
|	Using Double Hashing								|
|HashtableExperiment: size of hash table is 47896.0					|
|	Inserted 95791 elements, of which 95791 were duplicates				|
|	Avg. no. of probes = 0.00							|
|--------------------------------------------------------------------------------	|

|results-word-list.txt|
|--------------------------------------------------------------------------------	|
|Table Capacity Is: 95791								|
|HashtableExperiment: Found a twin prime table capacity: 95791				|
|HashtableExperiment: Input: Word-List   LoadFactor: 0.5				|
|											|
|	Using Linear Probing								|
|HashtableExperiment: size of hash table is 47896.0					|
|	Inserted 48071 elements, of which 175 were duplicates				|
|	Avg. no. of probes = 0.00							|
|											|
|	Using Double Hashing								|
|HashtableExperiment: size of hash table is 47896.0					|
|	Inserted 48071 elements, of which 48071 were duplicates				|
|	Avg. no. of probes = 0.00							|
|--------------------------------------------------------------------------------	|



## Sources used

3 Day Extension.
