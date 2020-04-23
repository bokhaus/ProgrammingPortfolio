# Exponents

# 2^3

print("2^3")
print(2**3)

print()
print("======================= Using a Function to calculate a power =======================")
print()
base = input("Enter number to be raised: ")
power = input("Enter to what power:")

def raise_to_power(base_num, pow_num):
    result = base_num ** pow_num
    return result


print(raise_to_power(int(base), int(power)))

print()
print("======================= Using a Function/For Loop to calculate a power =======================")
print()
base = input("Enter number to the base number: ")
power = input("Enter to what power:")

def raise_to_power(base_num, pow_num):
    result = 1  # stores result of math
    # Loop through range of numbers from 0 up to but not including the power number
    # Loop through power number of times
    for index in range(pow_num):
        result = result * base_num  # multiplying result by base number
    return result  # return the result of the function


print(raise_to_power(int(base), int(power)))



















