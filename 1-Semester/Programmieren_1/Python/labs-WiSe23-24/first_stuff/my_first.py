def my_add(num1, num2):
    x = num2 * num2
    return num1 + num2 + x


print("Enter first number:")
x = input()
x = int(x)

print("Enter second number:")
y = input()
y = int(y)

result = my_add(x, y)
print("Result:" , result)