public class App {

    public static void main(String[] args) throws Exception {
        Frame frame = new Frame(300, 200);
        frame.setDimensionSize(300, 400);

        frame.makeCalculator();

    }
}
// 계산기 숫자를 연산자로 split으로 나누는 것까지 했음.
// 연산자 우선순위에 따라 계산하는 코드 작성해야 함.
// 2561-456468*4154568+456465/1541

// 연산자 인덱스로 연산자 뽑음 -*+/
// + 연산자 인덱스 앞에꺼 먼저 계산`
// - 연산자 인덱스
// * 연산자 인덱스 먼저 계산 앞에 꺼 먼저 계산
// / 연산자 인덱스 먼저 계산


// 리스트에 피연산자 넣음 [2561, 156468,4154568,456465,1541]