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
    const buttons_parent = document.querySelectorAll('.calculator > .arithmetic-buttons');
    let input_display = document.querySelector(`.calculator > .display-container > .display`);
    let mathemeatical_expression = "";

    console.log(input_display);

    const nums = [`0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`];
    const operators = [`+`, `-`, `x`, `÷`];

    buttons_parent[0].addEventListener("click", (e) => {

        let value = e.target.innerText;

        if (e.target.innerText == "") {
            value = e.target.getAttribute('alt');
        }

        console.log(value);

        let tmp = "";

        for (const num of nums) {
            if (value == num) {
                mathemeatical_expression += num;
            }
        }

        console.log(mathemeatical_expression);

        input_display.value = mathemeatical_expression;
    });
}