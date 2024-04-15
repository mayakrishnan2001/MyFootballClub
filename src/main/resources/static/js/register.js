document.addEventListener("DOMContentLoaded", function() {
    const playerForm = document.getElementById("playerForm");

    playerForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent the default form submission behavior

        // Get form data
        const formData = new FormData(playerForm);
        const playerData = {
            playerNumber: formData.get("playerNumber"),
            playerName: formData.get("playerName"),
            age: formData.get("age"),
            position: formData.get("position")
        };

        // Get the file input
        const fileInput = document.getElementById("image");
        const file = fileInput.files[0]; // Get the first file (assuming single file upload)

        // Append the file to the FormData object
        formData.append("image", file);

        // Send POST request to server
        fetch("/api/players", {
            method: "POST",
            body: formData
        })
        .then(response => {
            if (response.ok) {
                console.log("Player added successfully");
                // Optionally, perform actions on success (e.g., show success message, redirect)
            } else {
                console.error("Failed to add player");
                // Optionally, handle errors (e.g., show error message)
            }
        })
        .catch(error => {
            console.error("Error adding player:", error);
            // Optionally, handle errors (e.g., show error message)
        });
    });
});
