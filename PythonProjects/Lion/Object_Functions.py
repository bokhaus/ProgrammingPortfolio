#Object_functions

# Import Student class
from Student import Student
student1 = Student("Brian ", "Information Technology", 3.5, False)
student2 = Student("Debbie", "Art", 2.5, True)
print()
print("Is " + student1.name + " on the honor roll?")
if not student1.on_honor_roll():
    print("No they are not!")
else:
    print("Yes they are!")

print()
print("Is " + student2.name + " on the honor roll?")
if not student2.on_honor_roll():
    print("No they are not!")
else:
    print("Yes they are!")

print()


