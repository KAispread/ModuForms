let index = {
    init : function () {
        let _this = this;
        $('#btn-register').on('click', function () {
            _this.register();
        })
        // $('#btn-login').on('click', function () {
        //     _this.login();
        // })
    },
    register : function () {
        let data = {
            id : $('#input_id').val(),
            pwd : $('#input_pwd').val(),
            username : $('#username').val(),
            nickname : $('#nickname').val(),
            birth : $('#birth').val(),
            gender : $("#gender option:selected").val(),
            email : $('#email').val(),
            phone : $('#phone').val(),
            company : $('#company').val()
        };

        $.ajax({
            type: 'POST',
            url: '/app/user',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            alert('회원 가입이 완료되었습니다.');
            window.location.href = '/login';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    login : function () {
        let data = {
            userId : $('#userId').val(),
            password : $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/login',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('로그인 성공');
            window.location.href = '/';
        })
    }
}

index.init();