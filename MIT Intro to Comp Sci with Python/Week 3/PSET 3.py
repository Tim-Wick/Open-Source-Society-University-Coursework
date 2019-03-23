# Hangman game
#

# -----------------------------------
# Helper code
# You don't need to understand this helper code,
# but you will have to know how to use the functions
# (so be sure to read the docstrings!)

import random
import string

WORDLIST_FILENAME = "words.txt"

def loadWords():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print("Loading word list from file...")
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r')
    # line: string
    line = inFile.readline()
    # wordlist: list of strings
    wordlist = line.split()
    print("  ", len(wordlist), "words loaded.")
    return wordlist

def chooseWord(wordlist):
    """
    wordlist (list): list of words (strings)

    Returns a word from wordlist at random
    """
    return random.choice(wordlist)

# end of helper code
# -----------------------------------

# Load the list of words into the variable wordlist
# so that it can be accessed from anywhere in the program
wordlist = loadWords()

def isWordGuessed(secretWord, lettersGuessed):
    '''
    secretWord: string, the word the user is guessing
    lettersGuessed: list, what letters have been guessed so far
    returns: boolean, True if all the letters of secretWord are in lettersGuessed;
      False otherwise
    '''
    guessed = True
    for i in secretWord:
        if i in lettersGuessed:
            continue
        else:
            guessed = False
    return guessed




def getGuessedWord(secretWord, lettersGuessed):
    '''
    secretWord: string, the word the user is guessing
    lettersGuessed: list, what letters have been guessed so far
    returns: string, comprised of letters and underscores that represents
      what letters in secretWord have been guessed so far.
    '''
    return_string = ""
    for i in secretWord:
        if i in lettersGuessed:
            return_string += i
        else:
            return_string += "_ "
    return return_string




def getAvailableLetters(lettersGuessed):
    '''
    lettersGuessed: list, what letters have been guessed so far
    returns: string, comprised of letters that represents what letters have not
      yet been guessed.
    '''
    available_letters = string.ascii_lowercase
    return_string = ""

    for i in available_letters:
        if i in lettersGuessed:
            continue
        else:
            return_string += i

    return return_string


    

def hangman(secretWord):
    '''
    secretWord: string, the secret word to guess.

    Starts up an interactive game of Hangman.

    * At the start of the game, let the user know how many 
      letters the secretWord contains.

    * Ask the user to supply one guess (i.e. letter) per round.

    * The user should receive feedback immediately after each guess 
      about whether their guess appears in the computers word.

    * After each round, you should also display to the user the 
      partially guessed word so far, as well as letters that the 
      user has not yet guessed.

    Follows the other limitations detailed in the problem write-up.
    '''
    print("Welcome to the game, Hangman!")
    print("I am thinking of a word that is {} letters long".format(len(secretWord)))
    print("-" * 10)
    # Sets variables for guesses left and letters guessed
    guesses_left = 8
    letters_guessed = []

    # while isWordGuessed is returning false and there is more than 0 guesses left we'll continue through the loop
    while not isWordGuessed(secretWord, letters_guessed) and guesses_left > 0:
        # Gets the available letters based on letters_guessed
        available_letters = getAvailableLetters(letters_guessed)
        # Prints number of guesses remaining
        print("You have {} guesses left".format(guesses_left))
        # Prints available_letters
        print("Available Letters: " + available_letters)
        # Gets the guess from the user and converts to lower case
        guess = input("Please guess a letter: ").lower()
        # If the user has not already guessed the letter... add it to letters_guessed
        if guess not in letters_guessed:
            letters_guessed += guess
            if guess in secretWord:
                print("Good guess: " + getGuessedWord(secretWord, letters_guessed))
            else:
                print("Oops! That letter is not in my word: " + getGuessedWord(secretWord, letters_guessed))
                guesses_left -= 1
        # Otherwise print error regarding double letter
        else:
            print("Oops! You've already guessed that letter: " + getGuessedWord(secretWord, letters_guessed))
        print("-" * 10)
    if isWordGuessed(secretWord, letters_guessed):
        print("Congratulations, you won!")
    else:
        print("Sorry, you ran out of guesses. The word was " + secretWord)

# When you've completed your hangman function, uncomment these two lines
# and run this file to test! (hint: you might want to pick your own
# secretWord while you're testing)

secretWord = chooseWord(wordlist).lower()
hangman(secretWord)
