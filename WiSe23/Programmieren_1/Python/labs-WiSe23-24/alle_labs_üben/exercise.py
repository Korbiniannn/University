# lab 02

def a_plus_abs_b(a, b):
    """
    Return a+abs(b), but without calling abs.
    >>> a_plus_abs_b(2, 3)
    5
    >>> a_plus_abs_b(2, -3)
    5
    >>> a_plus_abs_b(-1, 4)
    3
    >>> a_plus_abs_b(-1, -4)
    3
    """
    if b < 0:
        b =  -b
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
    if i > j and i > k:
        m = j
        n = k
    elif j > i and j > k:
        m = i
        n = k
    else:
        m = i
        n = j
    return m*m + n*n

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
    for factor in range(n-1, 0, -1):
        if n % factor == 0:
            return factor
        
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
    return True if "88" in str(n) else False

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
    return 0 if k >= len(str(n)) else int(str(n)[::-1][k]) 

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
    reversed_n = str(n)[::-1]
    if k < len(str(n)):
        new_n = reversed_n.replace(reversed_n[k], str(d))
    elif k >= len(str(n)):
        new_n = reversed_n + (str(d))
    return int(new_n[::-1])

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
    count = 0
    for x in str(y):
        count += int(x)
    return count

# lab 03

def remove_odd_indices(lst, odd, weight):
    """
    lst is a list, odd is a boolean. Return a new list with elements from ls
    removed at certain indices: If odd is True, remove elements at odd indic
    otherwise remove even indexed elements. The remaining elements are
    multiplied by weight.
    >>> s = [1, 2, 3, 4]
    >>> t = remove_odd_indices(s, True, 1)
    >>> s
    [1, 2, 3, 4]
    >>> t
    [1, 3]
    >>> l = [5, 6, 7, 8]
    >>> m = remove_odd_indices(l, False, 4)
    >>> m
    [24, 32]
    """
    new_lst = []
    if odd == True:
        new = lst[::2]
        for x in new:
            new_x = x * weight
            new_lst.append(new_x)
    else: 
        new = lst[::-1][::2]
        for x in new[::-1]:
            new_x = x * weight
            new_lst.append(new_x)
    return new_lst

def merge(lst1, lst2):
    """Merges two sorted lists, lst1 and lst2. The new list contains all
    elements in sorted order.
    >>> merge([1, 3, 5], [2, 4, 6])
    [1, 2, 3, 4, 5, 6]
    >>> merge([], [2, 4, 6])
    [2, 4, 6]
    >>> merge([1, 2, 3], [])
    [1, 2, 3]
    >>> merge([5, 7], [2, 4, 6])
    [2, 4, 5, 6, 7]
    """
    # Bubble sort
    '''
    lst1.extend(lst2)
    for i in range(0, len(lst1)):
        for j in range(i+1, len(lst1)):
            if lst1[j] < lst1[i]:
                lst1[j], lst1[i] = lst1[i], lst1[j]
    return lst1
    '''
    # easier way with sorted method
    lst1.extend(lst2)
    return sorted(lst1)

def alternatingSum(lst):
    """
    Return the alternating sum of the integer or float elements
    in the list lst. An alternating sum of a sequence is a sum
    where the sign alternates from positive to negative or
    vice versa.
    >>> alternatingSum([5, 3, 8, 4])
    6
    >>> alternatingSum([])
    0
    """
    alt_sum = 0
    for idx, elem in enumerate(lst):
        if idx % 2 == 0:
            alt_sum += elem
        else:
            alt_sum -= elem
    return alt_sum

def is_anagram(s1, s2):
    """
    Decide whether a string s1 and a string s2 are anagrams.
    Use only lists in your solution.
    >>> is_anagram("","")
    True
    >>> is_anagram("abCdabCd","abcdabcd")
    True
    >>> is_anagram("abcdaabcd","aabbcddcb")
    False
    """
    if s1.lower() == s2.lower():
        return True
    else:
        return False
    
def rotateStringLeft(s, n):
    """
    The function takes a string s and a possibly-negative integer n.
    If n is non-negative, the function returns the string s rotated
    n places to the left. If n is negative, the function returns
    the string s rotated |n| places to the right.
    >>> rotateStringLeft('abcd', 1)
    'bcda'
    >>> rotateStringLeft('abcd', -1)
    'dabc'
    >>> rotateStringLeft('abcdefgh', -3)
    'fghabcde'
    """
    if n == 0:
        return s
    else:
        s = s[n:] + s[:n]
        return s 

