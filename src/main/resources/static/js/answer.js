let answer = {
    init : function () {
        let _this = this;
        $('#btn-answer-save').on('click', function () {
            _this.answer_save();
        });
        $('#btn-answer-edit').on('click', function () {
            _this.answer_edit();
        });
        $('#btn-answer-delete').on('click', function () {
            _this.answer_delete();
        });
        $('#btn-copy-url').on('click', function () {
            _this.copy_answer_url();
        });
    },
    answer_save: function () {
        let path_name = new URLSearchParams(window.location.search).get('surveyId');
        console.log(path_name)

        let number_of_question = $('.question_wrapper').length;
        let answer_list = new Array(number_of_question);

        for (let i = 0; i <number_of_question; i++) {
            let type = $('.question_wrapper').eq(i).children('span').children("input[name='que_type']").val();
            switch (type) {
                case ("short"):
                    answer_list[i] = $('.question_wrapper').eq(i).children('.short_answer').children('#floatingTextarea').val();
                    break;
                case ("multiple"):
                    answer_list[i] = $('.question_wrapper').eq(i).children('.multiple_answer').children('input[name=answer]:checked').val();
                    break;
                default:
                    break;
            }
        }

        let flag = false;
        flag = $("input[name='anonymous_flag']:checked").val() === 'on';

        let data = {
            anonymousFlag: flag,
            answerList: answer_list
        }

        if (window.confirm('응답을 제출하시겠습니까?'))
        {
            $.ajax({
                type: 'POST',
                url: '/app/answers?surveyId=' + path_name,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('응답이 제출되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    answer_edit: function () {
        let path_name = $(location).attr('pathname').substr(9);
        path_name = path_name.substring(0, path_name.length - 5);

        let number_of_question = $('.question_wrapper').length;
        let answer_list = new Array(number_of_question);

        for (let i = 0; i <number_of_question; i++) {
            let type = $('.question_wrapper').eq(i).children('span').children("input[name='que_type']").val();
            switch (type) {
                case ("short"):
                    answer_list[i] = $('.question_wrapper').eq(i).children('.short_answer').children('#floatingTextarea').val();
                    break;
                case ("multiple"):
                    answer_list[i] = $('.question_wrapper').eq(i).children('.multiple_answer').children('input[name=answer]:checked').val();
                    break;
                default:
                    break;
            }
        }

        let flag = false;
        flag = $("input[name='anonymous_flag']:checked").val() === 'on';

        let data = {
            anonymousFlag: flag,
            answerList: answer_list
        }

        if (window.confirm('응답을 제출(수정)하시겠습니까?'))
        {
            $.ajax({
                type: 'PUT',
                url: '/app/answers/' + path_name,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('응답이 수정되었습니다.');
                window.location.href = '/answers/' + path_name;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    answer_delete: function (){
        let path_name = $(location).attr('pathname').substr(9);
        if (window.confirm('응답을 삭제하시겠습니까?'))
        {
            $.ajax({
                type: 'DELETE',
                url: '/app/answers/' + path_name,
                dataType: 'json'
            }).done(function () {
                alert('응답이 삭제되었습니다.');
                window.location.href = '/';
            }).fail(function () {
                alert('삭제 권한이 없습니다.');
            })
        }
    },
    copy_answer_url: function () {
        let path_name = $(location).attr('pathname').substr(9);

        let textarea = document.createElement("textarea");
        //url 변수 생성 후, textarea라는 변수에 textarea의 요소를 생성

        document.body.appendChild(textarea); //</body> 바로 위에 textarea를 추가(임시 공간이라 위치는 상관 없음)
        textarea.value = "http://3.36.156.200:8080/answers" + "?surveyId=" + path_name;
        textarea.select();  //textarea를 설정

        document.execCommand("copy");
        document.body.removeChild(textarea);

        alert("응답 URL 이 복사되었습니다.")  // 알림창
    }
}

answer.init();