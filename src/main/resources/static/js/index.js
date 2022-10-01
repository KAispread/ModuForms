let index = {
    init : function () {
        let _this = this;
        $('#btn-register').on('click', function () {
            _this.register();
        })
    },
    register : function () {
        let data = {
            id : $('#input_id').val(),
            pwd : $('#input_pwd').val(),
            username : $('#username').val(),
            birth : $('#birth').val(),
            gender : $("#gender option:selected").val(),
            email : $('#email').val(),
            phone : $('#phone').val()
        };

        $.ajax({
            type: 'POST',
            url: '/app/user',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원 가입이 완료되었습니다.');
            window.location.href = '/login';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        }).always(function () {
            window.location.href = '/login';
        })
    }
}

index.init();