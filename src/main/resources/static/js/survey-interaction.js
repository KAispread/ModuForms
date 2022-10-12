$(document).ready(function () {
    let question_box = $("#question_box");
    let index = 2;

    $("#insertButton").on("click", function () {
        let root_div = document.createElement("div");
        root_div.setAttribute("class", "question_root")

        root_div.innerHTML +=
            "<hr class=\"my-4\">\n" +
            "<div class=\"col-12 question_wrapper\">\n" +
            "  <label class=\"form-label\">Question </label>\n" +
            "  <button type=\"button\" class=\"btn-close btn-question-close\" aria-label=\"Close\"></button>\n" +
            "  <select class=\"form-select form-select-sm\" name=\"question_type\">\n" +
            "      <option selected>질문 유형 선택</option>\n" +
            "      <option value=\"SHORT\">단답형</option>\n" +
            "      <option value=\"MULTIPLE\">객관식</option>\n" +
            "   </select>\n" +
            "   <input type=\"text\" class=\"form-control\" name=\"question\" placeholder=\"질문을 입력하세요.\"\n" +
            "                                       value=\"\" required>\n" +
            "</div>"

        // let newHr = document.createElement("hr");
        // newHr.setAttribute("class", "my-4");
        //
        // let inner_div = document.createElement("div");
        // inner_div.setAttribute("class", "col-12 question_wrapper");
        //
        // let newLabel = document.createElement("label");
        // newLabel.setAttribute("class", "form-label");
        // newLabel.textContent = "질문 " + index + ".";
        // inner_div.append(newLabel);
        // inner_div.innerHTML +=
        //     '<button type="button" class="btn-close btn-question-close" aria-label="Close"></button>\n' +
        //     '                  <select class="form-select form-select-sm" name="question_type">\n' +
        //     "                    <option selected>질문 유형 선택</option>\n" +
        //     '                    <option value="SHORT">단답형</option>\n' +
        //     '                    <option value="MULTIPLE">객관식</option>\n' +
        //     "                  </select>\n" +
        //     '                  <input type="text" class="form-control" name="question" placeholder="질문을 입력하세요."\n' +
        //     '                    value="" required>';
        // root_div.append(inner_div);
        // root_div.append(newHr);
        question_box.append(root_div);
        index += 1;
    });
    $(document).on("click", ".btn-question-close", function () {
        $(this).parent("").parent(".question_root").remove();
        index -= 1;
    });
});