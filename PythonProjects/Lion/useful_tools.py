# Demo file

import random
feet_in_mile = 5280
meters_in_kilometer = 1000

beatles = ["John Lennon", "Paul McCartney", "George Harrison", "Ringo Starr"]

def get_file_ext(filename):  # give file name and function will tell user what the extension is
    return filename[filename.index(".") + 1:]

def roll_dice(num):  # simulates rolling dice sided dice is based on the number passed in (6 =6-sided dice)
    return random.randint(1, num)