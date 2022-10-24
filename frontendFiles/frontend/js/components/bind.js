/**
 * Created by Lodbrok on 13.07.2017.
 */

(function () {

    function getWorkName (target) {
        var tr = target.closest("tr");
        var name = tr.children[0];
        return name.innerHTML;
    }

    document.addEventListener("click", function (evt) {
        documentClick(evt);
    }, true);

    function documentClick (evt) {
        var target = evt.target;

        if (!target.className) {
            return;
        }

        if (target.className == "exit_btn") {
            var data = {
                action: "unLogin"
            };
            helper.getHttpPromise({
                method: "POST",
                url: "/api",
                contentType: "application/json",
                jsonData: data
            }).then(function (response) {
                var ans = JSON.parse(response);
                if (ans.success == true) {
                    document.location.href = "/";
                    return;
                }
            });

            evt.preventDefault();
            evt.stopPropagation();
        }



    }

})();
