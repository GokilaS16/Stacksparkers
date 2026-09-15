
function scrollToServices() {
    document.getElementById("services").scrollIntoView({
        behavior: "smooth"
    });
}

function selectService(service) {

    alert(
        "You selected: " + service +
        "\n\nChoose a suitable worker and book the service."
    );

}

function bookJob(job) {

    const confirmBooking = confirm(
        "Do you want to book " + job + "?"
    );

    if (confirmBooking) {
        alert(
            "✅ Booking request sent!\n\n" +
            "Service: " + job +
            "\nStatus: Waiting for worker confirmation."
        );
    }

}
function bookWorker(worker) {

    const confirmBooking = confirm(
        "Book " + worker + " for your service?"
    );

    if (confirmBooking) {

        alert(
            "✅ Booking request sent to " +
            worker +
            "!"
        );

    }

}

function becomeWorker() {

    alert(
        "👷 Worker Registration\n\n" +
        "Worker registration page will open here."
    );

}

function searchBox() {

    const search = prompt(
        "What service are you looking for?"
    );

    if (search && search.trim() !== "") {

        alert(
            "🔍 Searching for: " +
            search +
            "\n\nMatching local services will appear here."
        );

    }

}

function showNotifications() {

    alert(
        "🔔 Notifications\n\n" +
        "• New booking available\n" +
        "• Worker accepted your request\n" +
        "• Payment received"
    );

}

function showAllServices() {

    alert(
        "All Services\n\n" +
        "🌱 Farm & Agri Services\n" +
        "🏠 Home Services\n" +
        "🔧 Repair & Maintenance\n" +
        "👥 Community Services\n" +
        "🚚 Delivery & Transport"
    );

}

function showAllJobs() {

    alert(
        "📋 More jobs will be displayed here."
    );

}

function viewWorkers() {

    alert(
        "👥 Nearby worker directory will open here."
    );

}

const navLinks = document.querySelectorAll("nav a");

navLinks.forEach(link => {

    link.addEventListener("click", function() {

        navLinks.forEach(item => {
            item.classList.remove("active");
        });

        this.classList.add("active");

    });

});