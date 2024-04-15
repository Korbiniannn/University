from collections import deque
from utils import PriorityQueue, Node


def breadth_first_search(puzzle):
    node = Node(puzzle.initial)
    if puzzle.goal_test(node.state):
        return node
    frontier = deque([node])
    # implement breadth-first-search here
    #
    #
    #
    #
    pass


def best_first_search(puzzle, f):
    """Search the nodes with the lowest f scores first."""
    node = Node(puzzle.initial)
    frontier = PriorityQueue('min', f)
    # implement best-first-search here
    #
    #
    #
    #
    pass


def h(node):
    """Default heuristic function h(n) = number of misplaced tiles """
    goal = (1, 2, 3, 4, 5, 6, 7, 8, 0)
    # implement the default heuristic function here
    #
    #
    #
    pass


def g(node):
    return node.path_cost


def f(node):
    return g(node) + h(node)


def astar_search(puzzle):
    """A* search is best-first graph search with f(n) = g(n)+h(n).
    You need to specify the f function when you call astar_search."""
    return best_first_graph_search(puzzle, f)
