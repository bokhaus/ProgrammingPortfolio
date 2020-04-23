/* 
https://www.youtube.com/watch?v=Qqx_wzMmFeA

Example of code connecting to HTML
console.log("hello");
alert("Woohoo, I connected the JS file to the HTML file."); */

// Or comment this way as well

/* Variables
var b = "Smoothie";
console.log(b);

var someNumber = 45
console.log(someNumber); */

/* Changing Data in HTML file using tag id
document.getElementById('testHeader').innerHTML = 'Test program!!!';
document.getElementById('someText').innerHTML = 'Hello World'; */

/* Concatenate variable with id tag
var age = prompt('What is your age');
document.getElementById('someText').innerHTML = 'Your age is ' + age; */

// Manipulate DOM with JavaScript
/*... Fancy way of saying
change HTML with JavaScript*/

//var age = prompt('What is your age');
//document.getElementById('someText').innerHTML =  age;

/*
// Numbers in JavaScript
var num1 = 10;
console.log(num1);
///console.log(5*10);

//console.log(50/5);

//Increment num1 by 1
//num1 = num1 + 1;
num1++;
console.log(num1);
//console.log(60/num1); 

//Decrement num1 by 1
num1--;
console.log(num1);

// Divide, multiple *, remainder %
console.log(num1 % 5);
console.log(num1 % 6);


//Increment or decrement by any number you want (i.e. 10)
num1+=10;
console.log(num1);

*/

/*Functions
1. Create a function
2. Call the function
*/

/*
//Example One
//Create

function fun1(){
    alert('This is a function');
    console.log('This is a function');
}

//Call function
fun1();
*/

//Functions should do multiple things

//Example Two
/* Create a function which takes in a name and 
says 'hello' followed by the name

For example

Name: "Brian"
Return: "Hello, Brian"
*/

/*
// Create function
function greeting(){
    var name = prompt('What is your name?');
    var result = 'Hello ' + name; //String concatenation
    console.log(result);
}

// Call Function
//greeting();

//How do functions work in functions?
//How do we add two numbers together in a function?

function sumNumbers(num1, num2) {
    
    console.log(num1 + num2); // You can sum the numbers this way 
    
    //or use inputs and call the variable.
    var result = num1 + num2;
    console.log(result);
}

sumNumbers(3,7);

//Example Three - ****Best way****
// Used when a function is dependent on a piece of information
// Create function
function greeting(yourName){ //Uses user input 
    var result = 'Hello ' + yourName; //String concatenation
    console.log(result);
}

var name = prompt('What is your name?'); //Prompts for name
    
// Call Function
greeting(name); //Uses name as input into function

*/

// While vs For Loops 

/*While Loops

var num = 0;

// Should always have a break statement
while (num<100) {
    num += 1;
    console.log(num);

}
*/
/*
//For Loops
// 'let' is same thing as 'var' - 'let' is better way
for(let num = 0; num<100; num++) {
    console.log(num);
}  
*/

/*
//Data types
let yourAge = 18;                                   //number
let yourName = 'Bob';                               //string
let name = {first: 'Jane', last: 'Doe'};            //object
let truth = false;                                  //boolean
let groceries = ['apple', 'banana', 'oranges'];     //array
let random;                                         //undefined
let nothing = null;                                 //value null

*/

/* //Strings in JavaScript (Common Methods)
let fruit = 'banana';
let fruitBerry = 'blueberry, strawberry, black berry, raspberry'
let moreFruit = 'banana\napple'; //puts new line between banana and apple '\n' is an escape character.
console.log(moreFruit);
console.log('Gives the number of characters in the variable fruit = ' + fruit.length); //Gives the number of characters in the variable fruit

console.log(fruit.indexOf('an')); //Finds the index of an starting at 1

console.log(fruit.indexOf('nan')); //Finds the index starting at 2

console.log(fruit.slice(2,6)); //creates a segnment of characters starting at index 2 up to index 6.

console.log(fruit.replace('ban', '123')); //Find and replace - find ban and replace with 123.

console.log(fruit.toUpperCase()); //Upper case

console.log(fruit.toLowerCase()); //Lower case

console.log(fruit.charAt(2)); // Get the character at the index of 2

console.log(fruit[2]); // Get the character at the index of 2 (same as previous)

console.log(fruit.split('')); //Splits the sting by character and creates an array which includes each character

console.log(fruitBerry.split(',')); // Splits string on comma and creates an array

 */

