## MS3 Challenge
<b></b>
<b></b>
<p align="center"> 
Java application that will consume a CSV file parse its data and insert into SQLite DB</p>

## Table of Contents
1. [Demo](https://github.com/rdelarosa3/ms3-challenge#demo)
2. [Technologies Applied](https://github.com/rdelarosa3/ms3-challenge#technologies)
3. [Setup Instruction](https://github.com/rdelarosa3/ms3-challenge#setup-instruction)
4. [Overview - Approach - Assumptions](https://github.com/rdelarosa3/ms3-challenge#overview)
### Demo
<img width="666" alt="Screen Shot 2021-02-19 at 3 43 18 AM" src="https://user-images.githubusercontent.com/40813295/108487478-edfcdd00-7264-11eb-9a87-60edf33bafb5.png">
<img width="572" alt="Screen Shot 2021-02-19 at 3 46 36 AM" src="https://user-images.githubusercontent.com/40813295/108487695-269cb680-7265-11eb-83d8-6afa11bbbd32.png">
                        
### Technologies
- SQLite JDBC [link](https://github.com/xerial/sqlite-jdbc)
- Open CSV [link](http://opencsv.sourceforge.net/)
- Maven [link](https://maven.apache.org/)
- Commons IO [link](https://commons.apache.org/proper/commons-io/)
- SQLite3 [link](https://www.sqlite.org/index.html)


### Setup Instruction
To run this project you will require SQLite3  

1. Clone this repo locally.
1. In Terminal cd to project root folder and run
```
$ mvn compile
$ mvn exec:java -Dexec.mainClass=com.ms3.app.App
``` 

### Interaction
After project starts type 1 to import csv and enter absolute file path to csv
````
What would you like to do?
1. Import CSV
2. Exit
Enter an option (1 through 2):
1
Enter your CSV file path: 
/Users/Projects/ms3_challenge/ms3Interview.csv

````
Once file is imported:
1. It will be parsed and create a directory with the file name.
1. A new SQLite DB will be created and stored in directory.
1. A csv file with failed imports will be stored in directory.
1. A log file will be created and stored in directory
````
$ ls
ms3interview-bad.csv    ms3interview.db         ms3interview.log
```` 
Process can be repeated until terminated by user.

### Overview

<details>
  <summary>Robert De laRosa <a href="https://github.com/rdelarosa3" target="_blank">GitHub</a></summary>

  1. Created this in more of a CLI to handle a single repetitive task.  
  2. Use of Maven to simplify build process and use of dependencies.

### Approach

1. Use of OpenCSV to use arrays to handle and loop through rows.
1. Use of JDBC to handle connection with SQLite DB
1. Use of Class object to handle csv rows as a representation of Table entity and attributes as representation of table columns.


### Assumptions

1. Will create a directory based on each csv file name and store db,csv,log
1. All CSV's will hold 10 columns with the exact data type as the ms3interview.csv columns.
1. Directory, Database and Table will be created based on file name.
1. Each database will have a table with columns named based on the header from csv file.
1. If column data type is not same as data type from original csv add to failed csv file.
1. Header columns are not counted as received record.
1. User can keep importing CSV or overwrite existing import. 
 
</details>
