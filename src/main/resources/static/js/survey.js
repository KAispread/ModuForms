let survey = {
    init : function () {
        let _this = this;
        $('#btn-survey-save').on('click', function () {
            _this.survey_save();
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
    }
}

survey.init();