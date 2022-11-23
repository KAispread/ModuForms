let index = {
    init : function () {
        let _this = this;
        $('#btn-register').on('click', function () {
            _this.register();
        });
        $('#btn-logout').on('click', function () {
            _this.logout();
        });
        $('#user_toggle').on('click', function () {
            $("#user_dropdown").toggle();
        });
    },
    register : function () {
        let data = {
            id : $('#id').val(),
            pwd : $('#pwd').val(),
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
            window.location.href = '/users/login';
        }).fail(function () {
            alert('올바른 정보를 입력해주세요.');
        })
    },
    logout : function () {
        if (window.confirm('로그아웃 하시겠습니까?'))
        {
            $.ajax({
                type: 'POST',
                url: '/app/users/logouts',
            }).done(function (){
                alert('로그아웃 했습니다.');
                window.location.href = '/users/login';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    }
}

index.init();