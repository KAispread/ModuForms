$(document).ready(function () {
    let created_flag= new URLSearchParams(location.search).get('created');
    if (created_flag === 'true') {
        alert("회원가입이 완료되었습니다");
    }
});