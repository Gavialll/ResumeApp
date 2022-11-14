let getById = id => document.getElementById(id);

/** Scroll navigation of menu ---------------------------------------------------*/
let elemId = localStorage.getItem('id');
if(elemId > 0){
    setScroll(elemId)
    localStorage.setItem('id', -1);
}

getById('About_Me').addEventListener('click', () => {
    setScroll(1)
    getById('menu').classList.toggle("showMenu")
})

getById('Language').addEventListener('click', () => {
    setScroll(3)
    getById('menu').classList.toggle("showMenu")
})

getById('Resume').addEventListener('click', () => {
    setScroll(4)
    getById('menu').classList.toggle("showMenu")
})

getById('Skills').addEventListener('click', () => {
    setScroll(5)
    getById('menu').classList.toggle("showMenu")
})

getById('ForExample').addEventListener('click', () => {
    window.location.href = "/for_example";
})

getById('Contact').addEventListener('click', () => {
    setScroll(6)
    getById('menu').classList.toggle("showMenu")
})

getById('learn-more').addEventListener('click', () => {
    setScroll(1)
})
/**------------------------------------------------------------------------------*/

/** Send location ---------------------------------------------------------------*/
    sendUserPosition();
/** -----------------------------------------------------------------------------*/

/** Send email ------------------------------------------------------------------*/
getById('send-message').addEventListener('click', async () => {
    let count = 0;

    if (getById('name').value.length > 1) count++;
    else getById('name').style.borderBottom = "1px solid red";

    if (getById('message').value.length > 5) count++;
    else getById('message').style.border = "1px solid red";

    console.log(validateEmail(getById('email').value))
    if (validateEmail(getById('email').value)) count++;
    else getById('email').style.borderBottom = "1px solid red"

    if (count === 3) {
        getById('alert_wrapper').style.display = 'flex'
        getById('alert_loading_img').style.display = 'flex'

        let message = {
            name: getById('name').value,
            email: getById('email').value,
            message: getById('message').value
        }

        const url = '/send-email';
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

        getById('alert_wrapper').addEventListener('click', addListener)

        if (json.status === 200) {
            getById('alert').style.display = 'flex'
            getById('alert_text').innerText = "Message was send"
        } else {
            getById('alert').style.display = 'flex'
            getById('alert_text').innerText = "Message not send"
        }
    }
})

getById("email").addEventListener("input", () => {
    let email = getById('email');

    if (validateEmail(email.value)) {
        email.style.borderBottom = "1px solid green"
    } else {
        email.style.borderBottom = "1px solid red"
    }
})

getById("name").addEventListener("input", () => {
    let name = getById('name');

    if (name.value.length > 1) {
        name.style.borderBottom = "1px solid green"
    } else {
        name.style.borderBottom = "1px solid red"
    }
})

getById("message").addEventListener("input", () => {
    let message = getById('message');

    if (message.value.length > 5) {
        message.style.border = "1px solid green"
    } else {
        message.style.border = "1px solid red"
    }
})

const validateEmail = (email) => {
    return String(email)
        .toLowerCase()
        .match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
};
/**------------------------------------------------------------------------------*/



/** Function --------------------------------------------------------------------*/
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

function addListener() {
    getById('alert_wrapper').style.display = 'none'
    getById('alert_loading_img').style.display = 'none'
    getById('alert').style.display = 'none'
    getById('alert_wrapper').removeEventListener('click', addListener)
    getById('name').value = ""
    getById('email').value = ""
    getById('message').value = ""
    getById('name').style.borderBottom = "1px solid #b2b2b2";
    getById('email').style.borderBottom = "1px solid #b2b2b2";
    getById('message').style.border = "1px solid #b2b2b2";
}

function sendEmailYouHaveNewVisitor(location) {

    const url = '/addVisitor';
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'charset': 'UTF-8'
        },
        body: JSON.stringify(location)
    });
}

async function sendUserPosition() {
    let lat;
    let lon;
    navigator.geolocation.getCurrentPosition( pos => {
        lat = pos.coords.latitude;
        lon = pos.coords.longitude
        fetch("https://api.geoapify.com/v1/geocode/reverse?lat=" + lat + "&lon=" + lon + "&apiKey=1419b4e9e48a46139c62efbfe5cdcad2", requestOptions)
            .then(response => response.json())
            .then(result => {
                sendEmailYouHaveNewVisitor({
                    city: result.features[0].properties.city,
                    country: result.features[0].properties.country,
                    street: result.features[0].properties.street
                })
            })
            .catch(error => console.log('Location not found', error));
    }, () => console.log("Location not found"));

    var requestOptions = {
        method: 'GET',
    };
}
/**------------------------------------------------------------------------------*/








