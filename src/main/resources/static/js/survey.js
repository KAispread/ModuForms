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
        $('#btn-copy-url').on('click', function () {
            _this.copy_answer_url();
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
            alert('????????? ?????????????????????.');
            window.location.href = '/';
        }).fail(function () {
            alert('????????? ?????? ????????? ??????????????????');
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

        if (window.confirm('????????? ??????????????? ?????? ?????? ???????????? ???????????????. ????????? ?????????????????????????'))
        {
            $.ajax({
                type: 'PUT',
                url: '/app/surveys/' + path_name,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('????????? ?????????????????????.');
                window.location.href = '/surveys/' + path_name;
            }).fail(function () {
                alert('????????? ?????? ????????? ??????????????????');
            })
        }
    },
    survey_delete: function (){
        let path_name = $(location).attr('pathname').substr(9);
        if (window.confirm('????????? ?????????????????????????'))
        {
            $.ajax({
                type: 'DELETE',
                url: '/app/surveys/' + path_name,
                dataType: 'json'
            }).done(function () {
                alert('????????? ?????????????????????.');
                window.location.href = '/';
            }).fail(function () {
                alert('?????? ????????? ????????????.');
            })
        }
    },
    copy_answer_url: function () {
        let path_name = $(location).attr('pathname').substr(9);

        let textarea = document.createElement("textarea");

        document.body.appendChild(textarea);
        textarea.value = "http://ec2-3-36-156-200.ap-northeast-2.compute.amazonaws.com:8080/" + "answers?surveyId=" + path_name;
        textarea.select();  //textarea??? ??????

        document.execCommand("copy");
        document.body.removeChild(textarea);

        alert("?????? URL ??? ?????????????????????.")  // ?????????
    }
}

survey.init();