async function callRestartEndpoint() {
    let loader = '<div class="loader"></div>';
    document.getElementById('output').innerHTML = loader;

    const username = "user";
    const password = "user";

    // Encode credentials in Base64 format
    const basicAuth = 'Basic ' + btoa(username + ":" + password);

    const userTimezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
    console.log("tmz: ", userTimezone)

    try {

    let response = await fetch(`/vpn/restart`, {
        method: 'POST',
        headers: {
            "Authorization": basicAuth,
            "X-Timezone": userTimezone
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


async function fetchHistory() {
    try {
        const response = await fetch('/vpn/history');
        const data = await response.json();

        const tbody = document.getElementById('history-table-body');
        if (!tbody) return; // Avoid errors if element is missing

        tbody.innerHTML = ''; // Clear previous rows

        data.forEach(item => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${item.username}</td>
                <td>${item.status}</td>
                <td>${item.started}</td>
                <td>${item.finished}</td>
            `;

            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching history:', error);
    }
}

// Initial and periodic call
document.addEventListener('DOMContentLoaded', () => {
    fetchHistory();
    setInterval(fetchHistory, 5000);
});
