let survey = {
    init : function () {
        let _this = this;
        $('#btn-survey-save').on('click', function () {
            _this.survey_save();
        })
    },
    survey_save : function () {
        let data = {
            "title": "회식 참여 조사",
            "description": "회식 참여 조사를 위한 설문입니다.",
            "deadLine": "2022-10-06-15-30",
            "email": "sinf01@gmail.com",
            "maximumAnswer": 200,
            "surveyQuestionList": [
                {
                    "number": 1,
                    "question": "회식에 참여하십니까?",
                    "distractor": null,
                    "questionType": "SHORT"
                },
                {
                    "number": 2,
                    "question": "참여자의 성함을 입력해주세요.",
                    "distractor": null,
                    "questionType": "SHORT"
                }
            ]
        }
        let nickname = $('#userNickName').val();

        $.ajax({
            type: 'POST',
            url: '/app/survey/' + nickname,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            alert('설문이 등록되었습니다.');
            window.location.href = '/' + nickname;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

survey.init();