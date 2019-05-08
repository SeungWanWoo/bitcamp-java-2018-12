var pageNo = 1,
pageSize = 3,
tbody = $('tbody'),
prevPageLi = $('#prevPage'),
nextPageLi = $('#nextPage'),
currSpan = $('#currPage > span');

//JSON 형식의 데이터 목록 가져오기[테이블 갱신]
function loadList(pn) {
  //var url = '../../app/json/board/list?pageNo='+ pn + '&pageSize=' + pageSize;
  /* var settings = {
      success(result) {
        // 서버에서 받은 JSON 형식의 데이터를 JavaScript 객체로 변환
        var obj = JSON.parse(result);

        // 서버에서 받은 데이터 중에서 페이지 번호를 글로벌 변수에 저장한다.
        pageNo = obj.pageNo;

        // TR 태그를 생성하여 테이블 데이터를 갱신한다.
        tbody.innerHTML = ''; // 이전에 출력한 내용을 제거한다.
        for (data of obj.list) {
          var tr = '<tr>'
            + '<th scope="row">' + data.no + '</th>'
            //+ '<td><a href="view.html?no=' + data.no + '">' + data.contents + '</a></td>'
            + '<td><a class="bit-view-link" href="#" data-no="' + data.no + '">' + data.contents + '</a></td>'
            + '<td>' + data.createdDate + '</td>'
            + '<td>' + data.viewCount + '</td>'
            + "</tr>";
          tbody.innerHTML = tbody.innerHTML + tr;
        }

        // 현재 페이지의 번호를 갱신한다.
        currSpan = document.querySelector('#currPage > span').innerHTML = String(pageNo);

        // 1페이지일 경우 버튼을 비활성화 한다.
        if (pageNo == 1) {
          prevPageLi.className = prevPage.className + ' disabled';
        } else {
          prevPageLi.className = prevPage.className.replace(' disabled', '');
        }
        // 마지막 페이지일 경우 버튼을 비활성화 한다.
        if (pageNo == obj.totalPage) {
          nextPageLi.className = nextPage.className + ' disabled';
        } else {
          nextPageLi.className = nextPage.className.replace(' disabled', '');
        }

        // 데이터 로딩이 완료되면 body 태그에 이벤트를 전송한다.
        document.body.dispatchEvent(new Event('loaded-list'));
      },
      error() {
        window.alert('서버에서 데이터를 가져오는 중 오류 발생!');
      }
  };*/
  //ajax(url, settings);

  /* ajax 함수 이용
   * Bitcamp.ajax('../../app/json/board/list?pageNo='+ pn + '&pageSize=' + pageSize, {
    dataType: 'json',
    success(obj) {
      // 서버에서 받은 데이터 중에서 페이지 번호를 글로벌 변수에 저장한다.
      pageNo = obj.pageNo;

      // TR 태그를 생성하여 테이블 데이터를 갱신한다.
      tbody.innerHTML = ''; // 이전에 출력한 내용을 제거한다.
      for (data of obj.list) {
        var tr = '<tr>'
          + '<th scope="row">' + data.no + '</th>'
          //+ '<td><a href="view.html?no=' + data.no + '">' + data.contents + '</a></td>'
          + '<td><a class="bit-view-link" href="#" data-no="' + data.no + '">' + data.contents + '</a></td>'
          + '<td>' + data.createdDate + '</td>'
          + '<td>' + data.viewCount + '</td>'
          + "</tr>";
        tbody.innerHTML = tbody.innerHTML + tr;
      }

      // 현재 페이지의 번호를 갱신한다.
      currSpan = document.querySelector('#currPage > span').innerHTML = String(pageNo);

      // 1페이지일 경우 버튼을 비활성화 한다.
      if (pageNo == 1) {
        prevPageLi.className = prevPage.className + ' disabled';
      } else {
        prevPageLi.className = prevPage.className.replace(' disabled', '');
      }
      // 마지막 페이지일 경우 버튼을 비활성화 한다.
      if (pageNo == obj.totalPage) {
        nextPageLi.className = nextPage.className + ' disabled';
      } else {
        nextPageLi.className = nextPage.className.replace(' disabled', '');
      }

      // 데이터 로딩이 완료되면 body 태그에 이벤트를 전송한다.
      document.body.dispatchEvent(new Event('loaded-list'));
    },
    error() {
      window.alert('서버에서 데이터를 가져오는 중 오류 발생!');
    }

  });*/
 $.getJSON('../../app/json/board/list?pageNo='+ pn + '&pageSize=' + pageSize, 
    function(obj) {
      // 서버에서 받은 데이터 중에서 페이지 번호를 글로벌 변수에 저장한다.
      pageNo = obj.pageNo;

      // TR 태그를 생성하여 테이블 데이터를 갱신한다.
      tbody.html(''); // 이전에 출력한 내용을 제거한다.
      for (data of obj.list) {
        $('<tr>')
          .append($('<th>').attr('scope', 'row').html(data.no))
          .append($('<td>').append(
              $('<a>').addClass('bit-view-link')
              .attr('href', '#')
              .attr('data-no', data.no)
              .html(data.contents)))
          .append($('<td>').html(data.createDate))
          .append($('<td>').html(data.viewCount))
          .appendTo(tbody);
      }

      // 현재 페이지의 번호를 갱신한다.
      currSpan.html(String(pageNo));

      // 1페이지일 경우 버튼을 비활성화 한다.
      if (pageNo == 1) {
        prevPageLi.addClass('disabled');
      } else {
        prevPageLi.removeClass('disabled', '');
      }
      // 마지막 페이지일 경우 버튼을 비활성화 한다.
      if (pageNo == obj.totalPage) {
        nextPageLi.addClass('disabled');
      } else {
        nextPageLi.removeClass('disabled', '');
      }

      // 데이터 로딩이 완료되면 body 태그에 이벤트를 전송한다.
      document.body.dispatchEvent(new Event('loaded-list'));
  }); // Bitcamp.getJSON(){}
} // loadList(pn)

//헤더가 로딩이 완료된 다음에 테이블에 출력할 데이터를 가져오고 싶다면 다음 코드를 참고하라!
//헤더 로딩이 완료되었을 때 호출될 함수 등록
$(document.body).trigger('loaded.header');

$('#prevPage > a').click((e) => {
  e.preventDefault();
  loadList(pageNo - 1)
});

$('#nextPage > a').click((e) => {
  e.preventDefault();
  loadList(pageNo + 1)
});

//테이블 목록 가져오기를 완료했으면 제목 a 태그에 클릭 리스너를 등록한다. 
//이 방법의 단점은 view.html의 링크가 안보인다.
//따라서 검색 엔진이 검색해서 view.html까지 찾아가지 못한다.
$(document.body).bind('loaded-list', () => {
  //제목을 클릭했을 때 view.html로 전환시키기
  var alist = $('.bit-view-link').click((e) => {
    e.preventDefault();
    location.href = 'view.html?no=' + $(e.target).attr('data-no');
    // 오리지널 자바스크립트 함수의 e.target에는 attr이 없다. $로 래핑해준다.
  });
});
//페이지를 출력한 후 1페이지 목록을 로딩한다.
loadList(1);