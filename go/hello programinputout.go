/*
simple Hello world program
by ityfyme.com
*/

package main

//include library
import (
	   "fmt"
       "os"
) 
func main() {
	fmt.Println("hello" + os.Args[1] +"! Welcome to programming world")
}
 