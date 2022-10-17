let index = {
    init : function () {
        let _this = this;
        $('#btn-register').on('click', function () {
            _this.register();
        });
        $('#btn-logout').on('click', function () {
            _this.logout();
        });
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
            url: '/app/users',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원 가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    logout : function () {
        $.ajax({
            type: 'POST',
            url: '/app/users/logout',
            dataType: 'json',
        }).done(function (){
            alert('로그아웃 했습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

index.init();