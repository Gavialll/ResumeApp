let getById = id => document.getElementById(id);

getById('learn-more').addEventListener('click', () => {
    setScroll(1)
})

function setScroll(id) {
    let elements = document.querySelectorAll(".sc");
    let height = 0;
    for (let i = 0; i < elements.length; i++) {
        if (elements[i] === elements[id]) break
        height += getFullHeightElem(elements[i]);
    }
    height -= 40;
    window.scroll({top: height, behavior: `smooth`})
}

function getFullHeightElem(element) {
    let marginLeft = parseInt(getComputedStyle(element, true).marginTop);
    let marginRight = parseInt(getComputedStyle(element, true).marginBottom);
    return element.offsetHeight + marginLeft + marginRight;
}

getById('About_Me').addEventListener('click', () => {
    localStorage.setItem("id", 1);
    window.location.href = "/";
})

getById('Language').addEventListener('click', () => {
    localStorage.setItem("id", 3);
    window.location.href = "/";
})

getById('Resume').addEventListener('click', () => {
    localStorage.setItem("id", 4);
    window.location.href = "/";
})

getById('Skills').addEventListener('click', () => {
    localStorage.setItem("id", 5);
    window.location.href = "/";
})

getById('Projects').addEventListener('click', () => {
    localStorage.setItem("id", 6);
    window.location.href = "/";
})

getById('Contact').addEventListener('click', () => {
    localStorage.setItem("id", 7);
    window.location.href = "/";
})