# Two Dimensional List and Nested Loops

# Create a 2D List
number_grid = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9],
    [0]
]

print()
print("========== Accessing elements in a 2-D list ==========")
print(number_grid)
print()
print("number_grid[0][0]")
print(number_grid[0][0])
print("number_grid[2][1]")
print(number_grid[2][1])


print()
print("========== Parsing elements in a 2-D list ==========")
for row in number_grid:
    print(row)

# Nested FOR loop to print each element within each list element
print()
print("========== Nested FOR loop to print each element within each list element ==========")
for row in number_grid:
    for col in row:
        print(col)

print()
print("========== Nested FOR loop to print each element within each list element and "
      "the element in the main list ==========")
for row in number_grid:
    print(row)
    for col in row:
        print(col)









