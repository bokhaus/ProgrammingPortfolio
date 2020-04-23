print()
print("----------- Printed Variable ----------- ")
"""Print Statement uses new line formatting (\n)"""
print("Lion\nString Tutorial")

"""Print Statement inserting a quote into print
 statement formatting (\") 
 In this case the (backslash) is called an escape character.
 It is then rendered literally"""
print("Lion \"String Tutorial\"")

"""This example prints a backslash and also includes the quotes 
whatever character follows the blackslach will be printed"""
print("Lion \\\"String Tutorial\"")

print("Lion \ String Tutorial")

# String Variable with concatenation
phrase = "Lion String Tutorial"
print()
print("----------- Printed Variable Concatenation ----------- ")
print("This is the printed variable " + "\"" + phrase + "\"")

# Convert phrase to lower case / upper case
phrase = "Lion String Tutorial"
print()
print("----------- Printed Variable in upper and lower case ----------- ")
print("This is the printed variable in lower case " + "\"" + phrase.lower() + "\"")
print()
print("This is the printed variable in upper case " + "\"" + phrase.upper() + "\"")

# Check to see is the phrase is in upper case returns a boolean value
print()
print("----------- Printed Variable testing case and returning a boolean value----------- ")
print("Is the phrase " + phrase + " in upper case? \n" +
      "The answer is... ")
print(phrase.isupper())

# Check to see is the phrase is in upper case, returns a boolean value
# Convert phrase to upper and then check upper
print()
print("----------- Printed Variable convert to upper case and then test----------- ")
print("Is the phrase " + phrase.upper() + " in upper case? \n" +
      "The answer is... ")
print(phrase.upper().isupper())

# Calculate the length of the String
print()
print("----------- Finding length of String variable ----------- ")
print("The length of the phrase (# number of characters) in " + "\"" + phrase.upper() + "\" is...")
print(len(phrase))

# Find a specific character index
print()
print("----------- Printed Variable (Find specific index value) ----------- ")
print("The first character in the phrase " + phrase + " is...")
print(phrase[0])
print("The fourth character in the phrase " + phrase + " is...")
print(phrase[3])
print("The fifth character in the phrase " + phrase + " is...")
print(phrase[4])
print("The fifth character is a space between the \"n\" and the \"S\"")

# Index function by passing in a parameter -> parameter is "T"
print()
print("----------- Printed Variable (Find index at which String starts)----------- ")
print("What is the index of \"T\" in the phrase " + "\"" + phrase + "\"")
print(phrase.index("T"))
# Index function by passing in a parameter -> parameter is "ion"
print()
print("What is the starting index of \"ion\" in the phrase " + "\"" + phrase + "\"")
print(phrase.index("ion"))

# Replace a part of a String this requires two parameters
phrase = "Lion String Tutorial"
print()
print("----------- Printed Variable (Replace a portion of the String)----------- ")
print("In the phrase, \"Lion String Tutorial\", the word \"Lion\" has been replaced with \"Brian\"")
print(phrase.replace("Lion", "Brian")) # two parameters












