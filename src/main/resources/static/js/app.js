function callRestartEndpoint() {
    fetch(`/vpn/restart`, {
        method: 'POST'
    })
        .then(() => console.log("finished execution"))
        .then(response => response.json())  // Parse the JSON response
        .then(data => console.log(data))
        .then(data => {
            if (data && data.output) {  // Check if data and output are available
                document.getElementById("output").textContent = data.message;
            } else {
                document.getElementById("output").textContent = errorMessage
            }
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById("output").textContent = errorMessage
        });
}

const errorMessage = "Something went wrong. Try pressing the button again.";