## Java Acid Rain Project - 도전 사격왕!

### 1. 작품 개요

​	게임을 시작하면 ‘도전 사격왕!’ 게임 타이틀과 함께 시작 화면이 나온다. 화면에는 게임 시작, 랭킹, 종료 버튼과 이름을 적는 칸이 있다. 랭킹을 누르면 현재 기록되어있는 사람들의 점수와 순위 화면이 출력된다. 이름을 적고 게임 시작을 누르면 게임 설정 화면이 나오고 설정 화면에서 언어, 난이도를 정해 게임을 시작할 수 있다.

​	시작을 누르면 메뉴바에 단어, 설정, 초기화면 버튼 툴바에 시작, 중단, 재시작 버튼이 있다. 단어 메뉴에서는 단어 목록을 눌러 출력되는 단어 목록을 볼 수 있다. 단어 목록은 설정 화면에서 선택한언어가 출력된다. 단어 추가를 선택하면 한글, 영어를 선택하고 입력한 단어를 선택한 단어 목록에 추가할 수 있다.

​	설정은 첫 화면에서 게임시작을 눌렀을 때 나오는 설정 화면이 나와 난이도, 언어를 바꿔 다시 시작할 수 있고 초기화면을 누르면 프로그램을 실행했을때의 첫 화면이 나온다. 툴바의 시작버튼을 눌러 게임을 시작할 수 있고 중단을 눌러 일시정지, 재시작을 눌러 다시 시작할 수 있
다.

​	시작을 누르면 단어가 내려오고 단어를 맞추면 점수가 10점 올라간다. 타겟 위에 글자색에 따라 아이템이 사용되는데 검정은 기본 단어, 하늘색은 체력회복, 파란색은 2초간 시간정지, 초록색은 5초간 점수 2배 아이템이다. 게임 좌측의 노란패널에는 점수, 이름, 목숨이 나오고 하늘색 패널에는 사용된 아이템과 남은 효과 시간이 출력된다. 

​	게임패널 하단의 회색 선에 단어가 닿으면 목숨이 하나 줄어들고 3개가 모두 줄어들면 게임이 끝난다. 게임 종료 화면에는 이름과 점수가 출력되고 YES나 NO 버튼으로 랭킹에 등록할지 선택할 수 있다. 버튼을 누르면 초기화면으로 돌아간다.

### 2.프로그램 구조

![프로그램 구조](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img/프로그램 구조.png?raw=true)



### 3. 프로그램 실행 과정

- 시작 화면

  ![시작화면](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\시작화면.png?raw=true)

- 랭킹 버튼 클릭하면 나오는 랭킹 화면

  ![랭킹화면](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\랭킹화면.png?raw=true)

- 게임 시작 시 나오는 설정 화면

  ![설정화면1](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\설정화면1.png?raw=true)

  ![설정화면2](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\설정화면2.png?raw=true)

- 게임 시작

  ![게임화면1](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\게임화면1.png?raw=true)

  ![게임화면2](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\게임화면2.png?raw=true)

  `

- 게임 종료

  ![게임종료1](https://github.com/vvorldlee/ChallengeBestShooter/blob/master/README_img/%EA%B2%8C%EC%9E%84%EC%A2%85%EB%A3%8C1.png)

  

  ![게임종료2](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\게임종료2.png?raw=true)

- 랭킹 저장

  ![랭킹저장](https://github.com/vvorldlee/ChallengeBestShooter/tree/master/README_img\랭킹저장.png?raw=true)

  

  ### 3. 느낀 점

  ​	미니 프로젝트를 수행하면서 객체지향 프로그래밍의 중요성과 편의성, 실제 게임 개발에서의 스레드 관리에 대해 깊게 배웠습니다. 프로젝트를 진행하는 과정에서 여러 스레드를 다루며 게임의 안정성을 고려하는 것 만으로도 큰 집중을 해야했으며 멀티스레딩을 구현하면서 스레드간의 상태 공유, 자원 관리의 중요성을 알게 되었습니다. 

  ​	수업시간에 배웠던 지식들을 교재를 보며 다시 공부하고 개발하는 과정에서 필요하지만 내가 모르는 부분을 찾아보며 개발, 코딩은 직접 해 봐야 실력이 늘어난다는 것을 깨달았습니다. 인터넷이나 교재를 공부하며 다른 코드에 적용되어있는 내게 필요한 기능을 내가 코딩중인 코드에 알맞게 고쳐 적용하는 능력을 기르는데 큰 도움이 되었습니다.

  ​	게임의 GUI를 설계하는 부분에서는 공부가 부족하여 자바에서 제공하는 레이아웃매니저를 잘 이용하지 못하고 레이아웃매니저를 없애고 직접 위치, 크기 등을 조정하느라 상당히 많은 시간을 들여야 했다는 점이 아쉬운 부분이었습니다. 

  ​	외에도 프레임을 만들 때 패널을 만들어 그 위에 컴포넌트를 붙이는 것이 아닌 프레임 위에 직접 컴포넌트들을 갖다 붙이는 등 수업시간에 배웠던 방식들을 제대로 활용하지 못해 아쉬움이 남습니다. 

  ​	이번 경험은 저에게 여러 부분에서 도움이 되었고 아쉬운 점도 남기게 되었습니다. 추후에 더 많은 프로젝트를 진행해보며 이런저런 부분에서 저의 코딩 실력을 쌓고 그 때마다 아쉬웠던 점들을 점차 줄여나갈 수 있도록 이와 같은 프로젝트를 더 진행 해 보고 싶다고 느꼈습니다.

