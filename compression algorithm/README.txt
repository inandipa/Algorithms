
Details:
Name - IndraKiranReddy Nandipati
ID - 800937635
Email-id - inandipa@uncc.edu

Programming :
programming is done in java language. java version is "1.8.0_73".

Files Description:

Project consists of 2 main source code 
 1) LZW_encoding.java
 2) LZW_decoding.java
and one class (Hashtable_findbyvalue.java) which is called by LZW_encoding.java for searching Hashmap  

Executution steps :
change directory to the file that has these three class and also place any TEXT file for checking and compile by - 
Dir/algos_project> javac LZW_encoding.java
Dir/algos_project> javac LZW_decoding.java

Run the code using command - 
Dir/algos_project> java LZW_encoding input.txt 12
Dir/algos_project> java LZW_encoding input.lzw 12

Data Structure used :
Hash Maps are used for storing Strings into table.

Program Design :

Both for Encoding and Decoding -
Varibles are intilized by taking inputs from arguments passed while running.
a for loop is used to store strings converted from char of ASCII values from 0 to 255.
Now every character is read from file, character after character and its ASCII value is calulated.

Encoding design - 
If it is present in table add next character from file to string and so on..
Otherwise store the string which is not present in table to it untill table size reaches maximum and send string to file with out adding new character.
continue above process untill all characters is read.

Decoding design - 
Fetch the binaray UTF-16BE code byte by byte from input.lzw file.
Convert binary to decimal and check wether if decimal is less than table legth print the corresponding string to string.
Otherwise concat string to it first character and store in table and print this new string to file.
Continue above process untill all data is read from lzw file

Comments - 
Code of both encoding and decoding works as per sudo code without any deviation from expected out-come.
LZW file fromate is done to UTF-16BE. it exactly matches with sample lzw files.
The only drawback of the code is that, program reads character by character from input file. this increases the running time for huge file drastically.
Anyway we get output as expected but it takes long time. Please be patience while running program over large files.
