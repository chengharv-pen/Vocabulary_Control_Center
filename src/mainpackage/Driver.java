package mainpackage;

import linkedlists.*;
import vocabpackage.*;

import java.util.Scanner;

import java.util.ArrayList; //This is the only Collection import allowed for this assignment
import java.util.Collections;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;

/**
 * 
 * This program allows the user to browse the menu of a Vocabulary Control Center, by inputting
 * an integer as the choice.
 * 
 * It is recommended to first load a .txt file, using option 7, so that all the other 
 * menu options work properly.
 * 
 * The user may:
 * Browse a topic to print out every word inside that topic, through the option 1.
 * Insert a new topic before another one, through the option 2.
 * Insert a new topic after another one, through the option 3.
 * Remove a topic, through the option 4. 
 * Enter, remove or change vocabulary words for a topic, through the option 5.
 * Search topics for a word, through the option 6.
 * Show all words starting with a given letter, through the option 8.
 * Save the current words and topics to any file, through the option 9.
 * 
 */
public class Driver {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		boolean check = false;
		ArrayList<String> extractedArray = new ArrayList<String>();
		SinglyLinkedList<String> wordLinkedList = new SinglyLinkedList<String>();
		DoublyLinkedList<Vocab> vocabLinkedList = new DoublyLinkedList<Vocab>();

		while (!check) {
			int choice = 0;
			displayMenu();
			choice = input.nextInt();

			switch (choice) {
			case 1:
				browseTopic(vocabLinkedList);
				break;
			case 2:
				insertTopicBefore(wordLinkedList, vocabLinkedList);
				break;
			case 3:
				insertTopicAfter(wordLinkedList, vocabLinkedList);
				break;
			case 4:
				removeTopic(wordLinkedList, vocabLinkedList);
				break;
			case 5:
				modifyTopic(wordLinkedList, vocabLinkedList);
				break;
			case 6:
				searchTopic(vocabLinkedList);
				break;
			case 7:
				loadFromFile(wordLinkedList, vocabLinkedList);
				break;
			case 8:
				showLetterWords(wordLinkedList, extractedArray);
				break;
			case 9:
				saveToFile(vocabLinkedList);
				break;
			default:
				check = true;
				break;
			}
		}

