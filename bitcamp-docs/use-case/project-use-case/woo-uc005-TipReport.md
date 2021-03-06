# UC006- 신고하기(Report)
부적절한 팁에 대해 회원이 관리자에게 신고하는 것.

## 주 액터(Primary Actor)
회원

## 보조 액터(Secondary Actor)

## 사전 조건(Preconditions)
- 회원으로 로그인 되어있다.
- 부적절한 팁이 게시가 되어 있다.

## 종료 조건(Postconditionse)
- 관리자에게 신고한다.

## 시나리오(Flow of Events)

### 기본 흐름(Basic Flows)
- 1. Actor가 `광고성 글`이나 `현재 팁과는 적절치 않은 글`을 발견했을 때 이 유스케이스를 시작한다.
- 2. Actor가 `신고하기` 버튼을 클릭한다.
- 3. 시스템은 `신고 등록 폼`을 출력한다.
- 4. Actor는 `신고 등록 폼`에 신고 내용을 기입한다.
- 5. 시스템은 Actor가 입력한 신고 내용을 확인할 수 있는 `신고 내용 확인 창`을 띄운다.
- 6. Actor가 `신고하기` 버튼을 클릭하면, 시스템은 Actor가 입력한 신고 내용을 저장하고 `팁 열람하기` 유스케이스 2번으로 이동한다.
    - Actor가 `취소하기` 버튼을 클릭하면,
        시스템은 `팁 열람하기` 유스케이스 2번으로 이동한다.