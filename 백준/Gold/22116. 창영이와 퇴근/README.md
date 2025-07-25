# [Gold IV] 창영이와 퇴근 - 22116 

[문제 링크](https://www.acmicpc.net/problem/22116) 

### 성능 요약

메모리: 165320 KB, 시간: 1132 ms

### 분류

그래프 이론, 그래프 탐색, 최단 경로, 데이크스트라, 격자 그래프

### 제출 일자

2025년 7월 17일 02:53:54

### 문제 설명

<p>창영이의 퇴근길은 출근길과 조금 다르다. 창영이는 건강을 위해 따릉이를 빌려 타고 퇴근하는 습관을 기르고 있다.</p>

<p>창영이의 퇴근길은 <em>N</em>×<em>N</em> 크기의 격자로 표현된다. 창영이는 <em>A<sub>1,1</sub></em>에서 출발하여 <em>A<sub>N,N</sub></em>까지 이동할 계획이다. 창영이는 상하좌우 인접한 격자로 한 번에 한 칸씩 이동할 수 있다. 각 격자 <em>A<sub>r,c</sub></em>에는 자연수가 적혀 있는데, 이는 해당 지역의 높이를 뜻한다. 인접한 격자 사이의 <strong>높이 차이의 절댓값</strong>을 <strong>경사</strong>라고 하고, 경사가 클수록 경사가 가파르다고 하자.</p>

<p>따릉이는 가격에 따라 성능이 다르다. 비싼 따릉이는 경사가 가파르더라도 내리지 않고 타고 갈 수 있지만, 값싼 따릉이는 경사가 가파르면 힘들고 위험하기 때문에 내려서 이동해야 한다.</p>

<p>창영이는 최소한의 비용으로 따릉이를 빌려서, 따릉이에서 한 번도 내리지 않고 집에 도착하고 싶다. 그러기 위해선 창영이가 지날 수 있는 최대 경사의 최솟값을 알아야만 한다. 여러분들이 창영이를 도와주자.</p>

### 입력 

 <p>첫째 줄에 격자의 크기 <em>N</em>이 주어진다.</p>

<p>둘째 줄부터 <em>N</em>개의 줄에 걸쳐 각 격자의 높이 정보가 주어진다. 첫 번째로 주어지는 값이 <em>A<sub>1,1</sub></em>이고, 마지막으로 주어지는 값이 <em>A<sub>N,N</sub></em>이다.</p>

### 출력 

 <p><em>A<sub>1,1</sub></em>에서 <em>A<sub>N,N</sub></em>까지, 경로상의 최대 경사의 최솟값을 출력한다.</p>

