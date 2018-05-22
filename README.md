URL Shortening Service
======================

* ###프로젝트 소개
    * URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service

* ###문제해결 전략
    * URL Shortening Key는 길이를 8로 고정하여 생성
    * 동일한 URL에 대한 요청은 동일한 Shortening Key를 반환
    * Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 처리
    * Shortening Key 생성 알고리즘은 'a'~'z', 'A'~'Z', '0'~'9' 까지 총 62개의 문자를 사용하여 랜덤으로 key를 생성

* ###사용한 기술
    * Java1.8, SpringBoot2.0, gradle, thymeleaf

* ###프로젝트 빌드 방법
    * ./gradlew clean build

* ###프로젝트 실행 방법
    * ./gradlew bootrun
