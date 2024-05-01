$(document).ready(function(){
    $('form').validate({
        rules: {
            reviewContent: {
                required: true
            }
        },
        messages: {
            reviewContent: "Please enter a review"
        },
        highlight: function (input) {
            $(input).addClass('is-invalid');
        },
        unhighlight: function (input) {
            $(input).removeClass('is-invalid');
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
});