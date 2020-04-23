# Functions (Method in Java)
# Creating a function to say hello user
# Used when a group of code is to perform a specific task
def say_hi():
    print("Hello User")


print()
print("Printing hello user using a function")
# Execute code outside of function
print("Top")
# Execute code inside the function
say_hi()
# Execute code outside of function
print("Bottom")
print("------------------------------------")


# Say hello using a parameter
print()
print("Printing Hello using a parameter within a function")
def say_hello(name):
    print("Hello " + name)


say_hello("Brian")
say_hello("John")
print("------------------------------------")

# Say hello using multiple parameters (name and age)
print()
print("Printing Hello using multiple parameters (name and age) within a function")
def say_hello2(name, age):
    print("Hello " + name + ", you are " + age)


say_hello2("Brian", "47")
say_hello2("John", "66")
print("------------------------------------")

# Convert age number to string
# Say hello using multiple parameters (name and age)
print()
print("Printing Hello using multiple parameters (name and age) within a function")
print("Convert age number to String number")
def say_hello2(name, age):
    print("Hello " + name + ", you are " + str(age))  # Convert age as number to age as a String


say_hello2("Brian", 47)
say_hello2("John", 66)
print("------------------------------------")

# =============================================
# Applying what I have learned
# user input to a function to say hello
# =============================================
print()
print("Printing Hello using multiple parameters (name and age) within a function")
print("Convert age number to String number")
print()
name2 = input("Enter your name: ")
age2 = input("enter your age: ")
print()

def say_hello3(name, age):
    print("Hello " + name + ", you are " + str(age))  # Convert age as number to age as a String


# user input
print()
print("This was printed using user input variables")
say_hello3(name2, age2)
print()
print("This was printed using hard-coded values")
say_hello3("John", 66)
print("------------------------------------")
