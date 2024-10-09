def oddcount(L):
    """
    >>> oddcount([])
    
    >>> oddcount([2, 5, 8, 3, 4, 9, 5, 5, 7, 8])
    6
    """
    if L == []:
        return None
    elif len(L) == 1:
        return 1 if L[0] % 2 else 0
    else:
        counter = 0 
        last_elem = L.pop()
        if last_elem % 2 != 0:
            counter += 1
        return counter + oddcount(L)

def oddsum(L):
    """
    >>> oddsum([1, 4, 6, 5,4, 3, 8, 5, 9])
    23
    >>> oddsum([])

    """
    if L == []:
        return None
    if len(L) == 1:
        return L[0] if L[0] % 2 else 0
    else:
        last_elem = L.pop()
        counter = 0
        if last_elem % 2 != 0:
            counter += last_elem
        return counter + oddsum(L)

def oddsonly(L):
    """
    >>> oddsonly([2, 4, 6, 7, 1, 3, 4, 5, 8, 9,])
    [7, 1, 3, 5, 9]
    >>> oddsonly([])

    """
    new_list = []
    if L == []:
        return None
    elif len(L) == 1:
        return [L[0]] if L[0] % 2 else []
    else:
        if L[0] % 2 != 0:
            new_list.append(L[0])
        return new_list + oddsonly(L[1:])
    
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
            return max( L[0], maxOdd(L[1:]) )
        return maxOdd(L[1:])

def hasConsecutiveDigits(n):
    """
    >>> hasConsecutiveDigits(-88)
    True
    >>> hasConsecutiveDigits(77)
    True
    >>> hasConsecutiveDigits(64)
    Flase
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
    if len(n) == 1:
        return False
    
    if n[0] == n[1]:
        return True
    else:
        n = n[1:]
        return hasConsecutiveDigits(n)
    
def alternatingSum(L):
    """
    >>> alternatingSum([1, 2, 3, 4, 5])
    3

    """
    sum = 0
    if len(L) == 0:
        return 0
    if len(L) == 1:
        return L[0] 
    else:
        sum += L[0]
        sum -= L[1]
    return sum + alternatingSum(L[2:])

def ispalindrome(strng):
    """
    >>> ispalindrome("tennet")
    True
    >>> ispalindrome("xanax")
    True
    >>> ispalindrome("super")
    False
    """
    if len(strng) <= 1:
        return True
    else:
        return strng[0] == strng[-1] and ispalindrome(strng[1:-1])

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
    if pos == 0:
        return 0
    last_digit = pos % 10
    if last_digit == 8:
        return 1 + num_eights(pos // 10)
    else:
        return num_eights(pos // 10)
