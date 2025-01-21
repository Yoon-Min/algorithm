import sys

def get_distance(a, b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])


left_hand_pos, right_hand_pos = sys.stdin.readline().split()
input = sys.stdin.readline().strip()

keyboard = [
    ['q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'],
    ['a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'],
    ['z', 'x', 'c', 'v', 'b', 'n', 'm'],
]
korean_consonant_keys =  {'q', 'w', 'e', 'r', 't', 'a', 's', 'd', 'f', 'g', 'z', 'x', 'c', 'v'}
korean_vowel_keys = {'y', 'u', 'i', 'o', 'p', 'h', 'j', 'k', 'l', 'b', 'n', 'm'}

coordinates = {}
for i, inner_list in enumerate(keyboard):
    for j in range(len(inner_list)):
        key = keyboard[i][j]
        value = (i,j)
        coordinates[key]= value

left_hand_pos = coordinates[left_hand_pos]
right_hand_pos = coordinates[right_hand_pos]
totalTime = 0
for c in input:
    next_coordinate = coordinates[c]
    distance = 0
    if c in korean_consonant_keys:
        distance = get_distance(left_hand_pos, next_coordinate)
        left_hand_pos = next_coordinate
    elif c in korean_vowel_keys:
        distance = get_distance(right_hand_pos, next_coordinate)
        right_hand_pos = next_coordinate
    totalTime += distance + 1

print(totalTime)




