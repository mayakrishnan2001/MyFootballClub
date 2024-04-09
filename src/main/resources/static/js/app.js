document.addEventListener("DOMContentLoaded", function() {
    const playerForm = document.getElementById("playerForm");

    playerForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent the default form submission behavior

        // Get form data
        const playerNumber = document.getElementById("playerNumber").value;
        const playerName = document.getElementById("playerName").value;
        const age = document.getElementById("age").value;
        const position = document.getElementById("position").value;

        // Create player object
        const playerData = {
            playerNumber: playerNumber,
            playerName: playerName,
            age: age,
            position: position
        };

        // Send POST request to server
        fetch("/api/players", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(playerData)
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
