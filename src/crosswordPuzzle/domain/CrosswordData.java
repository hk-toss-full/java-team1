package crosswordPuzzle.domain;

import java.util.HashMap;
import java.util.Map;

public class CrosswordData {

    // 십자말풀이 문제와 답을 저장하는 클래스
    private final Map<String, CrosswordLevelData> crosswordDataMap = new HashMap<>();

    public CrosswordData() {
        // 십자말풀이 문제 데이터를 각 레벨별로 설정
        crosswordDataMap.put("easy", new CrosswordLevelData(
                new String[]{"타임머신", "사시나무", "지도", "서당", "릿성", "발행성오찬", "주인공", "설악산", "성격", "편지", "설거지"},
                new String[]{"[0,0] 타임을 여행하는 장치", "[3,1] 바람에 떨리는 나무", "[0,2] 지도에서 임지의 위치", "[2,3] 서당에 대한 설명", "[4,0] '성(性)'의 조각", "[5,0] 발행된 성과 오찬", "[3,6] 이야기의 중심 인물", "[1,7] 대한민국의 유명한 산", "[3,8] 사람의 성품", "[0,9] 소식을 전하는 글", "[1,7] 그릇을 닦는 행동"},
                new int[][]{
                        {0, 0}, {1, 1}, {2, 0}, {3, 1}, {4, 0},
                        {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}
                },
                new String[]{"HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "HORIZONTAL", "VERTICAL"}
        ));

    }

    // 레벨에 따른 십자말풀이 데이터 반환
    public CrosswordLevelData getCrosswordData(String level) {
        return crosswordDataMap.get(level);
    }

    // 내부 클래스: 레벨별 십자말풀이 데이터를 저장
    public static class CrosswordLevelData {
        private final String[] words;
        private final String[] descriptions;
        private final int[][] positions;
        private final String[] directions;

        public CrosswordLevelData(String[] words, String[] descriptions, int[][] positions, String[] directions) {
            this.words = words;
            this.descriptions = descriptions;
            this.positions = positions;
            this.directions = directions;
        }

        public String[] getWords() {
            return words;
        }

        public String[] getDescriptions() {
            return descriptions;
        }

        public int[][] getPositions() {
            return positions;
        }

        public String[] getDirections() {
            return directions;
        }
    }
}
