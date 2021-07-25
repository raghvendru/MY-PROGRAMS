def cond():
    p=int(input())
    m=int(input())
    c=int(input())
    s=(p+c+m/3)
    if p>=75 and c>=75 and m>=75 and s:
       print("elig")
    else:
       print("not elig")

cond()