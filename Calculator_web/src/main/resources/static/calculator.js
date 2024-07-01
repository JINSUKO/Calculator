/*main beginning point*/
$(document).ready( () => {
    load_calculator_page();
});

function load_calculator_page() {
    $("#calculator").load('calculator.html', () => {
        click_buttons_event();
        press_number_pad_event();
    });
}

// 모든 함수 내부에서 textarea의 동일한 인스턴스를
// 사용할 수 있도록 전역 변수로 설정함.
let mathemeatical_expression;
function click_buttons_event() {
    const buttons_parent = document.querySelectorAll('.calculator > .buttons');
    let input_display = document.querySelector(`.calculator > .display-container > .display`);
    mathemeatical_expression = input_display.value;


    buttons_parent[0].addEventListener("click", (e) => {
        mathemeatical_expression = make_mathemeatical_expression(e.target, mathemeatical_expression, input_display);
    });
}

function press_number_pad_event() {
    let arithmatic_btns = document.querySelectorAll(`.calculator > .buttons > .arithmatic-btn`);
    let input_display = document.querySelector(`.calculator > .display-container > .display`);
    mathemeatical_expression= input_display.value;

    const btn_test_list = [];

    arithmatic_btns.forEach((e) => {btn_test_list.push(e.textContent);});
    arithmatic_btns = Array.from(arithmatic_btns);

    document.addEventListener('keypress', (e) => { // 누르고 있을 때 이벤트 발생
        let num = e.key;
        num = (num === '/') ? '÷' : num;

        const carithmatic_btn = arithmatic_btns.filter( e => e.textContent === num);

        if (carithmatic_btn.length) {
            // 숫자와 연산자는 다른 효과를 주도록 css 로 설정함.
            carithmatic_btn[0].classList.add('button-press');

        }
    });

    document.addEventListener('keyup', (e) => { // 버큰을 땔 때 이벤트 발생
        let num = e.key;
        num = (num === '/') ? '÷' : num;

        const carithmatic_btn = arithmatic_btns.filter( e => e.textContent === num);

        if (carithmatic_btn.length) {
            // 숫자와 연산자는 다른 효과를 주도록 css 로 설정함.
            carithmatic_btn[0].classList.remove('button-press');

            // console.log(e.target);
            mathemeatical_expression = make_mathemeatical_expression(carithmatic_btn[0], mathemeatical_expression, input_display);
        }
    });
}

/** target: tag element*/
function make_mathemeatical_expression(target, mathemeatical_expression, input_display) {
    let value = target.innerText;

    if (target.innerText === "") {
        value = target.getAttribute('alt');
    }

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
    input_display.value = mathemeatical_expression;

    // textarea 영역의 크기를 넘을 때, 스크롤을 내리는 코드.
    input_display.scrollTop = input_display.scrollHeight;

    return mathemeatical_expression;
}