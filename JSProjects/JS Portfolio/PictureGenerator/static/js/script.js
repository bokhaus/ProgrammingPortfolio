//Challenge 1: Age in days
// DOM - Document Object Model

function ageInDays() {
    let birthYear = prompt('What year were you born?');
    let numOfDays = (2020 - birthYear) * 365;
    let h1 = document.createElement('h1'); //creates a new h1 element
    let textAnswer = document.createTextNode('You are ' + numOfDays + ' days old.'); // Completes calulation
    h1.setAttribute('id', 'daysOld'); // sets h1 attributes
    h1.appendChild(textAnswer); // adds the answer to the h1
    document.getElementById('flex-box-result').appendChild(h1); // puts h1 into flexbow result <div>
    
}

// Reset Function
function reset() {
    document.getElementById('daysOld').remove();
    
}

function generatePic(){
    let image = document.createElement('img');
    let div = document.getElementById('flex-pic-gen');
    image.setAttribute('id', 'allPics');
    image.src = "static/images/airplaneSunset.jpg";
    div.appendChild(image);
}

// Reset Function - removes one picture at a time.
function resetTwo() {
    document.getElementById('allPics').remove();
    
}