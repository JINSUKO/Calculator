/*main beginning point*/
$(document).ready( () => {
    load_calculator_page();
});

function load_calculator_page() {
    $("#calculator").load('calculator.html', () => {
        click_buttons();

    });
}

function click_buttons() {
    let buttons_parent = document.querySelectorAll('.calculator > .arithmetic-buttons');

    buttons_parent[0].addEventListener("click", (e) => {
       console.log(e.target.innerText);
    });
}