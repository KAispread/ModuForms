let survey = {
    init : function () {
        let _this = this;
        $('#btn-survey-save').on('click', function () {
            _this.survey_save();
        });
        $('#btn-survey-edit-save').on('click', function () {
            _this.survey_edit();
        });
        $('#btn-survey-delete').on('click', function () {
            _this.survey_delete();
        });
    },
    survey_save : function () {
        let number_of_question = $('input[name=question]').length;
        let question_list = new Array(number_of_question);

        for (let i = 0; i < number_of_question; i++) {
            let number_of_distractor = $(".question_wrapper").eq(i).children(".multiple_box").children(".input-group").length;
            let distractor_all = "";

            for (let j = 0; j < number_of_distractor; j++) {
                if (j === 0) {
                    distractor_all += $(".question_wrapper").eq(i).children(".multiple_box").children(".input-group").eq(j).children("input[name=distractor]").val();
                } else {
                    distractor_all += "|";
                    distractor_all += $(".question_wrapper").eq(i).children(".multiple_box").children(".input-group").eq(j).children("input[name=distractor]").val();
                }
            }
            if (distractor_all === "") {
                distractor_all = null;
            }

            question_list[i] = {
                number: i,
                question: $(".question_wrapper").eq(i).children("input[name=question]").val(),
                distractor: distractor_all,
                questionType: $("select[name=question_type] option:selected").eq(i).val()
            };
        }

        let data = {
            title: $('#survey_title').val(),
            description: $('#survey_description').val(),
            deadLine: $('#date').val() + "-" + $('#time').val(),
            email: $('#survey_email').val(),
            maximumAnswer: $('#survey_max').val(),
            surveyQuestionList: question_list
        }

        let nickname = $('#userNickName').val();

        $.ajax({
            type: 'POST',
            url: '/app/surveys/' + nickname,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('설문이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function () {
            alert('양식에 맞게 설문을 작성해주세요');
        })
    },
    survey_edit: function () {
        let number_of_question = $('input[name=question]').length;
        let question_list = new Array(number_of_question);

        for (let i = 0; i < number_of_question; i++) {
            let number_of_distractor = $(".question_wrapper").eq(i).children(".multiple_box").children(".input-group").length;
            let distractor_all = "";

            for (let j = 0; j < number_of_distractor; j++) {
                if (j === 0) {
                    distractor_all += $(".question_wrapper").eq(i).children(".multiple_box").children(".input-group").eq(j).children("input[name=distractor]").val();
                } else {
                    distractor_all += "|";
                    distractor_all += $(".question_wrapper").eq(i).children(".multiple_box").children(".input-group").eq(j).children("input[name=distractor]").val();
                }
            }
            if (distractor_all === "") {
                distractor_all = null;
            }

            question_list[i] = {
                number: i,
                question: $(".question_wrapper").eq(i).children("input[name=question]").val(),
                distractor: distractor_all,
                questionType: $("select[name=question_type] option:selected").eq(i).val()
            };
        }

        let data = {
            title: $('#survey_title').val(),
            description: $('#survey_description').val(),
            deadLine: $('#date').val() + "-" + $('#time').val(),
            email: $('#survey_email').val(),
            maximumAnswer: $('#survey_max').val(),
            surveyQuestionList: question_list
        }

        let nickname = $('#userNickName').val();
        let path_name = $(location).attr('pathname').substr(9);
        path_name = path_name.substring(0, path_name.length - 5);

        if (window.confirm('설문 내용을 저장하시겠습니까?'))
        {
            $.ajax({
                type: 'PUT',
                url: '/app/surveys/' + path_name,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('설문이 수정되었습니다.');
                window.location.href = '/';
            }).fail(function () {
                alert('양식에 맞게 설문을 작성해주세요');
            })
        }
    },
    survey_delete: function (){
        let path_name = $(location).attr('pathname').substr(9);
        if (window.confirm('설문을 삭제하시겠습니까?'))
        {
            $.ajax({
                type: 'DELETE',
                url: '/app/surveys/' + path_name,
                dataType: 'json'
            }).done(function () {
                alert('설문이 삭제되었습니다.');
                window.location.href = '/';
            }).fail(function () {
                alert('삭제 권한이 없습니다.');
            })
        }
    }
}

survey.init();