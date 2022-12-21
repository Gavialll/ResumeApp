let getById = id => document.getElementById(id);

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

getById('ForExample').addEventListener('click', () => {
    window.location.href = "/for_example";
})

getById('Contact').addEventListener('click', () => {
    localStorage.setItem("id", 7);
    window.location.href = "/";
})