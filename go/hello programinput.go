/*
simple Hello world program
by ityfyme.com
*/

package main

//include library
import "fmt"
func main() {
	var name string
	fmt.Println("please enter your name")
	fmt.Scanln(&name) //
	fmt.Println("hello" + name +"! Welcome to programming world")
}
