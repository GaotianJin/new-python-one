print("hello,word")


# 汉诺塔 3根柱子 n个盘子，把所有盘子一个一个放到别的柱子上，
# 盘子越小越靠上，同理数值越大盘子越大
def hanoti(n, x1, x2, x3):
    if(n == 1):
        print('move1:', x1, '-->', x3, '-->', n)
        print('X1==', x1, 'X2==', x2, 'X3==', x3)
        return
    hanoti(n-1, x1, x3, x2)
    print('move2:', x1, '-->', x3, '-->', n)
    print('X1==', x1, 'X2==', x2, 'X3==', x3)
    hanoti(n-1, x2, x1, x3)
    print('X1==', x1, 'X2==', x2, 'X3==', x3)


hanoti(3, 'A', 'B', 'C')
