def cond():
  print ("Enter quantity")
  quantity = input()
  if (quantity*100 > 1000):
   print ("Cost is",quantity*100*0.9)
  else:
   print ("Cost is",quantity*100)
  
cond()