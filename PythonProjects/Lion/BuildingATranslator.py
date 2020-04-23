# Building a translator

# Change all vowels in a phrase to g
def translate(phrase):
    translation = ""
    # Using a FOR loop in conjunction with
    for letter in phrase:
        # If letter is a vowel change to g
        if letter in "AEIOUaeiou":
            translation = translation + "g"
        else:
            translation = translation + letter

    return translation


# Combining several statements
# Print out the translation of what the user inputs
print()
print("==================== Basic Translator=========================")
print(translate(input("Enter a phrase: ")))


# Change all vowels in a phrase to g
def translate(phrase):
    translation = ""
    # Using a FOR loop in conjunction with
    for character in phrase:
        # If letter is a vowel change to g
        # change user input to lower to compare making it more efficient than first ex.
        # Change letter to a g if in String.
        if character.lower() in "aeiou":
            # Change character to capital letter if a vowel being replaced is a capital letter
            if character.isupper():
                translation = translation + "G"
            else:
                translation = translation + "g"

        else:
            translation = translation + character

    return translation


# Combining several statements
# Print out the translation of what the user inputs
print()
print("================ Translator change user input to lower to compare making it more efficient ====================")
print(translate(input("Enter a phrase: ")))
