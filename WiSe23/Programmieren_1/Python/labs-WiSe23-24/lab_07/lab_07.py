import math
class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def distance_to_origin(self):
        return (self.x**2 + self.y**2)**0.5
    
    def euclidean_distance(self, other):
        return ((self.x - other.x)**2 + (self.y - other.y)**2)**0.5
    
    def manhattan_distance(self, other):
        return abs(self.x - other.x) + abs(self.y - other.y)
    
    def angle_between(self, other):
        vert = other.y - self.y
        horiz = other.x - self.x
        return math.atan2(vert, horiz)
class triangle(Point):
    '''
    >>> t = triangle(Point(2,3), Point(5,6), Point(3, 5))
    >>> t.angle_classification()
    'acute'
    >>> t.side_classification()
    'Isosceles'
    '''
    def __init__(self, p1, p2, p3):
        self.p1 = p1
        self.p2 = p2
        self.p3 = p3
    
    def side_lengths(self):
        first = self.p1.euclidean_distance(self.p2)
        second = self.p2.euclidean_distance(self.p3)
        third = self.p3.euclidean_distance(self.p1)
        return first, second, third

    
    def angles(self):
        a = self.p1.angle_between(self.p2)
        b = self.p2.angle_between(self.p3)
        c = self.p3.angle_between(self.p1)
        return a, b, c

    def side_classification(self):
        if self.side_lengths()[0] == self.side_lengths()[1] == self.side_lengths()[2]:
            return 'Equilateral'
        if self.side_lengths()[0] == self.side_lengths()[1] or self.side_lengths()[1] == self.side_lengths()[2] or self.side_lengths()[0] == self.side_lengths()[2]:
            return 'Isosceles'
        else:
            return 'Scalene'

    def angle_classification(self):
        x = self.angles()
        if x[0] == 90 or x[1] == 90 or x[2] == 90:
            return 'right'
        if x[0] >= 90 or x[1] >= 90 or x[2] >= 90:
            return 'obtuse'
        if x[0] == x[1] == x[2]:
            return 'equiangular'
        else:
            return 'acute'

    def is_right(self):
        if self.angle_classification() == 'right':
            return True
        else:
            False

    def area(self,):
        pass

    def perimeter():
        pass
