def cond():
    x=int(input())
    y=int(input())
    if x>0 and y>0:
       print("first q")
    if x<0 and y<0:
       print("fourth")
    if x<0 and y>0:
       print("second")
    if x>0 and y<0:
       print("third")

cond()