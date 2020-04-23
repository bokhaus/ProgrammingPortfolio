# Multiple Choice Quiz

# From Question file Import question class
from Question import Question

# Question prompts Array
question_prompts = [
    "What color are the apples?\n(a) Red\Green\n(b) Purple\n(c) Orange\n\n",
    "What color are the bananas?\n(a) Teal\n(b) Magenta\n(c) Yellow\n\n",
    "What color are the Strawberries?\n(a) Yellow\n(b) Red\n(c) Blue\n\n"
]

# Create another array with correct answers
# Creates  question data type with two parameters, (question prompt, correct answer)
questions = [
    Question(question_prompts[0], "a"),
    Question(question_prompts[1], "c"),
    Question(question_prompts[2], "b"),
]

def run_test(quest_ans):
    score = 0
    # for each question object in the answer_key array
    # ask each question and store the response in a variable
    for question in quest_ans:
        # Prints question prompt
        answer = input(question.prompt)
        # if the student answer is the same as the correct answer increase score
        if answer == question.answer:
            score += 1
    # must convert score number to a string.
    # convert the length of the array to str and print for number of questions asked
    print("You got " + str(score) + "/" + str(len(quest_ans)) + " correct")


run_test(questions)



