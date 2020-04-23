# Reading from External Files (Text, CSV, HTML)
# Python Read Command

# Relative or absolute path to the file.
# or file name if in the same directory
# options: "r" -> read, "w" -> write, "a" -> append, "r+" -> read and write
employee_file = open("employees.txt", "r")

"""
# Returns a boolean
print("=========== Is the file Readable?=============")
print(employee_file.readable())

# Reads all lines
print("=========== Reads all lines in the file =============")
print(employee_file.read())

# Reads first Line of file
print("=========== Reads first line of file =============")
print(employee_file.readline())

# Reads next Line of file
print("=========== Reads next line of file =============")
print(employee_file.readline())

# Reads All Lines of file as a List
print("=========== Reads all lines as list =============")
print(employee_file.readlines())

# Select specific element in the List
print("=========== Select specific element in the List =============")
print(employee_file.readlines()[4])  # select element at index 4 (5th element)
"""
# This line will loop through employees files and print each employee
print()
print("=========== Print each employee line =============")
for employee in employee_file.readlines():
    print(employee)

# Always close file after use
employee_file.close()



