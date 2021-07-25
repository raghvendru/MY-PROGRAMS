def cond():
    a=int(input())
    b=int(input())
    c=int(input())
    if a>=b and a>=c:
       print("a is greater")
    if b>=c and b>=a:
       print("b is greater")
    else:
       print("c is greater")

cond()