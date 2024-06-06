/*main beginning point*/
$(document).ready( () => {
    load_calculator_page();
    click_buttons();

});

function load_calculator_page() {
    $("#calculator").load('calculator.html');
}

function click_buttons() {
    let buttons_parent = document.querySelectorAll('.calculator > buttons');
    print(buttons_parent)
}