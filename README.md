# java-chess

체스 미션 저장소

## 체스 규칙

### 체스 기물
킹, 퀸, 룩, 비숍, 나이트, 폰

### 폰
- 처음 움직일 때 앞으로 최대 2칸 움직일 수 있다.
- 대각선으로 한 칸 움직여 공격한다.
- 평소에는 앞으로 한 칸 움직인다.

### 나이트
- L자로 움직인다. (총 3칸 이동)
- 기물을 뛰어넘을 수 있다.

### 비숍
- 대각선으로만 움직인다. 

### 룩
- 상하좌우로 움직인다.

### 퀸
- 상하좌우, 대각선으로 움직인다.

### 킹
- 상하좌우, 대각선으로 한 칸 움직인다.

## 기능

### 기물
- [x] 기물은 움직일 수 있는지 판단한다.
- [x] source 위치부터 target 위치까지 이동 경로를 반환한다.
- [x] 기물은 팀에 속해있어야 한다.

### 위치
- [x] rank와 file을 가진다.
- [x] rank와 file은 1이상 8이하의 정수여야 한다.
- [x] 위치를 이동한다.

### 팀
- [x] BLACK, WHITE 두 팀이 있다.
- [x] 같은 팀인지 판단한다.

### 보드
- [x] 소스위치에 기물이 있는지 확인한다.
- [x] 움직이는 경로에 기물이 있는지 확인한다.

### 체스게임
- [x] 턴을 확인한다.
- [x] 보드에게 기물을 움직이라 명령한다. 

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
