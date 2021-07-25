// C# program to convert
// Hexadecimal to Octal
using System;
class GFG{ 
   
// Driver code 
public static void Main(string[] args)
{ 
  int dec = 0;
 
  // taking 1AC as an example 
  // of hexadecimal Number.
  string hexa = "1AC";
  int c = hexa.Length - 1;
 
  // Finding the decimal 
  // equivalent of the
  // hexa decimal number
  for(int i = 0; i < hexa.Length ; i ++ )
  {
    // Extracting each character 
    // from the string.
    char ch = hexa[i];
    switch (ch)
    {
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        dec = dec + Int32.Parse(ch.ToString())*
                               (int)Math.Pow(16, c);
        c--;
        break;
      case 'a':
      case 'A':
        dec = dec + 10 * (int)Math.Pow(16, c);
        c--;
        break;
      case 'b':
      case 'B':
        dec = dec + 11 * (int)Math.Pow(16, c);
        c--;
        break;
      case 'c':
      case 'C':
        dec = dec + 12 * (int)Math.Pow(16, c);
        c--;
        break;
      case 'd':
      case 'D':
        dec = dec + 13 * (int)Math.Pow(16, c);
        c--;
        break;
      case 'e':
      case 'E':
        dec = dec + 14 * (int)Math.Pow(16, c);
        c--;
        break;
      case 'f':
      case 'F':
        dec = dec + 15 * (int)Math.Pow(16, c);
        c--;
        break;
      default:
        Console.Write("Invalid hexa input");
        break;
    }
  }
 
  // String oct to store the octal 
  // equivalent of a hexadecimal number.
  string oct = "";
 
  // converting decimal 
  // to octal number.
  while(dec > 0)
  {
    oct = dec % 8 + oct;
    dec = dec / 8;
  }
 
  // Printing the final output.
  Console.Write("Equivalent Octal Value = " + 
                 oct);
}
}