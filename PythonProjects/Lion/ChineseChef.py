# Class used for Inheritance

# Import (Inherit) from Chef class other functions
from Chef import Chef

# Chinese Chef Class
class ChineseChef(Chef):

    # Two functions within the class
    def make_special_dish(self):
        print("The chef makes a special dish called orange chicken")

    def make_fried_rice(self):
        print("The chef makes fried rice")




