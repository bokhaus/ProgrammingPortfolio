# Guessing Game

# Declare and intialize variables
secret_word = "lion"
guess = ""

# Prompt user for secret word using WHILE loop
while guess != secret_word:
    guess = input("Enter guess: ")

print("You WIN!")

print("==========================================================")
print()
# Limit number of guesses a user can try before they lose
secret_word = "lion"
guess = ""
guess_count = 0
guess_limit = 3  # Guess limit is 3
out_of_guesses = False

# Prompt user for secret word using WHILE loop
while guess != secret_word and not (out_of_guesses):
    if guess_count < guess_limit:
        guess = input("Enter guess: ")
        guess_count += 1  # increment guess count
    else:
        out_of_guesses = True

if out_of_guesses:
    print("Out of guesses, You LOSE!!!!")
else:
    print("You WIN!")

