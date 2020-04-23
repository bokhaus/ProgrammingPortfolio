# IF_Statements and Comparisons
# Compare any variable types (number, String, Boolean)
# Comparison operators used >=. ==, =<, != and others

# Function
def max_num(num1, num2, num3):
    if num1 >= num2 and num1 >= num3:
        return num1
    elif num2 >= num1 and num2 >= num3:
        return num2
    else:
        return num3


print("The max number in the set is...")
print(max_num(300, 250, 500))


print()
print("===========================================")

# User input as a String
print()
number_input1 = input("Enter first number : ")
number_input2 = input("Enter second number : ")
number_input3 = input("Enter third number : ")

# Function comparing 3 numbers
def max_num(num1, num2, num3):
    if num1 >= num2 and num1 >= num3:
        return num1
    elif num2 >= num1 and num2 >= num3:
        return num2
    else:
        return num3


print()
print("The largest number of the three numbers is ...")
# Each number has to be declared as a float. Cannot do all three as one declaration
print(max_num(float(number_input1), float(number_input2),
              float(number_input3)))
print()
print("===========================================")









