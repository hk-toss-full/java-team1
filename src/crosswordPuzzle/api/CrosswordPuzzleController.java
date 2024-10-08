package crosswordPuzzle.api;

import crosswordPuzzle.application.CrosswordPuzzleService;
import crosswordPuzzle.domain.CrosswordDirection;
import crosswordPuzzle.utils.TimerThread;

import java.util.Scanner;

public class CrosswordPuzzleController {
    private final CrosswordPuzzleService crosswordService;
    private final Scanner scanner;

    // 생성자에서 CrosswordService 객체와 Scanner 객체를 초기화
    public CrosswordPuzzleController(CrosswordPuzzleService crosswordService) {
        this.crosswordService = crosswordService;
        this.scanner = new Scanner(System.in);
    }

    // 십자말풀이 게임을 실행하고 사용자 입력을 처리
    public void run() {
        crosswordService.startGame();  // 게임 시작 및 보드 출력

        TimerThread timerThread = new TimerThread(1200);
        timerThread.start();

        while (true) {
            System.out.println("옵션을 선택하세요: ");
            System.out.println("1. 단어 입력");
            System.out.println("2. 힌트 사용");
            System.out.println("3. 게임 종료");

            if (timerThread.isTimeUp()) {
                System.out.println("제한 시간이 모두 지났습니다.");
                return;
            }

            int option = scanner.nextInt();
            scanner.nextLine();  // 개행 문자 처리

            switch (option) {
                case 1:
                    insertWordByNumber();
                    break;
                case 2:
                    useHintByNumber();
                    break;
                case 3:
                    System.out.println("게임을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 옵션입니다. 다시 시도하세요.");
                    break;
            }

            if (crosswordService.isGameCompleted()) {
                System.out.println("모든 단어를 맞췄습니다! 게임을 성공적으로 완료하였습니다.");
                break;
            }

        }
        timerThread.stopTimer();
    }


    // 단어 입력 옵션
    private void insertWordByNumber() {
        System.out.print("몇 번째 문제의 단어를 입력하시겠습니까? (번호 입력): ");
        int problemNumber = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        System.out.print("단어를 입력하세요: ");
        String word = scanner.nextLine();

        CrosswordDirection direction = getDirectionFromUser();

        if (direction == null) {
            System.out.println("올바른 방향이 입력되지 않았습니다. 단어 입력을 취소합니다.");
            return;
        }

        boolean success = crosswordService.insertWord(problemNumber, word, direction);
        if (success) {
            System.out.println("단어가 보드에 성공적으로 삽입되었습니다.");
        } else {
            System.out.println("단어를 보드에 삽입할 수 없습니다. 다시 시도하세요.");
        }

        crosswordService.startGame();  // 현재 보드와 설명을 다시 출력
    }

    private CrosswordDirection getDirectionFromUser() {
        System.out.print("단어의 방향을 선택하세요 (1: 가로, 2: 세로) : ");
        int directionInput = scanner.nextInt();
        scanner.nextLine();

        switch (directionInput) {
            case 1:
                return CrosswordDirection.HORIZONTAL;
            case 2:
                return CrosswordDirection.VERTICAL;
            default:
                System.out.println("잘못된 입력입니다. 올바른 값을 입력하세요.");
                return null;
        }
    }

    // 문제 번호를 입력받아 힌트 사용
    private void useHintByNumber() {
        System.out.print("몇 번째 문제의 힌트를 사용하시겠습니까? (번호 입력): ");
        int problemNumber = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        crosswordService.useHint(problemNumber); // 특정 문제 번호에 대한 힌트 사용
    }

}