/* Arrays in JavaScript 
let fruits = ['banana', 'apple', 'orange', 'pineapples']; //Array

fruits = new Array ('banana', 'apple', 'orange', 'pineapples'); 

//alert(fruits[1]); // Selects index 1 element (apple)

//console.log(fruits[2]); //Access value at index 2

fruits[0] = 'pear'; //Change the value of index0 (banana to pear)

console.log(fruits);

//Loop Arrays

for (let i = 0; i< fruits.length; i++){ //.length refers to thenumber of items in the array
    console.log(fruits[i]); //Prints the array using for loop
}

//Array Common Methods 

//Convert an Array to a string
console.log('to string', fruits.toString()); //Print the text and then print other string from array. Two different statements separated by comma

console.log('Print this statement.', 'Then print this statement.'); //Similar to concatenation

console.log(fruits.join('-')); // joins each element in the array by a hyphen

console.log(fruits.join('*')); // joins elements using a *

//Prints fruits array, removes last item and returns to screen, prints fruits array with last item removed.
console.log(fruits, fruits.pop(), fruits); //removes last item in the array (pineapple)

//Push
console.log(fruits.push('blackberries'), fruits); // appends(adds) item to array.

console.log(fruits[3]); // selects index 3 and prints (blackberries)

//add item to the array

fruits[4] = 'new fruit one'; //basically adds new fruit item. 
console.log(fruits); //prints fruits array with 'new item' in the array list

//Clever way to add to array
fruits[fruits.length] = 'new fruit two'; //same as push
console.log(fruits); //prints new fruit item

//remove first element from an array - Very expensive (resource eater)
fruits.shift();
console.log(fruits);

//add first element to an array
fruits.unshift('kiwi');
console.log(fruits);

//Multiple Arrays
let vegetables = ['asparagus', 'tomato', 'broccoli'];
let allGroceries = fruits.concat(vegetables); //combines two arrays.
console.log(allGroceries);

// Slice array
console.log(allGroceries.slice(1,4)); //slices off 3 items of the new array, starting at index 1.

//Reverse an Array
console.log(allGroceries.reverse());

//Sort a String Array
console.log(allGroceries.sort()); //alphabetical order

//Sort Number Array
let someNumbers = [5, 10, 2, 25, 3, 255, 1, 2, 5, 334, 321, 2];
console.log(someNumbers.sort(function(a,b){return a-b})); //ascending order

console.log(someNumbers.sort(function(a,b){return b-a})); //decending order

//Array using for loop - COMMON
let emptyArray = new Array();
for(let num=0; num<=10; num++){ //for loop
    emptyArray.push(num); //append the array from 0-10 bc num <= 10
}
console.log(emptyArray); //print the array

*/

/* 
//Objects in JavaScript


let student = { //object has keys
    first: 'Brian', 
    last: 'Bok', 
    age: 48, 
    height: 68, 
    studentInfo: function(){
        return this.first + '\n' + this.last + '\n' + this.age;
    }
    //keep comma as good practice
}; //object formatted to better understand.

//console.log(student.first); //print first name to console
//student.first = 'notBrian'; //change value
//console.log(student.first);
//console.log(student.last); //print last name to console
//use key value pair to work on the object
//student.age++; //increment age by one
//console.log(student.age); //adds 1 to the current age
console.log(student.studentInfo());
*/


/* IF ELSE, Conditionals and SWITCH Statements (Control Flow)

// Target demographic for my program is 18-35
// && Logical AND - Boolean
// || Logical OR - Boolean


// IF ELSE
//let age = prompt('What is your age?');
let age = 45;
if( (age>=18) && (age <= 35) ) {
    status = 'target demo';
    console.log(status);
}else{
    status = 'Not target audiance';
    console.log(status);
}

// Switch Statements
// if everyday was a week day
// differentiate between weekday vs. weekend
// day 0 --> Sunday --> weekend
// day 1 --> Monday --> weekday
// day 2 --> Tuesday --> weekday
// day 3 --> Wednesday --> weekday
// day 4 --> Thursday --> weekday
// day 5 --> Friday --> weekday
// day 6 --> Saturday --> weekend
// Use a switch in place of IF ELSE statements
switch(4){
    case 0:
        text = 'weekend';
        break;
    case 5:
        text = 'weekend';
        break;
    case 6:
        text = 'weekend';
        break;
    default: 
        text = 'weekday';
}

console.log(text);

*/

/* Learning JSON */

console.log('Hello, this is working');
















































