import random
import math
import time 

import numpy as np
import matplotlib.pyplot as plt
from IPython.display import clear_output


class TSP:
    """Class representing a Traveling Salesperson Problem instance"""
    
    def __init__(self, num_cities=25, mode='random', size=100):
        """
        Initialize TSP problem instance
        
        Args:
            num_cities: Number of cities
            mode: How to generate cities ('random', 'circle', 'grid', 'clustered')
            size: Size of the coordinate space
        """
        self.num_cities = num_cities
        self.size = size
        
        # Generate city coordinates
        if mode == 'random':
            self.cities = self._generate_random_cities()
        elif mode == 'circle':
            self.cities = self._generate_circle_cities()
        elif mode == 'grid':
            self.cities = self._generate_grid_cities()
        elif mode == 'clustered':
            self.cities = self._generate_clustered_cities()
        else:
            raise ValueError(f"Unknown mode: {mode}")
            
        # Calculate distance matrix
        self.distances = self._calculate_distance_matrix()
    
    def _generate_random_cities(self):
        """Generate cities with random coordinates"""
        return [(random.uniform(0, self.size), random.uniform(0, self.size)) 
                for _ in range(self.num_cities)]
    
    def _generate_circle_cities(self):
        """Generate cities arranged in a circle"""
        radius = self.size / 2
        center = (self.size / 2, self.size / 2)
        cities = []
        
        for i in range(self.num_cities):
            angle = 2 * math.pi * i / self.num_cities
            x = center[0] + radius * math.cos(angle)
            y = center[1] + radius * math.sin(angle)
            cities.append((x, y))
            
        return cities
    
    def _generate_grid_cities(self):
        """Generate cities arranged in a grid pattern"""
        grid_size = math.ceil(math.sqrt(self.num_cities))
        spacing = self.size / (grid_size + 1)
        cities = []
        
        for i in range(grid_size):
            for j in range(grid_size):
                if len(cities) < self.num_cities:
                    x = spacing * (i + 1)
                    y = spacing * (j + 1)
                    # Add some noise to avoid perfect grid
                    x += random.uniform(-spacing/5, spacing/5)
                    y += random.uniform(-spacing/5, spacing/5)
                    cities.append((x, y))
                    
        return cities
    
    def _generate_clustered_cities(self):
        """Generate cities in clusters"""
        num_clusters = random.randint(2, 5)
        cities = []
        
        # Generate cluster centers
        cluster_centers = [(random.uniform(0.1*self.size, 0.9*self.size), 
                            random.uniform(0.1*self.size, 0.9*self.size))
                           for _ in range(num_clusters)]
        
        # Generate cities around cluster centers
        cities_per_cluster = [self.num_cities // num_clusters] * num_clusters
        # Distribute remaining cities
        for i in range(self.num_cities % num_clusters):
            cities_per_cluster[i] += 1
            
        for i, center in enumerate(cluster_centers):
            cluster_radius = random.uniform(0.05*self.size, 0.15*self.size)
            for _ in range(cities_per_cluster[i]):
                angle = random.uniform(0, 2 * math.pi)
                distance = random.uniform(0, cluster_radius)
                x = center[0] + distance * math.cos(angle)
                y = center[1] + distance * math.sin(angle)
                # Keep within bounds
                x = max(0, min(self.size, x))
                y = max(0, min(self.size, y))
                cities.append((x, y))
                
        return cities
    
    def _calculate_distance_matrix(self):
        """Calculate the distance matrix between all cities"""
        n = len(self.cities)
        distances = np.zeros((n, n))
        
        for i in range(n):
            for j in range(i+1, n):
                # Euclidean distance
                dx = self.cities[i][0] - self.cities[j][0]
                dy = self.cities[i][1] - self.cities[j][1]
                distance = math.sqrt(dx*dx + dy*dy)
                
                # Store in both positions (symmetric matrix)
                distances[i, j] = distance
                distances[j, i] = distance
                
        return distances
    
    def route_distance(self, route):
        """
        Calculate the total distance of a route
        
        Args:
            route: List of city indices representing a route
            
        Returns:
            Total distance of the route (including return to start)
        """
        total_distance = 0
        n = len(route)
        
        for i in range(n - 1):
            total_distance += self.distances[route[i], route[i+1]]
            
        # Add distance back to starting city
        total_distance += self.distances[route[-1], route[0]]
        
        return total_distance
    
    def plot_cities(self, title=None):
        """Plot the cities"""
        plt.figure(figsize=(10, 10))
        x = [city[0] for city in self.cities]
        y = [city[1] for city in self.cities]
        
        plt.scatter(x, y, s=100, c='blue', edgecolors='black')
        
        # Label cities with indices
        for i, (x_i, y_i) in enumerate(zip(x, y)):
            plt.annotate(str(i), (x_i, y_i), fontsize=12, 
                         xytext=(5, 5), textcoords='offset points')
        
        plt.xlim(0, self.size)
        plt.ylim(0, self.size)
        plt.grid(True, linestyle='--', alpha=0.7)
        
        if title:
            plt.title(title, fontsize=16)
        else:
            plt.title(f"{self.num_cities} Cities", fontsize=16)
            
        plt.show()
    
    def plot_route(self, route, title=None):
        """Plot a route between the cities"""
        plt.figure(figsize=(10, 10))
        
        # Extract coordinates for the route
        x = [self.cities[city][0] for city in route]
        y = [self.cities[city][1] for city in route]
        
        # Add starting city at the end to complete the loop
        x.append(self.cities[route[0]][0])
        y.append(self.cities[route[0]][1])
        
        # Plot cities
        plt.scatter([city[0] for city in self.cities], [city[1] for city in self.cities], 
                    s=100, c='blue', edgecolors='black')
        
        # Plot route
        plt.plot(x, y, 'r-', linewidth=2, alpha=0.75)
        
        # Label cities with indices
        for i, (x_i, y_i) in enumerate(self.cities):
            plt.annotate(str(i), (x_i, y_i), fontsize=12, 
                         xytext=(5, 5), textcoords='offset points')
        
        plt.xlim(0, self.size)
        plt.ylim(0, self.size)
        plt.grid(True, linestyle='--', alpha=0.7)
        
        distance = self.route_distance(route)
        
        if title:
            plt.title(f"{title} - Distance: {distance:.2f}", fontsize=16)
        else:
            plt.title(f"Route - Distance: {distance:.2f}", fontsize=16)
            
        plt.show()
        