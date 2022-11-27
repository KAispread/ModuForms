let details = {
    init : function () {
        let _this = this;
        $('#btn-user-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-user-edit').on('click', function () {
            _this.edit();
        });
    },
    delete : function () {
        let path_name = $('#userId').val();
        if (window.confirm('정말 계정을 삭제하시겠습니까? '))
        {
            $.ajax({
                type: 'DELETE',
                url: '/app/users/' + path_name,
            }).done(function (){
                alert('계정이 삭제되었습니다.');
                window.location.href = '/users/login';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    edit : function () {
        let path_name = $('#userId').val();
        let data = {
            name : $('#name').val(),
            nickname : $('#nickname').val(),
            birth : $('#birth').val(),
            gender : $("#gender option:selected").val(),
            email : $('#email').val(),
            phone : $('#phone').val(),
            company : $('#company').val()
        };

        if (window.confirm('프로필을 수정하시겠습니까?')) {
            $.ajax({
                type: 'PATCH',
                url: '/app/users/' + path_name,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('프로필 수정이 수정되었습니다.');
                window.location.href = '/users/' + path_name;
            }).fail(function () {
                alert('필수 정보를 모두 입력해주세요');
            })
        }
    }
}

details.init();