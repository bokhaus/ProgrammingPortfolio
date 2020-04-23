# A Better Calculator

# User variables converting String input to float
num1 = float(input("Enter first number: "))
op = input("Enter operator: ")
num2 = float(input("Enter first number: "))

# Use if statement to check which operator is used
if op == "+":
    print(num1 + num2)
elif op == "-":
    print(num1 - num2)
elif op == "/":
    print(num1 / num2)
elif op == "*":
    print(num1 * num2)
else:
    print("Invalid operator")














