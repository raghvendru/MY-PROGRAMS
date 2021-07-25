def cond():
    n=int(input())
    a=int(input())
    p=(a/n)*100
    if p>=75:
       print("allowed")
    else:
       print("not allowed")

cond()