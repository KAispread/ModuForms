let answer = {
    init : function () {
        let _this = this;
        $('#btn-answer-save').on('click', function () {
            _this.answer_save();
        });
    },
    answer_save: function () {
        let path_name = $(location).attr('pathname').substr(9);
        path_name = path_name.substring(0, path_name.length - 7);

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
                url: '/app/' + path_name + '/answer',
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
    }
}

answer.init();