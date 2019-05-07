var param = location.href.split('?')[1];
if (param) {
  document.querySelector('h1').innerHTML = "게시물 조회";
  loadData(param.split('=')[1]);
  var el = document.querySelectorAll('.bit-new-item');
  for (e of el) {
    e.style.display = 'none';
  }
} else {
  document.querySelector('h1').innerHTML = "새 글";
  var el = document.querySelectorAll('.bit-view-item');
  for (e of el) {
    e.style.display = 'none';
  }
}

document.querySelector('#add-btn').onclick = () => {
  var xhr = new XMLHttpRequest();

  // 리스너 역할을 선언해준다.
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;

    var data = JSON.parse(xhr.responseText);
    if (data.status == 'success') {
      location.href = "index.html"
    } else {
      alert('등록 실패 입니다.\n' + data.message)
    }
  };
  xhr.open('POST', '../../app/json/member/add', true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  var name = document.querySelector('#name').value;
  var email = document.querySelector('#email').value;
  var photo = document.querySelector('#photoFile').value;
  var tel = document.querySelector('#tel').value;
  xhr.send('name=' + encodeURIComponent(name) +
      '&email=' + encodeURIComponent(email) +
      '&photoFile=' + encodeURIComponent(photo) +
      '&tel=' + encodeURIComponent(tel))
}

document.querySelector('#delete-btn').onclick = () => {
  var xhr = new XMLHttpRequest();

  // 리스너 역할을 선언해준다.
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;

    var data = JSON.parse(xhr.responseText);
    if (data.status == 'success') {
      location.href = "index.html"
        
    } else {
      alert('삭제 실패 입니다.')
    }
  };
  var no = document.querySelector('#no').value;
  xhr.open('GET', '../../app/json/member/delete?no=' + no, true);
  xhr.send()
}

document.querySelector('#update-btn').onclick = () => {
  var xhr = new XMLHttpRequest();

  // 리스너 역할을 선언해준다.
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;

    var data = JSON.parse(xhr.responseText);
    if (data.status == 'success') {
      location.href = "index.html"
    } else {
      alert('변경 실패 입니다!\n' + data.message);
    }
  };
  var no = document.querySelector('#no').value,
      name = document.querySelector('#name').value,
      email = document.querySelector('#email').value,
      photo = document.querySelector('#photoFile').value,
      tel = document.querySelector('#tel').value,
      registeredDate = document.querySelector('#registeredDate').value;
  
  var qs = 'name=' + encodeURIComponent(name) +
            'email=' + encodeURIComponent(email) +
            'photoFile=' + encodeURIComponent(photo) +
            'tel=' + encodeURIComponent(tel) +
            'registeredDate=' + encodeURIComponent(registeredDate) +
            '&no' + no;
  xhr.open('POST', '../../app/json/member/update?no=' + no, true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.send(qs)
}

function loadData(no) {
  // 헤더 가져오는 코드
  var xhr = new XMLHttpRequest();

  // 리스너 역할을 선언해준다.
  xhr.onreadystatechange = function() {
    if (xhr.readyState != 4 || xhr.status != 200)
      return;

    var data = JSON.parse(xhr.responseText);
    console.log(data);
    document.querySelector('#no').value = data.no;
    document.querySelector('#name').value = data.name;
    document.querySelector('#email').value = data.email;
    document.querySelector('#photoFile').value = data.photo;
    document.querySelector('#tel').value = data.tel;
    document.querySelector('#registeredDate').value = data.registeredDate;
  };
  xhr.open('GET', '../../app/json/member/detail?no=' + no, true);
  xhr.send()
}

