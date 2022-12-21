
// const url = 'http://localhost:8080/admin/post/get_by_id?id=44';
// const response = fetch(url, {
//     method: 'GET',
//     headers: {
//         'Content-Type': 'application/json',
//         'charset': 'UTF-8'
//     }
// }).then(e => e.json())
// .then(e => {
//     console.log(e.code)
//     let json = JSON.parse(e.code)
//     console.log(json);
// });

// let textw = document.getElementById("11111");
//
//
// const url = 'http://localhost:8080/admin/post/add';
// const response = fetch(url, {
//     method: 'POST',
//     body: JSON.stringify({
//         "name": "test",
//         "code": textw.innerText,
//         "description": "test"
//     }),
//     headers: {
//         'Content-Type': 'application/json',
//         'charset': 'UTF-8'
//     }
// });

const url1 = 'http://localhost:8080/admin/post/get_all';
fetch(url1, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'charset': 'UTF-8'
    }
}).then(e =>{
    return e.json()
}).then(postList => {
    for (const post of postList) {
        let buttonMenu = document.createElement("button")
            buttonMenu.id = post.id
            buttonMenu.classList.add("send-message")
            buttonMenu.classList.add("height_button")
        let innerPWhite = document.createElement("p")
            innerPWhite.id = post.id
            innerPWhite.innerText = post.name;
            innerPWhite.classList.add("send-message-white")
            innerPWhite.classList.add("left-menu-white")
        let innerPBlack = document.createElement("p")
            innerPBlack.innerText = post.name;
            innerPBlack.id = post.id;
            innerPBlack.classList.add("send-message-black")
            innerPBlack.classList.add("left-menu-black")

        buttonMenu.addEventListener("click", e => {
            console.log(e.target.id)
            const url1 = 'http://localhost:8080/admin/post/get_by_id?id=' + e.target.id;
            fetch(url1, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'UTF-8'
                }
            }).then(e =>{
                return e.json()
            }).then(post => {
                console.log(post)
                document.getElementById("name").innerText = post.name

                document.getElementById("code").innerHTML = codeFormatting(post.code);
                document.getElementById("description").innerText = post.description

                document.getElementById("date").innerText = post.date
                document.querySelector(".right_content").style.display = "block"
              })
        })
        buttonMenu.append(innerPWhite, innerPBlack)

        let menu = document.getElementById("left_menu")
            menu.firstElementChild.append(buttonMenu)
    }
});


function codeFormatting(codeText){
    let text = printReservedWord(codeText);
    text = printAnnotation(text,0);
    text = printText(text, 0);

    return text;

    function printText(codeText, index){
        let startIndex = codeText.indexOf("\"", index);
        if(startIndex === -1) {
            return codeText;
        }
        let endIndex = codeText.indexOf("\"", startIndex+1)+1;

        let leftText = codeText.substring(0, startIndex);
        let rightText = codeText.substring(endIndex);

        let iter = endIndex

        // console.log(codeText.substring(startIndex,endIndex));

        codeText = leftText + "<span class='text'>" + codeText.substring(startIndex,endIndex)+ "</span>" + rightText
        if(iter >= codeText.length) {
            return codeText
        } else {
            return printText(codeText, endIndex + 19);
        }
    }

    function printAnnotation(codeText, index){
        let startIndex = codeText.indexOf("@", index);
        if(startIndex === -1) {
            return codeText;
        }
        let endIndex = codeText.indexOf(" ", startIndex);

        let leftText = codeText.substring(0, startIndex);
        let rightText = codeText.substring(endIndex);

        let iter = endIndex

        codeText = leftText + "<span class='annotations'>" + codeText.substring(startIndex,endIndex)+ "</span>" + rightText
        if(iter > codeText.length) return codeText;
        return printAnnotation(codeText,iter)
    }

    function printReservedWord(codeText){
        let reservedWord = ["abstract", "assert", "boolean", "break",
            "byte", "case", "abstract", "catch",	"char",	"class",
            "const", "continue", "default","double", "do",	"else",
            "enum",	"extends",	"false", "final", "finally", "float",
            "for", "goto", "if", "implements", "import", "instanceof",
            "int", "interface", "long", "native", "new", "null",
            "package", "private", "protected", "public", "return",
            "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient",
            "true",	"try",	"void",	"volatile",	"while"];

        let text = codeText
        for (let i = 0; i < reservedWord.length; i++) {
            let temp = getWord(0, i, text, "reserved_word")
            if(temp !== undefined)
                text = temp
        }
        return text;

        function getWord(index, i, text, className){
            let result = text;
            if(text.includes(reservedWord[i])) {
                let startIndex = text.indexOf(reservedWord[i], index);
                if (startIndex !== -1) {
                    let word = text.substring(startIndex);
                    let endIndex = word.indexOf(" ");

                    word = word.substring(0, endIndex);
                    // console.log(text)
                    if (reservedWord.includes(word)) {
                        // console.log("test --------------")
                        let start = text.substring(0, startIndex) + "<span class='" + className +"'>";
                        let center = text.substring(startIndex, startIndex + endIndex)
                        let end = "</span>" + text.substring(startIndex + endIndex);
                        let res = (start + center + end)
                        result = res;
                        if((startIndex + endIndex) > res.length) return res;
                        return  getWord(startIndex + endIndex, i, res, className)
                    }
                    else {
                        if((startIndex + endIndex) > result.length) return result;
                        return getWord(startIndex + endIndex, i, result, className)
                    }
                }
                return result
            }
            return result;
        }
    }
}
// getId('menu').classList.toggle("showMenu")
// getId('menu').style.display = "none";
// getId('btnMenu').addEventListener("click", () => {
//     document.querySelector(".wrapper_left_menu").style.transform = "translateX(0px)";
//     console.log(document.querySelectorAll(".wrapper_left_menu")[0].style.transform)
//     // menu.classList.toggle("showLeftMenu")
// });

