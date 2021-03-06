# 오픈빌더를 활용한 챗봇 서비스

![통계](img/통계.png)

---

<br/><br/>

## 📜 목차
* [소개](https://github.com/hyojaekim/chatbot#-%EC%86%8C%EA%B0%9C)
* [기능](https://github.com/hyojaekim/chatbot#-%EA%B8%B0%EB%8A%A5)
* [구성](https://github.com/hyojaekim/chatbot#-%EA%B5%AC%EC%84%B1)
* [기술 스택](https://github.com/hyojaekim/chatbot#-%EA%B8%B0%EC%88%A0-%EC%8A%A4%ED%83%9D)
* [Wiki](https://github.com/hyojaekim/chatbot#wiki)
* [TODO](https://github.com/hyojaekim/chatbot#-todo)
* [기능 이미지로 보기](https://github.com/hyojaekim/chatbot#-%EA%B8%B0%EB%8A%A5-%EC%9D%B4%EB%AF%B8%EC%A7%80%EB%A1%9C-%EB%B3%B4%EA%B8%B0)

<br/><br/>

## 🗣 소개

#### 🏫 KDU 알림이

캠퍼스 생활에 필요한 정보를 제공해주는 서비스 입니다.

#### 🦠 코로나 정보

코로나 관련된 정보를 제공하는 서비스 입니다.

<br/><br/>

## 🧩 기능

| 분류 | 기능 | 분류 | 기능 |
| :---: | :---: | :---: | :---: |
| KDU 알림이 | 캠퍼스 설정 | 코로나 정보 | 확진자 현황 및 동선 (해당 서비스로 이동) |
| | 학식 메뉴 보여주기 | | 해당 장소 방역 여부 (해당 서비스로 이동) |
| | 지하철 도착 정보 | | 반경 2km 내에 있는 마스크 재고 조회 |
| | 버스 시간표 | | 마스크 5부제 (구매 가능한 날짜) |
| | 재택 수업 Tip | | 예방 수칙 |
| | 학교 민원실 안내 | | 의심증상 |
| | Q & A | | |
| | 교내 공지사항 | | |
| | 전화번호 검색 | | |
| | QR 코드를 활용한 이벤트 | | |
| | 관리자 페이지(데이터 조회 및 분류) | | |
| | 익명 채팅방(카카오 로그인) | | |

<br/><br/>

## 👉🏻 구성

![사용자 요청](img/flow.png)

![CI/CD](img/flow2.png)

<br/><br/>

## 🛠 기술 스택

```
Language : Kotlin

Backend : Spring Boot, SpringSecurity, JPA

Database : MariaDB

AWS : EC2, RDS, S3, CodeDeploy

CI/CD : Travis-CI
 
Source version control : GIT

Repository : GitHub

Issue Tracker : GitHub Issues, GitHub Kanban Board
```

<br/><br/>

## [Wiki](https://github.com/hyojaekim/chatbot/wiki)

- [DB 방언 문제 해결하기](https://github.com/hyojaekim/chatbot/wiki/DB-%EB%B0%A9%EC%96%B8-%EB%AC%B8%EC%A0%9C-(MySQL5InnoDBDialect-Deprecated))
- [OSIV 옵션을 왜 꺼야하나?](https://github.com/hyojaekim/chatbot/wiki/OSIV-(Open-Session-In-View)-%EB%AC%B8%EC%A0%9C%EC%A0%90)
- [어떤 JSON 라이브러리를 사용할까?](https://github.com/hyojaekim/chatbot/wiki/JSON-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%84%A0%ED%83%9D-%EA%B8%B0%EC%A4%80)
- [크롤링을 위한 라이브러리는 무엇을 선택할까?](https://github.com/hyojaekim/chatbot/wiki/%ED%81%AC%EB%A1%A4%EB%A7%81%EC%9D%84-%EC%9C%84%ED%95%9C-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%84%A0%EC%A0%95-(Jsoup))
- [실시간 지하철 정보 API 데이터(예시)](https://github.com/hyojaekim/chatbot/wiki/%EC%8B%A4%EC%8B%9C%EA%B0%84-%EC%A7%80%ED%95%98%EC%B2%A0-%EC%A0%95%EB%B3%B4)
- [마스크 재고 정보 API 데이터(예시)](https://github.com/hyojaekim/chatbot/wiki/%EB%A7%88%EC%8A%A4%ED%81%AC-%EC%9E%AC%EA%B3%A0-%EC%A0%95%EB%B3%B4-%EC%98%88%EC%8B%9C)
- [Geocoding API - 주소를 주면 위도, 경도 알아내기](https://github.com/hyojaekim/chatbot/wiki/%EC%A3%BC%EC%86%8C-%EC%9C%84%EB%8F%84,-%EA%B2%BD%EB%8F%84-%EC%95%8C%EC%95%84%EB%82%B4%EA%B8%B0)
- [TravisCI 중요한 키값 관리하기](https://hyojaedev.tistory.com/13)
- [시리로 배포하기](https://hyojaedev.tistory.com/15)

<br/><br/>

## ✅ TODO

- [x] 무중단 배포
- [x] CI/CD 구축
- [x] 캠퍼스 설정
- [x] 학식 메뉴 크롤링하여 저장
- [x] 캠퍼스에 맞는 각 식당의 학식 메뉴 가져오기
- [x] 학교 민원실 이동 안내
- [x] 실시간 지하철 도착 정보
- [x] 재택 수업 Tip
- [x] 마스크 5부제 (구매 가능한 요일)
- [x] 주소로 위도와 경도 찾기
- [x] 반경 2km 마스크 재고
- [x] Siri로 배포하기
- [x] QR코드를 활용한 이벤트
- [x] 전화번호 검색
- [x] 관리자 페이지
  - [x] 데이터 조회
  - [x] 동의어 및 유형 설정
- [x] 단체 익명 채팅방
  - [x] 카카오 로그인

<br/><br/>

## 🧩 기능 이미지로 보기

#### #1 캠퍼스 알림이

![캠퍼스 알림이 메뉴](img/캠퍼스알림이메뉴.png)

![캠퍼스 설정](img/캠퍼스설정.png)

![학식 메뉴](img/학식메뉴.png)

![지하철 정보](img/지하철정보.png)

![버스 시간표](img/버스정보.png)

![기타 정보](img/기타.png)

![Q&A, 공지사항, 전화번호 검색](img/Q&A_공지사항_전화번호.png)

![QR코드 이벤트](img/QR코드이벤트.png)

![관리자페이지](img/관리자페이지.png)

![단체 익명 채팅](img/단체익명채팅.png)

---

#### #2 코로나 정보

![코로나 정보 메뉴](img/코로나정보메뉴.png)

![코로나 기타 정보](img/코로나기타정보.png)

![마스크 재고](img/마스크재고.png)
