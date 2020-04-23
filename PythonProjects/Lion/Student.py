# Creating a class called Student

# Create and model
# creating a data type
class Student:
    # initialized function
    def __init__(self, name, major, gpa, is_on_probation):  # defining what a student is in the program
        self.name = name
        self.major = major
        self.gpa = gpa
        self.is_on_probation = is_on_probation

    # Create a function within the class
    # Create honor roll function
    def on_honor_roll(self):
        if self.gpa >= 3.5:
            return True
        else:
            return False


