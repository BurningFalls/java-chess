# 1단계 - 체스판 초기화

- 체스판은 8*8 = 64 크기로 초기화한다.
  - 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8이다.
- white, black 팀 각각 최초에 16개의 말로 이루어진 Pieces를 가진다.
  - 말 1세트는 King: 1개, Queen: 1개, Rook: 2개, Knight: 2개, Bishop: 2개, Pawn: 8개로 이루어진다.
- 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.
- 빈칸은 `.`으로 표현한다.
- 초기 말의 위치는 아래와 같다.
  ```
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
  ```

# 2단계 - 말 이동

- 각 말은 이동할 수 있는 범위가 다르다.
- 나이트를 제외하고는 가는 길에 다른 기물이 있다면 이동할 수 없다. 
  (단, 목표 위치가 상대방 말인 경우 말을 잡고 해당 위치로 움직일 수 있다.)  
    - 킹
        - 8방향(십자가, 대각선)으로 1칸 움직일 수 있다
    - 퀸
        - 8방향(십자가, 대각선)으로 체스판 어디든 움직일 수 있다
    - 룩
        - 4방향(십자가)으로 어디든 움직일 수 있다
    - 나이트
        - `L` 방향으로 움직인다. 대각선 1칸 위치를 기준으로 바깥쪽 십자가 방향으로 움직일 수 있다.
        - 다른 기물이 있어도 움직일 수 있다.
    - 비숍
        - 4방향(대각선)으로 어디든 움직일 수 있다.
    - 폰
        - 전방에 기물이 없을 때, 기본적으로 앞으로 한 칸 움직일 수 있다.
        - 최초에 폰이 움직이지 않았다면 전방으로 2칸 움직일 수 있다.
        - 전방 대각선 방향에 상대방 기물이 있을 때만 이동하여 상대방 기물을 잡을 수 있다. (전방에 상대방이 있다면 잡을 수 없다)
    

# 3단계 - 승패 및 점수

- King이 잡히는 경우 게임에서 진다. King이 잡혔을 때 게임을 종료해야 한다.
  - [x] King이 잡히는 경우 점수 계산 후, 우승팀을 출력한다.
- 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.
- status 명령어를 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있다.
- 각 말의 점수
    -  king: 0점, queen: 9점, rook: 5점, bishop: 3점, knight: 2.5점, pawn: 기본 1점이나 세로줄에 같은 팀의 폰이 있는 경우 0.5점
    

# 리팩토링 목록
- [x] 리팩토링을 하면서 각 클래스에 맞는 테스트코드도 같이 수정한다.
- [x] Position 값인 alpha, number 원시값을 포장한다.
- [x] 각 객체가 Position 값을 가지므로 인자로 받는 source 값을 제거한다.
- [x] 각 팀 객체를 만든다.
- [x] 각 팀의 게임상태를 상태패턴으로 관리한다.
- [x] getChessboard로 장애물 여부를 판단 하지 않는다.
- [x] command 상태에 따라 실행가능한 Consumer를 구현해본다.

# 프로그래밍 요구사항
- 한 단계의 들여쓰기(indent)만 허용
- else 사용 금지
- 모든 원시값과 문자열 포장
- 일급 컬렉션 사용
- 3개 이상의 인스턴스 변수를 가진 클래스를 구현하지 않는다.(2개까지 인스턴스 변수 허용)
- 핵심 로직에 getter/setter 금지
- 메소드의 인자 수 제한(3개까지 허용)
- 코드 한줄에 점 하나만 허용
- 메소드가 한가지 일만 담당
- 클래스를 작게 유지

# 심화 가능하면 도전할 목록
- [x] 언제나 백팀이 먼저 움직인다.
- [x] 백팀과 흑팀이 번갈아가며 움직인다.