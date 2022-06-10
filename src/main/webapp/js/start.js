let getId = id => document.getElementById(id);

getId('btnMenu').addEventListener('click', () => getId('menu').classList.toggle("showMenu"));

window.addEventListener(`scroll`, (event) => {
    if (window.scrollY > 110) getId('up').style.display = 'block';
    else getId('up').style.display = 'none'
});

getId('up').addEventListener('click', () => {
    window.scroll({top: 0, behavior: `smooth`})
});

