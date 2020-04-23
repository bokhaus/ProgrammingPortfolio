# Return Statements
# Used to return information from a function

# User input as a String
print()
number_input = input("Enter a number to be cubed: ")

# Function to cube a number
def cube(num):
    return num*num*num  # need to use return statement to provide value.


# Input from user is cubed
print()
print("================================================================")
print("The cube of " + number_input + " is...")
print(cube(float(number_input)))  # Convert String to Integer or float
print()
result = cube(float(number_input))
print("The result of assigning the function to a variable is the same as the first result.")
print(result)