def most_frequent(L):
    """
    Return the most frequent element in a list L, resolving
    ties arbitrarily. L only has elements of scalar types.
    >>> most_frequent([2,5,3,4,6,4,2,4,5])
    4
    >>> most_frequent([2,3,4,3,5,3,6,3,7])
    3
    >>> most_frequent([42])
    42
    >>> most_frequent([])

    """
    counter = {}
    current_key = 0
    current_value = 0
    if L == []:
        return None
    else:
        for elem in L:
            if elem in counter:
                counter[elem] += 1
            else: 
                counter[elem] = 1
        for key, value in counter.items():
            if counter[key] > current_value:
                current_value = value
                current_key = key
        return current_key
    
# lab 04

def ispalindrome(strng):
    """
    >>> ispalindrome("tennet")
    True
    >>> ispalindrome("xanax")
    True
    >>> ispalindrome("super")
    False
    >>> ispalindrome("hallo")
    False
    >>> ispalindrome("Tennet")
    True
    """
    strng = strng.lower()
    if len(strng) == 1:
        return True
    while len(strng) > 1:
        if strng[0] == strng[-1]:
            return True
        else:
            strng = strng[1:-1]
        return False
    
def oddcount(L):
    """
    >>> oddcount([])
    
    >>> oddcount([2, 5, 8, 3, 4, 9, 5, 5, 7, 8])
    6
    """
    count = 0 
    if L == []:
        return None
    if len(L) == 1:
        return 0 if L[0] % 2 == 0 else 1
    if L[0] % 2 != 0:
        count += 1
    return count + oddcount(L[1:])

def num_eights(pos):
    """
    >>> num_eights(888)
    3
    >>> num_eights(34848)
    2
    >>> num_eights(2487)
    1
    >>> num_eights(0)
    0
    """
    count = 0
    pos = str(pos)
    if pos == "":
        return None
    if len(pos) == 1:
        return 1 if int(pos[0]) == 8 else 0
    if len(pos) > 1:
        if int(pos[0]) == 8:
            count += 1
        else:
            count = 0
    return count + num_eights(str(pos)[1:])

def maxOdd(L):
    """
    >>> maxOdd([2, 5, 19, 6, 2, 5, 17])
    19
    >>> maxOdd([])

    """
    if L == []:
        return None
    if len(L) == 1:
        return L[0] if L[0] % 2 != 0 else 0
    else:
        if L[0] % 2 != 0:
            return max(L[0], maxOdd(L[1:]) )
        return maxOdd(L[1:])
    
def oddsonly(L):
    """
    >>> oddsonly([2, 4, 6, 7, 1, 3, 4, 5, 8, 9,])
    [7, 1, 3, 5, 9]
    >>> oddsonly([])

    """
    new_L = []
    if L == []:
        return None
    if len(L) == 1:
        if L[0] % 2 != 0:
            new_L.append(L[0])
            return new_L
    if len(L) > 1:
        if L[0] % 2 != 0:
            new_L.append(L[0])
        return new_L + oddsonly(L[1:])
    
def oddsum(L):
    """
    >>> oddsum([1, 4, 6, 5,4, 3, 8, 5, 9])
    23
    >>> oddsum([])

    """
    counter = 0
    if L == []:
        return None
    if len(L) == 1:
        if L[0] % 2 != 0:
            counter = L[0]
            return counter
    elif L[0] % 2 != 0:
        counter += L[0]
        return counter + oddsum(L[1:])
    else:
        return oddsum(L[1:])

def hasConsecutiveDigits(n):
    """
    >>> hasConsecutiveDigits(-88)
    True
    >>> hasConsecutiveDigits(77)
    True
    >>> hasConsecutiveDigits(64)
    False
    >>> hasConsecutiveDigits(-4546)
    False
    >>> hasConsecutiveDigits(7)
    False
    >>> hasConsecutiveDigits(4556)
    True
    >>> hasConsecutiveDigits(-6477)
    True

    """
    n = str(n)
    if len(n) <= 1:
        return False
    if len(n) > 1:
        if n[0] == n[1]:
            return True
    return hasConsecutiveDigits(str(n)[1:])
        