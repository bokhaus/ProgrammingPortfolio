# Inheritance

# Import Chef class from Chef file
from Chef import Chef
from ChineseChef import ChineseChef
myChef = Chef()

print("=======================")
print("My chef makes... ")
myChef.make_chicken()
myChef.make_special_dish()
myChef.make_salad()

print("=======================")
print()
print("My Chinese chef makes... ")
myChineseChef = ChineseChef()
myChineseChef.make_special_dish()
myChineseChef.make_fried_rice()


