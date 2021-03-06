# UC006- 신고 게시판 관리하기(관리자)
관리자가 신고 게시판에서 신고 항목을 열람하고, 부적절한 글을 올린 사용자를 제재하며, 정상적인 팁 게시물로 롤백한다.

## 주 액터(Primary Actor)
관리자

## 보조 액터(Secondary Actor)

## 사전 조건(Preconditions)
- 관리자로 로그인 되어있다.

## 종료 조건(Postconditionse)
- 신고 항목을 열람하였다.
- 부적절한 사용자를 제재하였다.
- 정상적인 팁 게시물로 롤백하였다.

## 시나리오(Flow of Events)

### 신고 항목 열람하기 기본 흐름
- 1. Actor가 `신고 게시판`을 누른다.
- 2. 시스템은 `신고 게시판 리스트 폼`을 출력한다.
- 3. Actor는 리스트 목록 중 하나를 선택한다.
- 4. 시스템은 `신고 상세보기 폼`을 출력한다.
- 5. Actor는 신고 내용을 확인한다.

### 사용자 제재하기 기본 흐름
- 1. `신고 항목 열람하기` 유스케이스 3번에서 이 유스케이스가  시작된다.
- 2. Actor는 `부적절한 글`임을 확인하여 그 글을 올린 회원에 대해 제재하기 위해서 `제재하기` 버튼을 누른다.
- 3. 시스템은 `제재 확인 폼`을 출력한다.
- 4. Actor는 `제재` 버튼을 누른다.
- 5. 시스템은 Actor가 선택한 버튼에 따라 값을 저장하고 `신고 항목 열람하기` 유스케이스 2번으로 돌아간다.

### 사용자 제재하기 대안 흐름
- 4.1 Actor가 `보류` 버튼을 누르면,
    - 시스템은 Actor가 선택한 버튼에 따라 값을 저장하고 `신고 항목 열람하기` 유스케이스 2번으로 돌아간다.

### 롤백하기 기본 흐름(Basic Flows)
- 1. Actor가 `부적절한 글을 신고 받았고, 해당 글이 부적절한 글로 판별되었을 때` 이 유스케이스를 시작한다.
- 2. Actor가 `팁 히스토리` 버튼을 클릭한다.
- 3. 시스템은 `팁 히스토리 리스트 폼`을 출력한다.
- 4. Actor가 최근에 저장된 히스토리 중 정상적인 팁을 찾아 선택한다.
- 5. 시스템은 Actor가 선택한 팁을 확인할 수 있는 `선택 확인 폼`을 띄운다.
- 6. Actor가 `선택한 팁으로 롤백` 버튼을 클릭한다.
    - 6.1 Actor가 `돌아가기` 버튼을 클릭하면,
        - 시스템은 `팁 히스토리 리스트 폼`을 출력한다.
- 7. 시스템은 Actor가 선택한 팁으로 해당 게시물을 변경하고 `팁 히스토리 리스트`를 갱신한다.
- 8. 시스템은 리스트를 갱신한 후 `신고 게시판` 유스케이스로 간다.