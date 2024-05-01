$(document).ready(function(){
    $('form').validate({
        rules: {
            title: {
                required: true
            },
            author: {
                required: true
            },
            description: {
                required: true
            },
            coverUrl: {
                required: true
            }
        },
        messages: {
            title: "Please enter a title",
            author: "Please enter author",
            description: "Please enter description",
            coverUrl: "Please enter cover URL"
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