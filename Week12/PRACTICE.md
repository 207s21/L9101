# Exam Prep

Hi! This is a pretty short exercise that takes about 2-3 hours to complete. It's nowhere as 
intensive as an assignment but rather, it's designed to help prepare you
for the upcoming exam.  I've divided it into sections and each part will help you practice 
basic elements of what was taught in this class. Here's a small list of what you can expect
to practice...
1. ArrayLists/Arrays
2. FileIO
3. Regex (a little)
4. Design Patterns 
5. Javadocs
6. Classes/Interfaces/Abstract Classes
7. Data Types

**Note:** I added some time frames for each task. Try completing it by setting the timer for the limit
and try not to look at your notes too much. Study the material first and then try out the exercise. If
at any point you find yourself confused, that's alright. Take a breather and continue working until the
time ends. The time is meant to purposefully stress you out, and it'll prepare you for the real thing. If
you don't finish in the time, it's okay, keep working and finish the task but let the timer run, so you
know how much you went over. You can then determine areas you're not comfortable with. I know it sounds a
little excessive, but I understand how stressful an exam can be...and I want you all to do well :) Trust me
on this one ;)

Best of luck,
Ziyyad :)

##Section 0: Understanding the exercise
I thought it would be cool to make a program from scratch! We'll be making an application
that acts like Notes on your phone. We'll call it "My Mind Only" (totally not in any way related to 
Snapchat's My Eyes Only).The best part, you can lock the notes in three different methods. 
1. Binary Conversion
2. ASCII Inversion
3. ASCII Shift

If you want to unlock the note, you'd need to know the specific cipher that was used to lock
it. With this implementation, you can also stack the ciphers! For example, I 
can first lock the note by using ASCII Shift, then changing it to Binary! Pretty cool right?

###Structure
Here's how we can structure the program...actually...why don't you think about it for a bit.
Well first we know that there are at least three different *strategies* we can use to encrypt
and decrypt. So, lets use the strategy design pattern to create these ciphers. Now if we think 
about the program itself, we want to ask the user for input, and we would handle their data based
on the questions/options asked. It's always good practice to use MVC in this case, so we'll do that.

**Note**: this is meant to be a directed exercise, but you have free rein to change around the 
structure if you desire. I'll be putting some chunks of code here, and you can copy-paste them to
wherever you see fit.

##Section 1: Make a Notes program but forget about the ciphers
This first section will focus on making the program work by just acting as a regular, completely
unsafe note application.

###Task 1: Creating the files
So we know that we want to use MVC for this application. So, let's create these files and have
them empty for now. You should have three java files in total: MMOModel, MMOController, MMOView.

###Task 2: The Model
Recall that a model has all the backend info and holds that data in a specific manner. In our program,
we can say that the model will read from a file and hold that data. Based on what the controller asks,
it will use the different ciphers to decode the message and hand it to the controller. The same can be 
said about encoding. The raw data from the user will be passed into the model along with the type of cipher.
Then, it will write the encoded note to a text file. In essence, it shouldn't have any direct contact 
with the view.

####Task 2.1: WriteFile (< 10 mins)
First let's make a `writefile` method that returns true if we wrote successfully and false otherwise. It will 
act as a base function given a filename and data. We also need to ensure we handle the case if the file does 
not exist. Here's a hint. You can use the File class to check if the file exists. If it doesn't then we create 
it. If it already exists, then we can go ahead and start writing to a file. When we do write, the data should 
be on a single line. This makes our life easier! The input from the terminal will come in one line so each 
entry to this note is added in each line. For example: "blah blah blah" is part of the first line. If I add 
to the note, "blah2" will be on line 2. 

Try it out!

Hint 1: You can use a RandomAccessFile and open it in "rw" mode. 

Hint 2: You can use the seek method to navigate to the end of the file. This is ideally where you would
add to the file. Otherwise, you'd overwrite everything. 

Hint 3: USE TRY CATCH BLOCKS!!! They help keep things organized.

####Task 2.2: ReadFile (5 mins)
As it suggests, given a filename, we want to read the file. Create the method `readfile` that returns true if
the data has been successfully read. You can save the data to a variable of your choice. Later on, we can
try to decode it by using the different strategies.

Hint 1: Use a BufferedReader and a FileReader and handle the FileNotFoundException

####Task 2.3: DeleteFile (< 5 mins)
This one is actually pretty cheeky. We want a method `deletefile` to completely delete the file given a filename.
It should return true if the file has been successfully deleted and false otherwise.

Hint 1: Use the File class. If it exists then delete it using one of the methods. If not 
then return false. 

####Task 2.4: GetData (1 min)
This function will act as a getter function for the controller. As of now, it should return the data that is read.
Remember that we can have multiple lines of data, and you need to find some way of storing that(You should have
accounted for this in ReadFile).

