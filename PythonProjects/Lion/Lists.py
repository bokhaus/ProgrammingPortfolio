# Working with Lists

# Create a list
# You can use multiple formats, String, number, boolean
friends = ["Tom",  "Tanya", "Natalie", "Maggie","Kevin"]

# Print specific element of a list
print()
print("---------- Print the list and then print the third index ----------")
print("The elements of the friends list are... ")
print(friends)
print()
print("Printing index 3 of the friends list.")
print(friends[4])

# Print index at the end using negative index[0] is the starting point
print()
print("Printing index -1 of the friends list.")
print(friends[-1])

# Select elements starting at a specific index position
print()
print("---------- Print elements starting at a specific index position ----------")
print("Printing elements starting at index 1 of the friends list.")
print(friends[1:])

# Select RANGE of elements starting at a specific index position
print()
print("---------- Print RANGE of elements starting at a specific index position ----------")
print("Printing elements starting at index 1 of the friends list through index 3.")
print(friends[1:3])

# Change value of a specific index position
print()
print("---------- Change value of a specific index position ----------")
friends = ["Tom",  "Tanya", "Natalie", "Maggie","Kevin"]
print()
print("The elements of the friends list are currently...")
print(friends)

print()
print("Changing the value of index[0] to 'Matt'")
friends[0] = "Matt"
print()
print("The element at index[0] is now... ")
print(friends[0])
print()
print(friends)


