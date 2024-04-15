def how_warm(num):
    temp = num
    if temp < 20:
        print("Grab a coat!")
    elif temp > 25:
        print("Put sunscreen on!")
    elif temp < 0:
        print("You will get a cold!")
    else: print("Have fun!")

print("How warm is it?")
x = input()
x = int(x)

result = how_warm(x)