###Task 3: The View
We can make the view so that we can interact with the program effectively. Because I'm super nice,
I'll give it to ya! The most basic version of it anyway... :)

    import java.util.Scanner;
    
    public class MMOView {
    private static final MMOController controller = new MMOController();
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    
        public static void main(String[] args) {
            System.out.println("Welcome to MyMindOnly!\nWhat would you like to do?\n");
            boolean stop = false;
            while(!stop){
                System.out.println(ANSI_YELLOW + "[1] Open a note \n[2] Create a note or add to an existing note \n[3] Delete a note \n[4] Quit" + ANSI_RESET);
                System.out.println("Enter your command");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine().trim();
                switch (input) {
                    case "4":
                        System.out.println(ANSI_RED + "Exiting..." + ANSI_RESET);
                        stop = true;
                        break;
                    case "1":
                        System.out.println(ANSI_YELLOW + "Enter the file name below" + ANSI_RESET);
                        String filename = scanner.nextLine().trim() + ".txt";
                        System.out.println(ANSI_CYAN + controller.read(filename) + "\n" + ANSI_RESET);
                        break;
                    case "2":
                        System.out.println(ANSI_YELLOW + "Enter the file name below" + ANSI_RESET);
                        filename = scanner.nextLine().trim() + ".txt";
                        System.out.println(ANSI_YELLOW + "\nEnter the data you would like to save to the file below" + ANSI_RESET);
                        String data = scanner.nextLine().trim() + "\n";
                        if (controller.write(filename, data)) System.out.println(ANSI_GREEN + "Data written successfully\n" + ANSI_RESET);
                        break;
                    case "3":
                        System.out.println(ANSI_RED + "Enter the file name below" + ANSI_RESET);
                        filename = scanner.nextLine().trim() + ".txt";
                        if (controller.delete(filename)){
                            System.out.println(ANSI_RED + "File successfully deleted\n" + ANSI_RESET);
                        }
                        else{
                            System.out.println(ANSI_RED + "File could not be deleted\n" + ANSI_RESET);
                        }
                        break;
                }
            }
        }
    }

Based on this, you can add more things later on ;)

###Task 4: The Controller
The controller in our case will connect the Model and View together. Here's something to remember: The Controller
should not know anything about the data (nothing important anyway). It should have some connection to the Model
and would need to have the basic functions from the view (which I provided). 

####Task 4.1: Complete the Controller (10 mins)
This should be very basic. As I stated before, the functions you need to complete are already hinted in the View. We
gave the user 4 initial options and 3 of those call on the Controller. Complete those functions. 

Note: Depending on how you stored the read data, you would need to format the data so it is readable to the user. Each
newline should contain an entry in the note. In the example from 2.1, "blah blah blah" should be the first line and 
"blah2" should be on line 2. There should NOT be any brackets or quotation marks. If there are, you can use a 
built-in method to _replaceAll_ of the occurances of something with something else (You'd have to use regex here). 

##CONGRATS!! At this point, you should have a program that runs and can create, read, write to, and delete notes. 

##Section 2: Adding ciphers!

###Task 1: File stuff (5-10 mins)
So you might be wondering..."how are we going to implement ciphers into this program?" Well, we can use a design 
patterns to add many ciphers. Ideally, we want to use something that allows us to create more ciphers later on
without too much trouble. Think about it...I'll give you some choices, and you can say why or why not the design
pattern would be useful. 

- Factory: (your reasoning here)
- Singleton: (your reasoning here) 
- Builder: (your reasoning here)
- Strategy: (your reasoning here)
- Visitor: (your reasoning here)

Regardless of whatever you choose, we are going to be making 3 different ciphers. Each of them will have an `encrypt`
and `decrypt` method. So we can make a Cipher interface or abstract class. What would be best in this situation?
Add your file and create the three types of ciphers:
1. Binary Conversion
2. ASCII Inversion
3. ASCII Shift

###Task 2: Complete the Binary Cipher
This cipher changes all characters to binary and can change them back from binary to regular characters. There are
a few ways you can do this in Java. I'd recommend you use the StringBuilder class to help construct your encoded
string. It's fairly easy to use. You just create an instance and append. You can also use the Integer class to convert
strings to binary. I'd also recommend printing the outputs just to get an idea of what you're working with. Better yet,
create a TestSuite! It helps with deciding on how you want to store your data. 

####Task 2.1: Create a TestSuite (10 mins)
As stated above, it helps out a lot when you implement your methods. Create about 3 tests for both `encrypt` and `decrypt`.
**Encrypt:** It doesn't need to be exactly like this...your format can be different to mine. But, the translation 
should be identical.

Test 1: 
- input: "Hello"
- output: "1101000 1100101 1101100 1101100 1101111"

Test 2: 
- input: "Hola friends!!!!!"
- output: "1001000 1101111 1101100 1100001  100000 1100110 1110010 1101001 1100101 1101110 1100100 1110011  100001  100001  100001  100001  100001"

You can make the other tests :)

