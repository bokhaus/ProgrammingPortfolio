# For_Loop

# Basic For loop
print("=================== Using a Basic For Loop ======================")
for letter in "Lion Tutorial":  # 'letter' is a variable
    print(letter)  # Prints each letter of String. (saying -> for each loop in the String, print it

# Using an Array in a For Loop
print("=================== Using an Array in a For Loop ======================")
friends = ["Brian", "Tanya", "Kevin", "Mindy"]
for friend in friends:
    print(friend)

print("=================== Using a range in a For Loop ======================")
for index in range(10):
    print(index)

print("=================== Using a range between two numbers (3, 10) in a For Loop ======================")
for index in range(3, 10):
    print(index)

print("=================== Using a range to print index in a For Loop ======================")
friends = ["Brian", "Tanya", "Kevin", "Mindy"]
for index in range(len(friends)):
    print(friends[index])


print("=================== Using a range in a For Loop with Else if statements ======================")
for index in range(7):  # print range of 7
    if index == 0:
        print("First Iteration")
    elif index == 1:
        print("Second Iteration")
    elif index == 2:
        print("Third Iteration")
    elif index == 3:
        print("Fourth Iteration")
    elif index == 4:
        print("Fifth Iteration")
    else:
        print("Not the first 5 Iterations")  # Catch the last two iterations of the loop on the range of 7.
    print(index)







