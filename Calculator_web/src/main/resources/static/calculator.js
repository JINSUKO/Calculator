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
    let mathemeatical_expression = input_display.value;


    console.dir(input_display);
    console.log(input_display);

    const nums = [`0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`];
    const operators = [`+`, `-`, `x`, `÷`];

    buttons_parent[0].addEventListener("click", (e) => {

        let value = e.target.innerText;

        if (e.target.innerText === "") {
            value = e.target.getAttribute('alt');
        }

        console.log(value);

        let tmp = "";
        let char_last = mathemeatical_expression.substring(mathemeatical_expression.length - 1, mathemeatical_expression.length);

        if (isNaN(value)) {
            if( value === 'delete') {
                if (mathemeatical_expression.length == 0) {

                } else {
                    mathemeatical_expression = mathemeatical_expression.substring(0, mathemeatical_expression.length - 1);
                }

            } else if (['÷', 'x', '-', '+'].includes(value)) {
                if (isNaN(char_last)) {

                } else {
                    mathemeatical_expression += value;
                }
            } else if (value === '.') {
                // 연산자를 기준으로 숫자를 나눠서 이 값에 .이 들어 있으면 .이 또 들어는 것을 제한하는 코드.
                const regex_operator = /[÷x\-+]/;
                const expression_list = mathemeatical_expression.split(regex_operator);
                const len_expression_list = expression_list.length;

                if (expression_list[len_expression_list - 1].includes('.')) {
                    value = '';
                }

                if (['÷', 'x', '-', '+'].includes(char_last) || mathemeatical_expression.length == 0) {
                    value = '0' + value;
                    mathemeatical_expression += value;
                } else if (isNaN(char_last)) {

                } else {
                    mathemeatical_expression += value;
                }
            }
        } else {
            mathemeatical_expression += value;
        }

        console.log(mathemeatical_expression);

        input_display.value = mathemeatical_expression;
    });
}