$(document).ready(function(){
    $('form').validate({
        rules: {
            username: {
                required: true,
                minlength: 3,
                pattern: /^[a-zA-Z0-9_]+$/
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        messages: {
            username: "Username must be at least 3 characters long and only contain letters, numbers and underscores",
            password: "password must be at least 8 characters long"
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