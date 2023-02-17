'''
Manohar Chitoda
Suraj Upadhyay
'''
import sys
import numpy as np
from collections import defaultdict

'''
Priority Queue
'''
class PriorityQueue:
    #Initialize the queue instance
    def __init__(self):
        self.queue = []
        
    #Checks if the queue is empty
    def isEmpty(self):
        if(len(self.queue) == 0):
            return True
        else:
            return False
            
    #Returns the size of the queue
    def length(self):
        return len(self.queue)
    
    #Push an element in to the queue according to the y
    # value
    def push(self, element):
        if(self.isEmpty() == False):
            for k in range(0, len(self.queue)):
                if self.queue[k][1] > element[1]:
                    self.queue.insert(k,element)
                    return
        self.queue.append(element)
    
    # Traverse to find an existing element passed
    # if it exists, then pop it and push the parameter
    # element
    def exist(self, element):
        for k in range(0, len(self.queue)):
            if(self.queue[k][0] == element[0]):
                self.queue.pop(k)
                self.push(element)
                return True
        return False
        
    def pop(self):
        return self.queue.pop(0)

'''
Report reflexive vertices
'''

def findReflexiveVertices(polygons):
    vertices=[]
    
    # Your code goes here
    # You should return a list of (x,y) values as lists, i.e.
    # vertices = [[x1,y1],[x2,y2],...]

    
    for plgn_num in range(0, len(polygons)):
        for point_num in range(0, len(polygons[plgn_num])):
            n = polygons[plgn_num][point_num]
			
			#if it is the last point for a particular polygon
            if point_num == len(polygons[plgn_num]) - 1:
                m = polygons[plgn_num][point_num - 1]
                p = polygons[plgn_num][0]
                
            #if it is the first point for a particular polygon
            elif point_num == 0:
                m = polygons[plgn_num][len(polygons[plgn_num]) - 1]
                p = polygons[plgn_num][point_num + 1]
                
            else:
                m = polygons[plgn_num][point_num - 1]
                p = polygons[plgn_num][point_num + 1]
                
            sign = (n[0] - m[0]) * (p[1] - n[1]) - (n[1] - m[1]) * (p[0] - n[0])

            if sign <= 0:
                vertices.append(n)

    return vertices

'''
Compute the roadmap graph
'''
def computeSPRoadmap(polygons, reflexVertices):
    # Your code goes here
    # You should check for each pair of vertices whether the
    # edge between them should belong to the shortest path
    # roadmap. 
    #
    # Your vertexMap should look like
    # {1: [5.2,6.7], 2: [9.2,2.3], ... }
    #
    # and your adjacencyListMap should look like
    # {1: [[2, 5.95], [3, 4.72]], 2: [[1, 5.95], [5,3.52]], ... }
    #
    # The vertex labels used here should start from 1
    
    #Default values used
    index = 1
    vertexMap = dict()
    adjacencyListMap = dict()
    
    #Initialize the vertexMap with ref
    for k in range(0, len(reflexVertices)):
        vertexMap[index] = reflexVertices[k]
        index += 1
    #print (vertexMap[2])
        
    for i in vertexMap:
        adjList = []
        for j in vertexMap:
            if(i == j):
                continue
            if(test(vertexMap, polygons, i, j) == False):
                continue
            adjPosition = []
            adjPosition.append(j)
            
            distance = computeDistance(vertexMap[i],vertexMap[j])
            adjPosition.append(float(distance))
            adjList.append(adjPosition)
        adjacencyListMap[i] = adjList
    return vertexMap, adjacencyListMap

# Calculate if given is a valid in polygon
def calculateSide(sideA, sideB, sideC, polygon):
    counter = 0
    for coordinate in polygon:
        side = (sideA * coordinate[0]) + (sideB * coordinate[1]) + (sideC)
        if(side > 0):
            counter += 1
        elif(side < 0):
            counter -= 1
        else:
            pass

    if(abs(counter) < len(polygon) - 2):
        return False
    else:
        return True
            
# This computes the distaance between two points: (x1,y1) & (x2,y2)
def computeDistance(fromHere, toHere):
    distBetween = np.around(np.sqrt(np.sum(np.power([fromHere[0]-toHere[0], fromHere[1]-toHere[1]], 2))), decimals = 2)
    return float(distBetween)

