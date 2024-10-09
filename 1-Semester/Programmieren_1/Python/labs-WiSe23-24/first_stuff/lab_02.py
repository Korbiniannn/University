def a_plus_abs_b(a, b):
    """Return a+abs(b), but without calling abs.
    > a_plus_abs_b(2, 3)
    5
    > a_plus_abs_b(2, -3)
    5
    > a_plus_abs_b(-1, 4)
    3
    > a_plus_abs_b(-1, -4)
    3
    """
    if b < 0:
        return a - b
    else:
        return a + b
    
def two_of_three(i, j, k):
    """
    Return m*m + n*n, where m and n are the two smallest members of the
    positive numbers i, j, and k.
    >>> two_of_three(1, 2, 3)
    5
    >>> two_of_three(5, 3, 1)
    10
    >>> two_of_three(10, 2, 8)
    68
    >>> two_of_three(5, 5, 5)
    50
    """
    # YOUR CODE HERE
    if i <= k and j <= k:
        m = i
        n = j
    elif k <= i and j <= i:
        m = k
        n = j
    else:
        m = k
        n = i
    return m * m + n * n

def largest_factor(n):
    """
    Return the largest factor of n (n > 1 !) that is smaller than n.
    A factor divides a number evenly.
    >>> largest_factor(15) # factors are 1, 3, 5
    5
    >>> largest_factor(80) # factors are 1, 2, 4, 5, 8, 10, 16, 20, 40
    40
    >>> largest_factor(13) # factor is 1 since 13 is prime
    1
    """
    # YOUR CODE HERE
    for x in range(n-1, 0, -1):
        if n % x == 0:
            return x
        
def double_eights(n):
    """
    Return true if n has two eights in a row.
    >>> double_eights(8)
    False
    >>> double_eights(88)
    True
    >>> double_eights(2882)
    True
    >>> double_eights(880088)
    True
    >>> double_eights(12345)
    False
    >>> double_eights(80808080)
    False
    """
    # YOUR CODE HERE
    x = "88"
    if x in str(n):
        return True
    else:
        return False
    
def getKthDigit(n, k):
    """
    Return the kth digit of n (an integer), starting from 0,
    counting from the right.
    >>> getKthDigit(789, 0)
    9
    >>> getKthDigit(789, 1)
    8
    >>> getKthDigit(789, 2)
    7
    >>> getKthDigit(789, 3)
    0
    >>> getKthDigit(-789, 0)
    9
    """
    # YOUR CODE HERE
    if k >= len(str(n)):
        return 0
    Kth_Digit = (str(n))[::-1][k]
    result = int(Kth_Digit)
    print(result)

def setKthDigit(n, k, d):
    """
    n is an integer, k is a non-negative integer and d is non-negative
    single digit (0 = d = 9). Return the number n with the kth digit
    replaced with d.
    >>> setKthDigit(468, 0, 1)
    461
    >>> setKthDigit(468, 1, 1)
    418
    >>> setKthDigit(468, 2, 1)
    168
    >>> setKthDigit(468, 3, 1)
    1468
    """
    # YOUR CODE HERE
    if k in range(len(str(n))):
        return int(str(n).replace(list(str(n))[::-1][k], str(d)))
    
    return int((str(n)[::-1] + str(d))[::-1])
    
def sum_digits(y):
    """
    Sum all the digits of y. y is always nonnegative. Floor division
    and modulo might be helpful.
    >>> sum_digits(10) # 1 + 0 = 1
    1
    >>> sum_digits(4224) # 4 + 2 + 2 + 4 = 12
    12
    >>> sum_digits(1234567890)
    45
    >>> a = sum_digits(123) # use return rather than print in your code!
    >>> a
    6
    """
    # YOUR CODE HERE 
    get_sum_digt = map(int, str(y))
    return sum(get_sum_digt)