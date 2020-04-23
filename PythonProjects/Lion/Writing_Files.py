# Writing to file

employee_file = open("employees.txt", "r")
print()
print("===== Printing Employee file =====")
print(employee_file.read())
employee_file.close()


employee_file = open("employees.txt", "a")
print()
print("===== Appending Employee file =====")
new_emp = input("Add a new Employee:  ")  # Writes employee to end of text file
employee_file.write("\n" + new_emp)  # Appends to a new line in file using an escape character
# employee_file.write(new_emp)
employee_file.close()

"""
# Overwrites file
employee_file = open("employees.txt", "w")
print()
print("===== Overwrites Employee file =====")
new_emp = input("Add a new Employee:  ")  # Writes employee to end of text file
employee_file.write(new_emp)  # Overwrites file
employee_file.close()
"""
# Create a new file called new_employee
employee_file = open("new_employee.txt", "w")
print()
print("===== Create a new file called new_employee =====")
new_emp = input("Add a new Employee:  ")  # User Input
employee_file.write(new_emp)  # Overwrites file
employee_file.close()