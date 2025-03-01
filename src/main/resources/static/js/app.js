async function callRestartEndpoint() {
    let loader = '<div class="loader"></div>';
    document.getElementById('output').innerHTML = loader;

    const username = "user";
    const password = "user";

    // Encode credentials in Base64 format
    const basicAuth = 'Basic ' + btoa(username + ":" + password);

    try {

    let response = await fetch(`/vpn/restart`, {
        method: 'POST',
        headers: {
            "Authorization": basicAuth
        }
    })
    let data = await response.json(); // .json() is asynchronous and therefore must be awaited
    if (data && data.message) {  // Check if data and output are available
        document.getElementById("output").textContent = successMessage;
    } else {
        throw new Error('the response is empty')
    }
    } catch (error) {
        console.error("Error:", error);
        document.getElementById("output").textContent = errorMessage
    }
}

const successMessage = "перезагрузка завершилась успешно .. попробуй переподключиться через пару секунд";
const errorMessage = "что-то пошло не так .. попробуй нажать на кнопку еще раз";