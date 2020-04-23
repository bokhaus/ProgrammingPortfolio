# Classes and Objects

# Import Student class
from Student import Student

# Creating a student object using Student class
student1 = Student("Brian ", "Information Technology", 3.1, False)
student2 = Student("Debbie", "Art", 2.5, True)
print()
# using dot notation, the developer may access the student data from the object
# See example
print("====================================")
print("Student Name: " + student1.name + "  Student Major: " + student1.major)
print("====================================")
print("Student Name: " + student2.name + "  Student Major: " + student2.major)






