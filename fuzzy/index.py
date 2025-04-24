import pandas as pd
import numpy as np
import operator
import math
import random

df_full = pd.read_csv("./Iris.csv")
df_full = df_full.drop(['Id'], axis=1)
columns = list(df_full.columns)
features = columns[:len(columns)-1]
class_labels = list(df_full[columns[-1]])
df = df_full[features]

k = 3
MAX_ITER = 100
n = len(df)  # numbers of data points
# fuzzy parameters
m = 1.7

def calculateClusterCenter(membership_matrix):
    cluster_mem_val = list(zip(*membership_matrix))
    cluster_centers = []

    for j in range(k):
        x = list(cluster_mem_val[j])
        xraised = [p**m for p in x]
        denominator = sum(xraised)
        temp_num = []
        for i in range(n):
            data_point = list(df.iloc[i])
            print(data_point)
            prod = [xraised[i] * val for val in data_point]
            temp_num.append(prod)
        numerator = map(sum, list(zip(*temp_num)))
        center = [z / denominator for z in numerator]
        cluster_centers.append(center)
    return cluster_centers


def updateMembershipValue(membership_mat, cluster_centers):
    p = float(2 / (m - 1))
    for i in range(n):
        x = list(df.iloc[i])
        distances = [
            np.linalg.norm(np.array(list(map(operator.sub, x, cluster_centers[j]))))
            for j in range(k)
        ]
        for j in range(k):
            den = sum(
                [math.pow(float(distances[j] / distances[c]), p) for c in range(k)]
            )
            membership_mat[i][j] = float(1 / den)
    return membership_mat


def getClusters(membership_mat):
    cluster_labels = []
    for i in range(n):
        max_val, idx = max((val, idx) for (idx, val) in enumerate(membership_mat[i]))
        cluster_labels.append(idx)
    return cluster_labels


def initializeMembershipMatrix():
    membership_mat = []
    for i in range(n):
        random_num_list = [random.random() for i in range(k)]
        summation = sum(random_num_list)
        temp_list = [x / summation for x in random_num_list]

        flag = temp_list.index(max(temp_list))
        for j in range(0, len(temp_list)):
            if j == flag:
                temp_list[j] = 1
            else:
                temp_list[j] = 0

        membership_mat.append(temp_list)
    return membership_mat


def fuzzyCMeansClustering():  # Third iteration Random vectors from data
    # Membership Matrix
    membership_mat = initializeMembershipMatrix()
    curr = 0
    acc = []
    while curr < MAX_ITER:
        cluster_centers = calculateClusterCenter(membership_mat)
        membership_mat = updateMembershipValue(membership_mat, cluster_centers)
        cluster_labels = getClusters(membership_mat)
        acc.append(cluster_labels)

        if curr == 0:
            print("Cluster Centers:")
            print(np.array(cluster_centers))
        curr += 1
    print("---------------------------")
    print("Partition matrix:")
    print(np.array(membership_mat))
    # return cluster_labels, cluster_centers
    return cluster_labels, cluster_centers, acc


if __name__ == "__main__":
    labels, centers, acc = fuzzyCMeansClustering()