**Decrypt:** You can just reverse the above test. You should also test encrypt before decrypt and compare their values to
see if the string is the same value as the original.

####Task 2.2: Complete the `encrypt` method (about 10-15 mins)
This function should take in a data type that holds our file's data. It should then change all the characters to 
binary. For ease, the letters are separated by a whitespace and every other character is turned to a binary 
representation. Even the whitespace and symbols!! We don't want to give away any information, so it'll all be 
encoded. I gave a small hint in the task 2 description. 

Note: I wouldn't worry too much if you go over the time limit. It uses some methods and classes that you've rarely 
used. However, I think it's fair to say at the 5 minute mark, you should have a clear idea on how you want to 
implement this function. At the 10 minute mark, you should have `encrypt` started. If you do that first, `decrypt`
will be a piece of cake. It'll be a similar strategy, just reverse. I'd also limit to a max of 2 loops. More than that
is excessive.

####Task 2.3: Complete `decrypt` (5 mins)
If you finished encrypt, then decrypt should be fairly easy. As stated above, it should basically be the reverse. 
You can even reuse the structure of the first function. 

####Task 2.4: Make adjustments to the program (10-15 mins)
Now that you have the Binary Cipher, you can add it to the program. The Model should have an instance of 
this strategy at the ready. Your View also needs to be modified to ask the user if they would like to encrypt 
using this cipher. An option with "No Cipher" should also be available. Be sure that you modify the Controller to take
in the choice. Since we only need a "yes/no" answer from the user, when they ask to encrypt/decrypt the note, you 
don't need to ask them for a specific key. 

##WOOHOO!!! You added the Binary Cipher!
From here on out, I won't give as much guidance. I'll just give an explanation of the cipher along with the steps
you should follow :)

###Task 3: Add the ASCII Shift Cipher
This cipher shifts each character by an integer. As you know, there are 128 different ASCII codes. In our case, if
the character is a letter (65-90 & 97-122), you shift the character by n characters. The area between 90-97 should
not be considered in this case. For ease, we won't worry about the symbols. If you want, you can totally handle that
situation based on how you see fit.
- If the character is a capital letter, we only shift within the range of 65-90
- If the character is a lowercase letter, we only shift within the range of 97-122
- If the shift leads the character out of bounds, it continues from the other boundary. E.g. shift "x" by 4 
    characters. The result should be "b". 
- The shift can be positive or negative. Positive goes down the alphabet a, b, c.... Negative goes up the 
    alphabet z, y, x...
  
Here are some examples:
- "HELP", n = 3, -> "KHOS"
- "helLOHello", n = 3, -> "khoORKhoor"

Here is a table with the different ASCII characters:
![alt text](http://www.asciitable.com/index/asciifull.gif)

####Task 3.1:Create a TestSuite (10 mins)

####Task 3.2: Complete the `encrypt` method (about 15-20 mins)

####Task 3.3: Complete `decrypt` (10 mins)

####Task 3.4: Make adjustments to the program (10-15 mins)
Here you'd need to ask the user for a "key". In this case, it will be the shift element.

###Task 4: Add the ASCII Inversion Cipher
This cipher inverts the character by swapping it with a character on the opposite side of the ascii range. We will
be using the same ranges in the previous cipher.
- If the character is a capital letter, we only invert within the range of 65-90
- If the character is a lowercase letter, we only invert within the range of 97-122

The alphabet consists of 26 characters. The mid-character is the 13th, "m/M". We will use this position as our 
reference point. PS. Since it's in the middle, there is no pair for it. So m/M will remain as is. When I say "pair"
I mean the inverted pairs. So (Z/z, A/a) are a pair and (Y/y, B/b) is also a pair. Please DO NOT hardcode this! Use
the ascii numbers to find the character's inverted match. We're also ignoring symbols for this cipher. 

Examples: You can use these for your test suite if you'd like
- invert: abcd
- inverted: zyxw

- invert: ziyyad
- inverted: aqbbzw

- invert: mmmmm
- inverted: mmmmm

Here is a table with the different ASCII characters:
![alt text](http://www.asciitable.com/index/asciifull.gif)

####Task 4.1:Create a TestSuite (10 mins)

####Task 4.2: Complete the `encrypt` method (about 10-15 mins)

####Task 4.3: Complete `decrypt` (5 mins)

####Task 4.4: Make adjustments to the program (10-15 mins)
You don't need to ask for a key for this cypher. 

# YOU. ARE. DONE!!!! q(≧▽≦q)
If you made it this far, congrats! I am very proud of you. I hope that this helped you gain some practical
experience and with the stress of being on a time constraint. Note that this is a pretty good exercise to help
prepare but it DOES NOT COVER EVERYTHING!! Be sure to look at your notes too! I also don't think I'll be posting
the solution to this because most would just not do the exercise at all. If you have questions, please do ask. You
can ask on Piazza or on Discord and I'll answer as soon as I get the chance. 