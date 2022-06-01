
let getById = id => document.getElementById(id);

getById('btnMenu').addEventListener('click', () => getById('menu').classList.toggle("showMenu"));

window.addEventListener(`scroll`, (event) => {
    if(window.scrollY > 110) getById('up').style.display = 'block';
    else getById('up').style.display = 'none'
});

getById('up').addEventListener('click', () =>{
    window.scroll({top:0, behavior:`smooth`})
});

getById('About_Me').addEventListener('click', () => {
    setScroll(1)
})

getById('Language').addEventListener('click', () => {
   setScroll(3)
})

getById('Resume').addEventListener('click', () => {
    setScroll(4)
})

getById('Skills').addEventListener('click', () => {
    setScroll(5)
})

// getById('Projects').addEventListener('click', () => {
//     setScroll(6)
// })

getById('Contact').addEventListener('click', () => {
    setScroll(6)
})

getById('learn-more').addEventListener('click', ()=>{
    setScroll(1)
})

getById('alert_wrapper').addEventListener('click', () => {
    getById('alert_wrapper').style.display = 'none'
    getById('alert_loading_img').style.display = 'none'
    getById('alert').style.display = 'none'
})

function setScroll(id){
    let elements = document.querySelectorAll(".sc");
    let height = 0;
    for (let i = 0; i < elements.length; i++) {
        if(elements[i] === elements[id]) break
        height += getFullHeightElem(elements[i]);
    }
    height -=40;
    window.scroll({top:height, behavior:`smooth`})
    // console.log(height)
}

function getFullHeightElem(element){
    let marginLeft = parseInt(getComputedStyle(element, true).marginTop);
    let marginRight = parseInt(getComputedStyle(element, true).marginBottom);
    console.log(element.offsetHeight + marginLeft + marginRight)
    return element.offsetHeight + marginLeft + marginRight;
}

getById('send-message').addEventListener('click',async () => {
    getById('alert_wrapper').style.display = 'flex'
    getById('alert_loading_img').style.display = 'flex'
    let message = {
        name: getById('name').value,
        email: getById('email').value,
        message: getById('message').value
    }

    const url = 'http://localhost:8080/send-email';
    try {
        const response = await fetch(url, {
            method: 'POST',
            body: JSON.stringify(message),
            headers: {
                'Content-Type': 'application/json',
                'charset': 'UTF-8'
            }
        });

        const json = await response;
        getById('alert_loading_img').style.display = 'none'

        if(json.status === 200){
            getById('alert').style.display = 'flex'
            getById('alert_text').innerText = "Message was send"
        }
        else{
            getById('alert').style.display = 'flex'
            getById('alert_text').innerText = "Message not send"
        }
    } catch (error) {

    }
})




