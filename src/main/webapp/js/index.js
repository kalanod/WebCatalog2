$("#searchBtn").click(function () {
    window.location.replace(location.protocol + '//' + location.host + location.pathname
        + "?page=0&author=" + $("#authorInput").val()
        + "&title=" + $("#titleInput").val() +
        "&key=" + $("#keyWordsInput").val() +
        "&date=" + $("#dateInput").val());
});
$("#btnSubmit").click(function () {
    alert("button");
});
