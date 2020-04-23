// Change the color of all buttons.
let all_buttons = document.getElementsByTagName('button'); // returns all buttons with the tag - button.

let copyAllButtons = []; //Copy state of button colors before change in the array data structure. preserves order.
for(let i=0; i< all_buttons.length; i++) {
    copyAllButtons.push(all_buttons[i].classList[1]);  //Loop through all buttons and get the button's second class then push to current state to the array
}
console.log(copyAllButtons);

//Main controller function
function buttonColorChange(colorSelect) { //Used in HTML onchange in form.
   if(colorSelect.value == 'red') {
       buttonsRed();
   }else if (colorSelect.value == 'green') { 
       buttonsGreen();
   }else if (colorSelect.value == 'yellow') {
       buttonsYellow();
   }else if (colorSelect.value == 'reset') {
       buttonsReset();
   }else if (colorSelect.value == 'random') {
       randomColors();
   }else if (colorSelect.value == 'no style') {
       noColor();
   }
   console.log(colorSelect.value);
}

// Functions to change colors and reset to original
function buttonsRed() {
    for (let i=0; i< all_buttons.length; i++) {
        all_buttons[i].classList.remove(all_buttons[i].classList[1]); // got to each button, access class list and remove the second class. 
        all_buttons[i].classList.add('btn-danger'); //add class to all buttons.
    }
}

function buttonsGreen() {
    for (let i=0; i< all_buttons.length; i++) {
        all_buttons[i].classList.remove(all_buttons[i].classList[1]); // got to each button, access class list and remove the second class. 
        all_buttons[i].classList.add('btn-success'); //add class to all buttons.
    }
}

function buttonsYellow() {
    for (let i=0; i< all_buttons.length; i++) {
        all_buttons[i].classList.remove(all_buttons[i].classList[1]); // got to each button, access class list and remove the second class. 
        all_buttons[i].classList.add('btn-warning'); //add class to all buttons.
    }
}

function buttonsReset() {
    for (let i=0; i< all_buttons.length; i++) {
        all_buttons[i].classList.remove(all_buttons[i].classList[1]); // got to each button, access class list and remove the second class. 
        all_buttons[i].classList.add(copyAllButtons[i]); //add copy of class to all buttons.
    }
}

function randomColors() {
    for (let i=0; i< all_buttons.length; i++) {
        all_buttons[i].classList.remove(all_buttons[i].classList[1]); // got to each button, access class list and remove the second class. 
        all_buttons[i].classList.add('btn-warning'); //add class to all buttons.
    }
}

function noColor() {
    for (let i=0; i< all_buttons.length; i++) {
        all_buttons[i].classList.remove(all_buttons[i].classList[1]); // got to each button, access class list and remove the second class. 
     }
}

function randomColors() {
    let choices = ['btn-primary', 'btn-danger', 'btn-success', 'btn-warning']

    for (let i=0; i< all_buttons.length; i++) {
        /* randomNumber used inside of the loop or it would store the random number once and continually 
        use this number and not calculate it again.*/
        let randomNumber = Math.floor(Math.random() * 4);
        all_buttons[i].classList.remove(all_buttons[i].classList[1]); // got to each button, access class list and remove the second class. 
        all_buttons[i].classList.add(choices[randomNumber]); //add class to all buttons.
    }
}


