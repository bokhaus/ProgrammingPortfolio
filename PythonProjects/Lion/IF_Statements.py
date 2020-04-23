# IF_Statements

# Create a Boolean Variable
is_male = False
is_tall = True
print()
print("=============================")
print("The boolean variables are set to " + str(is_male) + " = Male, " + str(is_tall) + " = Tall")
print()
print("============ Simple IF-Else Statement =================")

# Simple IF-Else Statement
if is_male:
    print("You are a male")
else:
    print("You are not a male")

print()
print("============== IF Statement using OR ===============")

# Simple IF-Else Statement using OR
if is_male or is_tall:
    print("Your are a male or tall or both")
else:
    print("You are neither male nor tall")

print()
print("============== IF Statement using AND ===============")

# Simple IF-Else Statement using AND
if is_male and is_tall:
    print("Your are a tall male")
else:
    print("You are either not a male or not tall or both")

print()
print("============== IF Statement using If-Elseif-Else ===============")

# Simple IF-ElseIf-Else Statement using AND
if is_male and is_tall:
    print("Your are a tall male")
elif is_male and not is_tall:  # not negates parameter
    print("You are a short male")
elif not is_male and is_tall:  # not negates parameter
    print("You are not a male, but are tall")
else:
    print("You are not a male and not tall")




