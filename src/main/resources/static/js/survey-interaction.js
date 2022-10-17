$(document).ready(function () {
    let question_box = $("#question_box");

    $("#insertButton").on("click", function () {
        let root_div = document.createElement("div");
        root_div.setAttribute("class", "question_root");

        root_div.innerHTML +=
            '<hr class="my-4">\n' +
            '<div class="col-12 question_wrapper">\n' +
            '  <label class="form-label">Question </label>\n' +
            '  <button type="button" class="btn-close btn-question-close" aria-label="Close"></button>\n' +
            '  <select class="form-select form-select-sm" name="question_type">\n' +
            "      <option selected>질문 유형 선택</option>\n" +
            '      <option value="SHORT">단답형</option>\n' +
            '      <option value="MULTIPLE">객관식</option>\n' +
            "   </select>\n" +
            '   <input type="text" class="form-control" name="question" placeholder="질문을 입력하세요."\n' +
            '                                       value="" required>\n' +
            "</div>";

        question_box.append(root_div);
    });
    $(document).on("click", ".btn-question-close", function () {
        $(this).parent("").parent(".question_root").remove();
    });

    $(document).on("change", ".form-select", function () {
        let option = $(this).find(":selected").val();

        if (option === "SHORT") {
            $(this).siblings("div.multiple_box").remove();
        } else if (option === "MULTIPLE") {
            $(this)
                .parent(".question_wrapper")
                .append(
                    '                  <div class="multiple_box">\n' +
                    '                    <div style="margin-top: 15px; text-align: center;" id="passwordHelpBlock" class="form-text">\n' +
                    "                      객관식 타입의 선택지를 설정해주세요 (최대 5개)\n" +
                    "                    </div>\n" +
                    '                    <div class="input-group mb-3">\n' +
                    '                      <span class="input-group-text">A.</span>\n' +
                    '                      <input type="text" class="form-control underline" name="distractor" placeholder="선택지를 입력하세요."\n' +
                    '                        aria-label="Username" aria-describedby="basic-addon1">\n' +
                    "                    </div>\n" +
                    '                    <div class="input-group mb-3">\n' +
                    '                      <span class="input-group-text">A.</span>\n' +
                    '                      <input type="text" class="form-control underline" name="distractor" placeholder="선택지를 입력하세요."\n' +
                    '                        aria-label="Username" aria-describedby="basic-addon1">\n' +
                    "                    </div>\n" +
                    '                    <input class="option_index" type="hidden" value="2">\n' +
                    '                    <button type="button" class="btn btn-success bth-add-multiple-option">\n' +
                    '                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"\n' +
                    '                        class="bi bi-plus-square-dotted" viewBox="0 0 16 16">\n' +
                    "                        <path\n" +
                    '                          d="M2.5 0c-.166 0-.33.016-.487.048l.194.98A1.51 1.51 0 0 1 2.5 1h.458V0H2.5zm2.292 0h-.917v1h.917V0zm1.833 0h-.917v1h.917V0zm1.833 0h-.916v1h.916V0zm1.834 0h-.917v1h.917V0zm1.833 0h-.917v1h.917V0zM13.5 0h-.458v1h.458c.1 0 .199.01.293.029l.194-.981A2.51 2.51 0 0 0 13.5 0zm2.079 1.11a2.511 2.511 0 0 0-.69-.689l-.556.831c.164.11.305.251.415.415l.83-.556zM1.11.421a2.511 2.511 0 0 0-.689.69l.831.556c.11-.164.251-.305.415-.415L1.11.422zM16 2.5c0-.166-.016-.33-.048-.487l-.98.194c.018.094.028.192.028.293v.458h1V2.5zM.048 2.013A2.51 2.51 0 0 0 0 2.5v.458h1V2.5c0-.1.01-.199.029-.293l-.981-.194zM0 3.875v.917h1v-.917H0zm16 .917v-.917h-1v.917h1zM0 5.708v.917h1v-.917H0zm16 .917v-.917h-1v.917h1zM0 7.542v.916h1v-.916H0zm15 .916h1v-.916h-1v.916zM0 9.375v.917h1v-.917H0zm16 .917v-.917h-1v.917h1zm-16 .916v.917h1v-.917H0zm16 .917v-.917h-1v.917h1zm-16 .917v.458c0 .166.016.33.048.487l.98-.194A1.51 1.51 0 0 1 1 13.5v-.458H0zm16 .458v-.458h-1v.458c0 .1-.01.199-.029.293l.981.194c.032-.158.048-.32.048-.487zM.421 14.89c.183.272.417.506.69.689l.556-.831a1.51 1.51 0 0 1-.415-.415l-.83.556zm14.469.689c.272-.183.506-.417.689-.69l-.831-.556c-.11.164-.251.305-.415.415l.556.83zm-12.877.373c.158.032.32.048.487.048h.458v-1H2.5c-.1 0-.199-.01-.293-.029l-.194.981zM13.5 16c.166 0 .33-.016.487-.048l-.194-.98A1.51 1.51 0 0 1 13.5 15h-.458v1h.458zm-9.625 0h.917v-1h-.917v1zm1.833 0h.917v-1h-.917v1zm1.834-1v1h.916v-1h-.916zm1.833 1h.917v-1h-.917v1zm1.833 0h.917v-1h-.917v1zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z" />\n' +
                    "                      </svg> 선택지 추가\n" +
                    "                    </button>\n" +
                    "                  </div>"
                );
        }
    });

    $(document).on("click", ".bth-add-multiple-option", function () {
        let index = $(this).siblings(".option_index").val();
        if (index < 5) {
            $(this).before(
                '<div class="input-group mb-3">\n' +
                '                      <span class="input-group-text">A.</span>\n' +
                '                      <input type="text" class="form-control underline" name="distractor" placeholder="선택지를 입력하세요."\n' +
                '                        aria-label="Username" aria-describedby="basic-addon1">\n' +
                "\n" +
                '                      <svg style="margin-left: 10px; margin-top: 10px; font-size: larger;"\n' +
                '                        xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"\n' +
                '                        class="bi bi-x-octagon bi-close" viewBox="0 0 16 16">\n' +
                "                        <path\n" +
                '                          d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353L4.54.146zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1H5.1z" />\n' +
                "                        <path\n" +
                '                          d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />\n' +
                "                      </svg>\n" +
                "                    </div>"
            );

            $(this)
                .siblings(".option_index")
                .attr("value", parseInt(index) + 1);
        } else {
            alert("선택지는 최대 5개까지만 가능합니다");
        }
    });

    $(document).on("click", ".bi-close", function () {
        let index = $(this).parent().siblings(".option_index").val();
        $(this)
            .parent()
            .siblings(".option_index")
            .attr("value", parseInt(index) - 1);

        $(this).parent().remove();
    });
});
