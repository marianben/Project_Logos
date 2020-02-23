$(document).ready(function () {
    $("#signinBtn").on("click", function () {
        signin();
    });
});

function signin() {
    let user = {
        email: $("#email").val(),
        password: $("#password").val()
    };

    $.ajax({
        url: SERVER_URL + "auth/signin",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),
        complete: function (serverResponse) {
            let data = serverResponse.responseJSON;
            if (serverResponse.status == 200) {
                if (data.token) {
                    let tokenData = parseTokenData(data.token);
                    localStorage.setItem("auth_token", data.token);
                    localStorage.setItem("auth_role", tokenData.auth);

                    window.location.href = 'admin.html';
                }
            }
        }
    });
}
