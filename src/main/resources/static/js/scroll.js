$(document).ready(function () {
    let page_url = window.location.href;
    let page_id = page_url.substring(page_url.lastIndexOf("#") + 1);

    if (page_id == 'answer_point') {
        $('html, body').animate({
            scrollTop: $('#section-divider').offset.top
        }, 500);
    }
});