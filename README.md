## MS3 Challenge
<b></b>
<b></b>
<p align="center"> 
Java application that will consume a CSV file parse its data and insert into SQLite DB</p>

## Table of Contents
1. [Demo](https://github.com/rdelarosa3/ms3-challenge#demo)
2. [Technologies Applied](https://github.com/rdelarosa3/ms3-challenge#technologies)
3. [Setup Instruction](https://github.com/rdelarosa3/ms3-challenge#setup-instruction)
4. [Contribution](https://github.com/rdelarosa3/ms3-challenge#contribution)
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

### Contribution

<details>
  <summary>Robert De laRosa <a href="https://github.com/rdelarosa3" target="_blank">GitHub</a></summary>

  1.  
  2. 
  3.  
  4. 
  5. 
</details>
