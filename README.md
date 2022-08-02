### Future Features

1. Allow the words to translate to also be in the foreign language. The solution to this is to use two arrays and two hashmaps and when a random word getter is called it randomly picks which word list and hashmap to choose from. To add to each it would be easy because I can just swap them around for the second hashmap.
 Another way would be to change a bit more of the code and allow it to get a value as the word to translate and the key as the answer. 
2. Whenever the user gets a word right it will not show the word again. If a user gets the word wrong it adds it to the list twice. Correct words don't need practicing while wrong words should be practiced numerous times. I will need to change the code which checks if the word is contained already and also and some code to remove words from the dictionary and the array list words. 
3. It may be interesting to have some sort of scoring system, due to adding extra words it would have to increase on correct answers but also decrease on incorrect answers, otherwise doing badly could mean you get a higher score as there will be more clones added to the dictionary. 
4. Create a database so that anything stored will stay there if possible. Also create a way of allowing the user to change items in the database, such as modifying or removing. Maybe with a tree table. 
5. Allow 'enter' to control the submit button if possible. 
6. Style the GUI better and add some colour. 
7. Add sound when an answer is correct if possible. 
8. The entire GridPane changes in practice view when the text is longer which looks bad, need to separate the elements so that only the label gets bigger and the rest of the elements stay the same size. 
9. An error message occurs when the user tries to open the practice view without inputting anything to the dictionary. It doesn't go away though when something is submitted to the dictionary because I'm not sure how to link the input view buttons with the language learner elements. 
10. If the user inputs a phrase to the dictionary, it will still refer to it as a word which doesn't make sense. Need to change word to translation or remove it entirely where possible.  
11. Might be interesting to allow users to input their language, then they input words they want to practice and we use an API call to retrieve the translations from the appropriate dictionary.
12. When the button is pressed I want it to take the user back to the word textField instead of stay at the translate textField.