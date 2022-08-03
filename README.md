### Future Features

~~1. Allow the words to translate to also be in the foreign language. The solution to this is to use two arrays and two hashmaps and when a random word getter is called it randomly picks which word list and hashmap to choose from. To add to each it would be easy because I can just swap them around for the second hashmap.~~ 
 ~~Another way would be to change a bit more of the code and allow it to get a value as the word to translate and the key as the answer.~~ <br/>
~~2. Whenever the user gets a word right it will not show the word again. If a user gets the word wrong it adds it to the list twice. Correct words don't need practicing while wrong words should be practiced numerous times. I will need to change the code which checks if the word is contained already and also and some code to remove words from the dictionary and the array list words.~~ <br/> 
~~3. It may be interesting to have some sort of scoring system, due to adding extra words it would have to increase on correct answers but also decrease on incorrect answers, otherwise doing badly could mean you get a higher score as there will be more clones added to the dictionary.~~ <br/>
4. Create a database so that anything stored will stay there if possible. Also create a way of allowing the user to change items in the database, such as modifying or removing. Maybe with a tree table. <br/>
~~5. Allow 'enter' to control the submit button if possible.~~ <br/>
5. Style the GUI better and add some colour. <br/>
6. Add sound when an answer is correct if possible. <br/>
7. The entire GridPane changes in practice view when the text is longer which looks bad, need to separate the elements so that only the label gets bigger and the rest of the elements stay the same size. <br/>
8. An error message occurs when the user tries to open the practice view without inputting anything to the dictionary. It doesn't go away though when something is submitted to the dictionary because I'm not sure how to link the input view buttons with the language learner elements. <br/>
~~10. If the user inputs a phrase to the dictionary, it will still refer to it as a word which doesn't make sense. Need to change word to translation or remove it entirely where possible.~~  <br/>
9. Might be interesting to allow users to input their language, then they input words they want to practice and we use an API call to retrieve the translations from the appropriate dictionary.<br/>
~~12. When the button is pressed I want it to take the user back to the word textField instead of stay at the translate textField. I can't find anything in the documentation but I've posted on StackOverFlow.~~<br/>
~~10. In the future I want to create a UI to add and remove words but for now I'm going to implement reading and writing to different text files with an option to change which language to learn based on which text file.~~<br/>
~~10. Set the practice button to say "Retry" when the user is on the practice view and then change back to practice when on the input view.~~<br/>
~~11. New bug - when an item is incorrect, it's being added to the dictionary but with a blank value or key. Probably due to it being contained already, need to fix this.~~ <br/>
~~11. Adding buttons to choose language. Choosing language will change which file is being used to read and write to.~~ <br/>
~~11. Score is not resetting when swapping between different views.~~<br/>
~~12. Need to add words back to the array when pressing the retry button~~ <br/>
~~13. Need to make sure not getting a word right adds it twice.~~ <br/>
10. Need to set up automatic testing because manual testing is tedious <br/>
11. Need to implement a way of not displaying words that have been translated correctly multiple times. This is half implemented but needs to implemented for the long term. The user shouldn't have to remove words, the application should pseudo remove them if they're too easy. <br/>
12. Create a new hashmap with the word as key and an int count as the value. New words that are added to the dictionary will receive a value of 0. Whenever a word is correctly answered, the count will increase by 1. Whenever the game is played, the words that are populated from the dictionary into the playable list of words should be the words with the lowest value, i.e. the words that aren't known as well.


There is a list of the most used words in a language, I like the idea of adding words gradually based on their popularity, starting in batches of 20 from 1-20 then 20-40 etc. Doing this with an api and dictionary would be very interesting and allow a lot of autonomy for the user.<br/>
After spending a full day and a few hours last night on this project I'm happy with where it is at the moment. I do want to add some features and learn some more things about JavaFX but I also want to carry on with Java Mooc and get it finished first. For now the application works perfectly for my needs and I plan on using it each day. <br/>