		System.out.print("Goodbye!");
		input.close();

	}
	/**
	 * This method prints the main menu for the Driver class
	 */
	public static void displayMenu() {
		System.out.println("===========================\n" + 
					       " Vocabulary Control Center\n"  + 
					       "===========================\n" + 
					       "1 - browse a topic\n" + 
					       "2 - insert a new topic before another one\n" + 
					       "3 - insert a new topic after another one\n" + 
					       "4 - remove a topic\n" + "5 - modify a topic\n" + 
					       "6 - search topics for a word\n" + 
					       "7 - load from a file\n" + 
					       "8 - show all words starting with a given letter\n" + 
					       "9 - save to file\n" + 
					       "0 - exit\n" + 
					       "===========================\n");
		System.out.print("Enter Your Choice: ");
	}
	
	/**
	 * This method allows the user to browse a topic, using integer inputs.
	 * If the user inputs 0, then the method will end and we will go back to the
	 * main menu.
	 * 
	 * @see printTopics
	 * @see returnVocabList
	 * @param list2
	 * 
	 */
	public static void browseTopic(DoublyLinkedList<Vocab> list2) {
		
		boolean check = false;
		
		while(!check) {
			Vocab[] vocabArray = list2.returnVocabList();
			String[] topicArray = new String[20];
			
			System.out.println("===========================");
			
			//See the method printTopics
			printTopics(vocabArray, topicArray);
			
			System.out.println("0 - Exit");
			System.out.println("===========================");
			
			//Prompt user for input
			Scanner input = new Scanner(System.in);
			System.out.print("Enter Your Choice: ");
			int userInput = input.nextInt();
			
			//Break out of while loop if userInput == 0
			//Otherwise display words from a topic, the formatting may not be the best but it works for now
			int wordCount = 1;
			int lineCount = 0;
			
			if (userInput == 0) {
				check = true;
			} else {
				String userTopic = topicArray[userInput - 1];
				try {
					for (int i = 0; i < 1000; i++) { //Iterate through whole array
						if (vocabArray[i] != null) {
							if (vocabArray[i].getTopic().compareTo(userTopic) == 0) {							
								if (lineCount != 4) {
									System.out.print(wordCount + " - " + vocabArray[i].getWord() + "    ");
									lineCount++;
									wordCount++;
								}
									
								//Print again, then go to next line
								if (lineCount == 4) {
									lineCount = 0;
									System.out.println();
								}	
							}
						}
					}
					
				} catch (NullPointerException e) {
					e.getMessage();
				}
			}
			
			System.out.println();
		}	
			
	}

	/**
	 * This method prompts the user for a topic, then it asks the user to
	 * type the new topic's name. After, it asks the user to type all words 
	 * of the new topic, each separated with 2 spaces. Then it changes the 
	 * SinglyLinkedList list1 and DoublyLinkedList2 in a manner that accommodates
	 * the new topic before the first one.
	 * 
	 * @see returnVocabList
	 * @see printTopics
	 * @param list1
	 * @param list2
	 */
	public static void insertTopicBefore(SinglyLinkedList<String> list1, DoublyLinkedList<Vocab> list2) {
		
		Vocab[] vocabArray = list2.returnVocabList();
		String[] topicArray = new String[20];
		
		System.out.println("============================");
		System.out.println("        Pick a topic        ");
		System.out.println("============================");
		
		//See the method printTopics
		printTopics(vocabArray, topicArray);		
		
		System.out.println("0 - Exit");
		System.out.println("===========================");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Your Choice: ");
		int topicInput = input.nextInt();
		
		Scanner input2 = new Scanner(System.in);
		System.out.println("What is the new Topic's name? ");
		System.out.print("Enter The Name: ");
		String newTopic = input2.nextLine();
		newTopic = "#" + newTopic;
		
		
		Scanner lineScanner = new Scanner(System.in);
		System.out.println("Please type all the words in a line. To distinguish each word, seperate them with 2 spaces.");
		String lineInput = lineScanner.nextLine();
			
		String[] wordArray = lineInput.split("  ");
		int length = wordArray.length;
		
		//Append words in wordArray before the topic
		String currentTopic = "";
		String desiredTopic = topicArray[topicInput - 1];
			
		for (int i = 0; i < 1000 ; i++) { 
			try {
				currentTopic = vocabArray[i].getTopic();
				
				if (currentTopic.compareTo(desiredTopic) == 0) {
					//Currently at iteration i
					//Push all entries of vocabArray by j+length+1 (to the right)
					for (int j = 900 - length; j >= i; j--) {
						vocabArray[j + length + 1] = vocabArray[j];
					}
					System.out.println();
					
					//Assing wordArray entries to empty vocabArray entries from the j for loop
					vocabArray[i] = new Vocab("", newTopic);
					for (int k = 0; k < length; k++) {
						vocabArray[i + k + 1] = new Vocab(wordArray[k], newTopic);
					}
					
					i = 1000; //Break out of for loop
				}
			} catch(NullPointerException e) { 
				e.getMessage();
			}
			
		}
		
		//The vocabArray has been changed appropriately
		//Now we need to recreate the LinkedLists
		list1 = new SinglyLinkedList<String>(); //why does it break when i did list1.clear()???????????????????????????????
		list2.clear();
		
		for (int i = 0; i < 1000; i++) {
			if (vocabArray[i] != null) {
				list1.addToEnd(vocabArray[i].getWord());
				list2.addToEnd(vocabArray[i]);
			}
		}
	}

	/**
	 * This method prompts the user for a topic, then it asks the user to
	 * type the new topic's name. After, it asks the user to type all words 
	 * of the new topic, each separated with 2 spaces. Then it changes the 
	 * SinglyLinkedList list1 and DoublyLinkedList2 in a manner that accommodates
	 * the new topic after the first one.
	 * 
	 * @see returnVocabList
	 * @see printTopics
	 * @param list1
	 * @param list2
	 */
	public static void insertTopicAfter(SinglyLinkedList<String> list1, DoublyLinkedList<Vocab> list2) {
		
		Vocab[] vocabArray = list2.returnVocabList();
		String[] topicArray = new String[20];
		
		System.out.println("============================");
		System.out.println("        Pick a topic        ");
		System.out.println("============================");
		
		//See the method printTopics
		printTopics(vocabArray, topicArray);		
		
		System.out.println("0 - Exit");
		System.out.println("===========================");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Your Choice: ");
		int topicInput = input.nextInt();
		
		Scanner input2 = new Scanner(System.in);
		System.out.println("What is the new Topic's name? ");
		System.out.print("Enter The Name: ");
		String newTopic = input2.nextLine();
		newTopic = "#" + newTopic;
		
		
		Scanner lineScanner = new Scanner(System.in);
		System.out.println("Please type all the words in a line. To distinguish each word, seperate them with 2 spaces.");
		String lineInput = lineScanner.nextLine();
			
		String[] wordArray = lineInput.split("  ");
		int length = wordArray.length;
		
		//Append words in wordArray before the topic
		String currentTopic = "";
		String nextTopic = "";
		String desiredTopic = topicArray[topicInput - 1];
			
		for (int i = 0; i < 1000 ; i++) { 
			try {
				currentTopic = vocabArray[i].getTopic();
				nextTopic = vocabArray[i + 1].getTopic();
				
				if (currentTopic.compareTo(desiredTopic) == 0) {
					if (currentTopic.compareTo(nextTopic) != 0) {
						//Currently at iteration i
						//Push all entries of vocabArray by j+length
						for (int j = 900 - length; j > i; j--) {
							vocabArray[j + length + 1] = vocabArray[j];
						}
						
						System.out.println();
						
						//Assing wordArray entries to empty vocabArray entries from the j for loop
						vocabArray[i + 1] = new Vocab("", newTopic);
						for (int k = 0; k < length; k++) {
							vocabArray[i + k + 2] = new Vocab(wordArray[k], newTopic);
						}
						
						i = 1000; //Break out of for loop
					}
				}	
			} catch(NullPointerException e) { 
				e.getMessage();
			} catch (ArrayIndexOutOfBoundsException e) {
				e.getMessage();
			}
			
		}
		
		//The vocabArray has been changed appropriately
		//Now we need to recreate the LinkedLists
		list1 = new SinglyLinkedList<String>(); //why does it break when i did list1.clear()???????????????????????????????
		list2.clear();
		
		for (int i = 0; i < 1000; i++) {
			if (vocabArray[i] != null) {
				list1.addToEnd(vocabArray[i].getWord());
				list2.addToEnd(vocabArray[i]);
			}
		}
	}
	
	/**
	 * This method removes a topic by iterating through a vocabArray and using the remove method
	 * of both LinkedLists for a Vocab element that matches the topic.
	 * 
	 * @see returnVocabList
	 * @param list1
	 * @param list2
	 */
	public static void removeTopic(SinglyLinkedList<String> list1, DoublyLinkedList<Vocab> list2) {
		
		Scanner topicInput = new Scanner(System.in);
		System.out.println("What is the topic to be removed? ");
		String topic = topicInput.nextLine();
		topic = "#" + topic;
		
		Vocab[] vocabArray = list2.returnVocabList();
		
		//Wipe list1 and reinitialize
		list1 = new SinglyLinkedList<String>(); //why does it break when i did list1.clear()???????????????????????????????
		
		//Iterate through vocabArray and remove elements from list2 appropriately
		//Add to end of list1 the elements that are not excluded from list2
		for (Vocab element : vocabArray) {
			if (element != null) {
				if ((element.getTopic()).compareTo(topic) == 0) {
					list2.remove(element);
				} else {
					list1.addToEnd(element.getWord());
				}
			}
		} 
	}
	
	/**
	 * This method prompts the user to pick a topic, then it prompts for an action on that topic.
	 * The actions are either to add a word, to remove a word, to change a word, or to exit the
	 * action menu. All of these actions first check if the word exists. 
	 * 
	 * Adding a word is done by pushing the entries of vocabArray around to accommodate the new word. 
	 * 
	 * Removing a word is done by iterating through the vocabArray to find the matching word,
	 * then making that entry null.
	 * 
	 * Changing a word is done by iterating through the vocabArray to find the matching word, then
	 * prompt the user for the new word, then replacing that entry appropriately.
	 * 
	 * @see returnVocabList
	 * @see printTopics
	 * @see dataExists
	 * @param list1
	 * @param list2
	 */
	public static void modifyTopic(SinglyLinkedList<String> list1, DoublyLinkedList<Vocab> list2) {
		
		Vocab[] vocabArray = list2.returnVocabList();
		String[] topicArray = new String[20];
		boolean check = false;
		
		System.out.println("============================");
		System.out.println("        Pick a topic        ");
		System.out.println("============================");
		
		//See the method printTopics
		printTopics(vocabArray, topicArray);		
		
		System.out.println("0 - Exit");
		System.out.println("===========================");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Your Choice: ");
		int topicInput = input.nextInt();
		
		if (topicInput == 0)
			check = true;
		
		while(!check) {
			System.out.println("============================");
			System.out.println("     Modify Topics Menu     ");
			System.out.println("============================");
			System.out.println("a - add a word              ");
			System.out.println("b - remove a word           ");
			System.out.println("c - change a word           ");
			System.out.println("0 - Exit                    ");
			System.out.println("============================");
			
			System.out.println("Enter Your Choice: ");
			String modifyInput = input.next();
			
			//a - add a word
			if (modifyInput.compareTo("a") == 0) {
				Scanner addWord = new Scanner(System.in);
				System.out.println("Enter a word: ");
				String wordToAdd = addWord.nextLine();
				
				Vocab wordObject = new Vocab(wordToAdd, topicArray[topicInput - 1]);
				
				if (list2.dataExists(wordObject))
					System.out.println("The word exists.");
				else {
					//Append word to its associated topic...
					String currentTopic = "";
					String nextTopic = "";
					String desiredTopic = wordObject.getTopic();
					for (int i = 0; i < 1000 ; i++) { 
						try {
							currentTopic = vocabArray[i].getTopic();
							nextTopic = vocabArray[i + 1].getTopic();
							if (currentTopic.compareTo(desiredTopic) == 0) {
								if (currentTopic.compareTo(nextTopic) != 0) {
									//Currently at iteration i
									//Push all entries of vocabArray beyond i by i+1
									for (int j = 998; j >= i; j--) {
										vocabArray[j + 1] = vocabArray[j];
									}
									vocabArray[i] = wordObject;
									i = 1000; //Break out of for loop
								}
							}	
						} catch(NullPointerException e) { 
							e.getMessage();
						}
						
					}
					
					//The vocabArray has been changed appropriately
					//Now we need to recreate the LinkedLists
					list1 = new SinglyLinkedList<String>(); //why does it break when i did list1.clear()???????????????????????????????
					list2.clear();
					
					for (int i = 0; i < 1000; i++) {
						if (vocabArray[i] != null) {
							list1.addToEnd(vocabArray[i].getWord());
							list2.addToEnd(vocabArray[i]);
						}
					}
				}
			}
			
			//b - remove a word, there is probably a way to do this with list1/2.remove()...
			if (modifyInput.compareTo("b") == 0) {
				Scanner removeWord = new Scanner(System.in);
				System.out.println("Enter a word: ");
				String wordToRemove = removeWord.nextLine();
				
				Vocab wordObject = new Vocab(wordToRemove, topicArray[topicInput - 1]);
				
				if (list2.dataExists(wordObject)) {
					System.out.println("The word does exist.");
					for (int i = 0; i < 1000; i++) {
						if (vocabArray[i] != null) {
							if (vocabArray[i].getWord().compareTo(wordToRemove) == 0)
								vocabArray[i] = null;
						}
					}
				} else {
					System.out.println("The word does not exist.");
				}
			}
			
			//c - change a word
			if (modifyInput.compareTo("c") == 0) {
				Scanner changeWord = new Scanner(System.in);
				System.out.println("Enter a word: ");
				String wordToChange = changeWord.nextLine();
				
				//Change the vocabArray appropriately
				if (list2.dataExists(new Vocab(wordToChange, topicArray[topicInput - 1]))) {
					System.out.println("The word does exist.");
					for (int i = 0; i < 1000; i++) {
						if (vocabArray[i] != null) {
							if (vocabArray[i].getWord().compareTo(wordToChange) == 0) {
								System.out.println("Enter the new word: ");
								String changedWord = changeWord.nextLine();
								vocabArray[i] = new Vocab(changedWord, vocabArray[i].getTopic());
							}
						}	
					}
				} else {
					System.out.println("The word does not exist.");
				}
				
				//The vocabArray has been changed appropriately
				//Now we need to recreate the LinkedLists
				list1 = new SinglyLinkedList<String>(); //why does it break when i did list1.clear()???????????????????????????????
				list2.clear();
				
				for (int i = 0; i < 1000; i++) {
					if (vocabArray[i] != null) {
						list1.addToEnd(vocabArray[i].getWord());
						list2.addToEnd(vocabArray[i]);
					}
				}
			}
			
			
			//0 - Exit
			if (modifyInput.compareTo("0") == 0) {
				check = true;
			}	
		}	
	}
	
	/**
	 * This method searches a topic by iterating through a vocabArray and prints a found
	 * if there is a match. Otherwise, it prints a not found message.
	 * 
	 * @see returnVocabList
	 * @param list2
	 */
	public static void searchTopic(DoublyLinkedList<Vocab> list2) {
		
		//6 - search topics for a word
		Scanner topicInput = new Scanner(System.in);
		System.out.print("What is the topic to search? ");
		String topic = topicInput.nextLine();
		topic = "#" + topic;
		
		Scanner wordInput = new Scanner(System.in);
		System.out.print("What is the word to search? ");
		String word = wordInput.nextLine();
		
		Vocab compare = new Vocab(word, topic);
		Vocab[] vocabArray = list2.returnVocabList();
		
		boolean check = false;
		for (Vocab element : vocabArray) {
			if (element != null) {
				if (element.equals(compare)) {
					System.out.println("The word " + word + " does exist in the topic " + topic);
					check = true;
				}
			}
		}
		
		if (check == false)
			System.out.println("The word " + word + " does NOT exist in the topic " + topic);

	}
	
	/**
	 * This method prompts the user for fileName, then opens an input stream for fileName.
	 * Finally, it creates a SinglyLinkedList and DoublyLinkedList by reading the lines
	 * in fileName.
	 * 
	 * @param list1
	 * @param list2
	 */
	public static void loadFromFile(SinglyLinkedList<String> list1, DoublyLinkedList<Vocab> list2) {
		
		Scanner scanFile = new Scanner(System.in);
		System.out.println("Enter the name of the input file:");
		String fileName = scanFile.next();
		
		Scanner fileInput = null;
		// Step 1: Open input stream for fileName inputted by the user
		try {
			fileInput = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		} catch (Exception e) {
			System.out.println("Exception");
		}
		
		// Step 2: Read the file and sort the topic and words
		boolean checkString = false;
		String topic = "";
		String word = "";
		while (!checkString) {
			
			//Read every line of the file
			String current = "";
			try {
				current = fileInput.nextLine();
			} catch (RuntimeException e) {
				System.out.println("End of " + fileName);
				checkString = true;
				break; //Exit while loop if no remaining lines
			}
			
			//Check for first character to distinguish topic from word
			if (current != "") { 
				if (current.charAt(0) == '#') {
					topic = current;
				} else {
					word = current;
				}
				
				Vocab vocabWord = new Vocab(word, topic);
				System.out.println(vocabWord);
				
				//Put the word in the SinglyLinkedList
				list1.addToEnd(word);
				
				//Put the vocab word in the DoublyLinkedList
				list2.addToEnd(vocabWord);	
				
			} else {
				word = "";
			}
		}
		
		list1.printList();
		list2.printListFoward();
	}

	/**
	 * This method first clears the arrayList, then it adds words with the same
	 * starting letter as the user's input into the arrayList. After, it sorts
	 * the arrayList in alphabetical order, within the same starting letter, using
	 * Collections.sort and the SortLetterWords class. Finally, the arrayList is printed.
	 * 
	 * @see findSameStartingLetter
	 * @param list1
	 * @param arrayList
	 */
	public static void showLetterWords(SinglyLinkedList<String> list1, ArrayList<String> arrayList) {
		
		arrayList.clear();
		
		//Step 1: Prompt user for a letter
		Scanner userInput = new Scanner(System.in);
		System.out.print("Type the first letter of a word: ");
		String letter = userInput.next();
		
		//Step 2: Check in the SinglyLinkedList for words that match, then put words into arrayList
		String[] letterWordsArray = list1.findSameStartingLetter(letter);
		
		System.out.println("Adding word into arrayList");
		for (String element : letterWordsArray) {
			if (element != null) {
				arrayList.add(element);
			}	
		}
		
		//Step 3: Sort arrayList by alphabetical order
		System.out.println("Sorting arrayList words by alphabetical order");
		Collections.sort(arrayList, new SortLetterWords());
		
		//Step 4: Print out the arrayList
		for (String element : arrayList) {
			System.out.println(element);
		}
	}
	
	/**
	 * This method writes the current instance of the DoublyLinkedList into
	 * the fileName inputted by the user.
	 * 
	 * @see returnVocabList
	 * @param list2
	 */
	public static void saveToFile(DoublyLinkedList<Vocab> list2) {
		
		//Prompt user for next file
		Scanner input = new Scanner(System.in);
		System.out.print("What is the file's name? (Please enter a String): ");
		String fileName = input.next();
		
		//Put the contents of DoublyLinkedList into a Vocab[] array
		Vocab[] vocabArray = list2.returnVocabList();
		
		//open output stream for fileName
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file");
		}
		
		//Write elements of vocabArray into fileName
		pw.println(vocabArray[0].getTopic());
		vocabArray[0] = null;
		
		for (Vocab element : vocabArray) {
			if (element != null) {
				if (element.getWord() != "") {
					pw.println(element.getWord());
				} else {
					pw.println();
					pw.println(element.getTopic());
				}
			}
		}
		
		pw.close();
	}

	// ----------- Non-menu methods ---------- //
	
	/**
	 * This method adds the first topic into topicArray, then it iterates through the vocabArray. 
	 * For every iteration, it checks if the current topic (i) is not equal to the next topic (i + 1).
	 * If this is the case, then the next topic gets inserted into topicArray.
	 * 
	 * @param vocabArray
	 * @param topicArray
	 */
	public static void printTopics(Vocab[] vocabArray, String[] topicArray) {
		
		String currentTopic = "";
		String nextTopic = "";
		
		int count = 1;
		//First option
		topicArray[count - 1] = vocabArray[0].getTopic();
		System.out.println(count + " - " + vocabArray[0].getTopic().replaceAll("#", ""));
		
		//Other options, iterate through vocabArray
		for (int i = 0; i < 1000 ; i++) { 
			try {
				currentTopic = vocabArray[i].getTopic();
				nextTopic = vocabArray[i + 1].getTopic();
				
				if (currentTopic.compareTo(nextTopic) != 0) {
					count++;
					topicArray[count - 1] = nextTopic;
					nextTopic = nextTopic.replaceAll("#", "");
					System.out.println(count + " - " + nextTopic);
				}
				
			} catch(NullPointerException e) {
				e.getMessage();
			}
			
		}
	}
}