# Perform a test check of point1 and point2
def test(map, polygons, point1, point2):
    x1 = map[point1][0]
    x2 = map[point2][0]
    y1 = map[point1][1]
    y2 = map[point2][1]
    
    sideA = y2 - y1
    sideB = x1 - x2
    sideC = -(sideA * x1 + sideB * y1)
    
    for p in polygons:
        if (map[point1] in p):
            break
    if (map[point2] in p):
        return calculateSide(sideA, sideB, sideC, p)
    else:
        for pPl in polygons:
            if (map[point2] in pPl):
                break
        return (calculateSide(sideA, sideB, sideC, p) and calculateSide(sideA, sideB, sideC, pPl))

'''
Perform uniform cost search 
'''
def uniformCostSearch(adjListMap, start, goal):
    path = []
    pathLength = 0
    visited = []
    
    # Your code goes here. As the result, the function should
    # return a list of vertex labels, e.g.
    #
    # path = [23, 15, 9, ..., 37]
    # in which 23 would be the label for the start and 37 the
    # label for the goal.

    p_queue = PriorityQueue()
    node = [start, 0, -2]
    p_queue.push(node)
    
    path.append(start)

    while True:
        if p_queue.length() > 0:
            node = p_queue.pop()
            if node[0] == goal:
                path.extend(printResult(visited, node, start))
                path.pop(0)
                path.append(node[0])
                pathLength = node[1]
                return path, pathLength

            visited.append(node)

            for nxtVal in adjListMap[node[0]]:
                child = [nxtVal[0], nxtVal[1] + node[1], node[0]]
                if (not p_queue.exist(child)) and (not isVisited(visited, child)):
                    p_queue.push(child)
        else:
            return path, pathLength

    return path, pathLength

#Check if this vertex was visited or not
def isVisited(vertices, vertex):
    for n in vertices:
        if n[0] == vertex[0]:
            return True
    return False

def printResult(vertices, vertext, start):
    for item in vertices:
        if item[0] == vertext[2]:
            if item[0] == start:
                return start
            return [printResult(vertices, item, start), item[0]]

'''
Agument roadmap to include start and goal
'''
def updateRoadmap(polygons, vertexMap, adjListMap, x1, y1, x2, y2):
    updatedALMap = dict()
    startLabel = 0
    goalLabel = -1

    # Your code goes here. Note that for convenience, we 
    # let start and goal have vertex labels 0 and -1,
    # respectively. Make sure you use these as your labels
    # for the start and goal vertices in the shortest path
    # roadmap. Note that what you do here is similar to
    # when you construct the roadmap. 
    
    vertexMap[startLabel] = [x1,y1]
    vertexMap[goalLabel] = [x2,y2]
    
    for i in [startLabel, goalLabel]:
        updatedALMap[i] = []
        for j in adjListMap:
            updatedALMap[j] = adjListMap[j]
            if(test(vertexMap, polygons, j, i)):
                adjPosition = []
                adjPosition.append(i)
                
                disance = float(computeDistance(vertexMap[j], vertexMap[i]))
                adjPosition.append(disance)
                
                updatedALMap[j].append(adjPosition)
                updatedALMap[i].append([j, disance])
    return startLabel, goalLabel, updatedALMap


if __name__ == "__main__":
    # Retrive file name for input data
    if(len(sys.argv) < 6):
        print("Five arguments required: python spr.py [env-file] [x1] [y1] [x2] [y2]")
        exit()
    
    filename = sys.argv[1]
    x1 = float(sys.argv[2])
    y1 = float(sys.argv[3])
    x2 = float(sys.argv[4])
    y2 = float(sys.argv[5])

    # Read data and parse polygons
    lines = [line.rstrip('\n') for line in open(filename)]
    polygons = []
    for line in range(0, len(lines)):
        xys = lines[line].split(';')
        polygon = []
        for p in range(0, len(xys)):
            polygon.append([float(i) for i in xys[p].split(',')])
        polygons.append(polygon)

    # Print out the data
    print("Pologonal obstacles:")
    for p in range(0, len(polygons)):
        print(str(polygons[p]))
    print("")

    # Compute reflex vertices
    reflexVertices = findReflexiveVertices(polygons)
    print("Reflexive vertices:")
    print(str(reflexVertices))
    print("")

    # Compute the roadmap 
    vertexMap, adjListMap = computeSPRoadmap(polygons, reflexVertices)
    print("Vertex map:")
    print(str(vertexMap))
    print("")
    print("Base roadmap:")
    print(dict(adjListMap))
    print("")

    # Update roadmap
    start, goal, updatedALMap = updateRoadmap(polygons, vertexMap, adjListMap, x1, y1, x2, y2)
    print("Updated roadmap:")
    print(dict(updatedALMap))
    print("")

    # Search for a solution     
    path, length = uniformCostSearch(updatedALMap, start, goal)
    print("Final path:")
    print(str(path))
    print("Final path length:" + str(length))
