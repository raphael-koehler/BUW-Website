async function fetchData() {
    try {
        const response = await fetch('http://localhost:8080/api');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.text();
        document.getElementById('output').innerHTML = data;
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}
