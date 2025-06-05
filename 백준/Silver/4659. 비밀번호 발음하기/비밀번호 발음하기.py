mother = {'a', 'e', 'i', 'o', 'u'}

while(True):
    str = input()

    if str == "end":
        break

    mother_count = 0
    child_count = 0
    continuous_count = 0
    continuous = ''
    prev = ''

    is_acceptable = True

    for i in range(len(str)):
        cur = str[i]

        if prev == cur and cur not in ('e','o'):
            is_acceptable = False
            break

        if cur in mother:
            mother_count += 1

        is_continuous_mother = prev in mother and cur in mother
        is_continuous_child = prev not in mother and cur not in mother

        if is_continuous_mother or is_continuous_child:
            continuous_count += 1
        else:
            continuous_count = 1

        if(continuous_count == 3):
            is_acceptable = False
            break

        prev = cur
        continuous = cur
    
    if mother_count == 0:
        is_acceptable = False

    if is_acceptable:
        print(f"<{str}> is acceptable.")
    else:
        print(f"<{str}> is not acceptable.")
    