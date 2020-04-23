# List Functions
lucky_numbers = [3, 4, 10, 17, 23, 45]
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy",
           "Matthew", "Krista", "Thomas", "Leslie"]


# Print the elements in the friends list
print()
print("This is the friends list ")
print(friends)

# Append two lists
print()
print("--------- Combine multiple lists into one list (friends and lucky_numbers)---------")
friends.extend(lucky_numbers)  # combines two lists, friends and lucky_numbers
print(friends)  # Prints appended list

# Append additional item to list
lucky_numbers = [3, 4, 10, 17, 23, 45]
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy",
           "Matthew", "Krista", "Thomas", "Leslie"]
print()
print("--------- Append additional item to list Added 'Jeff' to end of friends list---------")
friends.append("Jeff")  # adds another element to friends list
print(friends)  # Prints appended list

# Insert additional item to list
lucky_numbers = [3, 4, 10, 17, 23, 45]
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy",
           "Matthew", "Krista", "Thomas", "Leslie"]
print()
print("--------- Insert additional item to list 'Couch' at index[1]---------")
# Requires the index where the element needs inserted and the item to be inserted
friends.insert(1, "Couch")  # Inserts another element to friends list
print(friends)  # Prints updated list

# Remove item from list
print()
print("--------- Remove item from list 'Couch'---------")
friends.remove("Couch")  # Removes element to friends list
print(friends)  # Prints updated list

# Clear list
print()
print("--------- Remove ALL elements from friends list ---------")
friends.clear()
print(friends)  # print updated list

# (POP) Remove last element from friends list.
lucky_numbers = [3, 4, 10, 17, 23, 45]
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy",
           "Matthew", "Krista", "Thomas", "Leslie"]
print()
print("--------- (POP) Remove last element from friends list.---------")
print("Friend list prior to pop")
print(friends)  # Prints updated list
# POP function
print()
print("New friends list")
friends.pop()  # Removes element from friends list
print(friends)  # Prints updated list

# Determine if an element is part of a list.
lucky_numbers = [3, 4, 10, 17, 23, 45]
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy",
           "Matthew", "Krista", "Thomas", "Leslie"]
print()
print("--------- Determine if an element is part of a list. Error is returned if not part of the list. ---------")
print("what is the index number of 'Kevin'")
print(friends.index("Kevin"))
print()
print("what is the index number of 'Tanya'")
print(friends.index("Tanya"))

# Count number of times an element repeats.
lucky_numbers = [3, 4, 10, 17, 23, 45]
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy", "Matt",
           "Matthew", "Krista", "Thomas", "Leslie"]
print()
print("--------- Count number of times an element repeats. ---------")
print("How many times does 'Matt' occur")
print(friends.count("Matt"))
print("How many times does 'Tanya' occur")
print(friends.count("Tanya"))
print("How many times does 'Brian' occur")
print(friends.count("Brian"))

# Sort list
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy", "Matt",
           "Matthew", "Krista", "Thomas", "Leslie"]
print()
print("--------- Sort elements in friends list. ---------")
print("Prior to sort.")
print(friends)
friends.sort()
print()
print("After sort.")
print(friends)

# Reverse Sort list
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy", "Matt",
           "Matthew", "Krista", "Thomas", "Leslie"]
print()
print("--------- Reverse Sort elements in friends list. ---------")
print("Prior to sort.")
print(friends)
friends.reverse()
print()
print("After sort.")
print(friends)

# Copy list
friends = ["Tanya", "Tom", "Natalie", "Matt", "Maggie", "Kevin", "Mindy", "Matt",
           "Matthew", "Krista", "Thomas", "Leslie"]
friends2 = friends.copy()
print()
print("--------- Copy elements in friends list to new list friends2. ---------")
print("Friends1.")
print(friends)

print()
print("Friends2.")
print(friends2)
















