# Catching Errors and handling errors

print()
print("========== Basic Program without which will break when input is not a number ==========")
number = int(input("Enter a number: "))

print(number)

print()
print("========== Basic Program with Error Handling (Invalid Input) ==========")
try:
    value = 10 / 0  # demonstrates a zero value error and specify two exceptions
    number = int(input("Enter a number: "))
    print(number)

except ValueError:
    print("Invalid input")
except ZeroDivisionError as err:  # May be done using print error statement or as a custom message
    print(err)
    # print("Divided by Zero")
except:  # Too broad of an exception. Should always specify error
    print("This is an error")
