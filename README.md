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

Random Numbers
| Load Factor | Linear Probing Average # Of Probes | Double Hashing Average # Of Probes |
|-------------|------------------------------------| ----------------------------------	|
| 0.5 | 1.50 | 1.39 |
| 0.6 | 1.74 | 1.53 |
| 0.7 | 2.21 | 1.72 |
| 0.8 | 2.94 | 2.01 |
| 0.9 | 5.67 | 2.59 |
| 0.95 | 10.64 | 3.17 |
| 0.99 | 47.92 | 4.63 |

Dates
| Load Factor | Linear Probing Average # Of Probes | Double Hashing Average # Of Probes |
|-------------|------------------------------------| ----------------------------------	|
| 0.5 | 1.28 | 1.38 |
| 0.6 | 1.44 | 1.64 |
| 0.7 | 1.60 | 2.02 |
| 0.8 | 1.82 | 2.49 |
| 0.9 | 2.18 | 3.21 |
| 0.95 | 2.70 | 3.76 |
| 0.99 | 5.41 | 5.40 |

Word List
| Load Factor | Linear Probing Average # Of Probes | Double Hashing Average # Of Probes |
|-------------|------------------------------------| ----------------------------------	|
| 0.5 | 1.60 | 1.39 |
| 0.6 | 2.15 | 1.53 |
| 0.7 | 3.60 | 1.72 |
| 0.8 | 6.71 | 2.02 |
| 0.9 | 19.81 | 2.57 |
| 0.95 | 110.59 | 3.19 |
| 0.99 | 471.67 | 4.70 |





## Sources used

3 Day Extension.
