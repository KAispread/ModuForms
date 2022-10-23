let answer = {
    init : function () {
        let _this = this;
        $('#btn-answer-save').on('click', function () {
            _this.answer_save();
        });
        $('#btn-answer-edit').on('click', function () {
            _this.answer_edit();
        });
        // $('#answer-edit-form').on('click', function () {
        //     _this.answer_edit_form();
        // });
        $('#btn-answer-delete').on('click', function () {
            _this.answer_delete();
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
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            })
        }
    },
    // answer_edit_form: function () {
    //     let path_name = $(location).attr('pathname').substr(9);
    //     if (window.confirm('응답을 수정하시겠습니까?'))
    //     {
    //         window.location.href = '/answers/' + path_name + '/edit';
    //     }
    // },
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
    }
}

answer.init();