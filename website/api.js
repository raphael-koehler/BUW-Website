const baseUrl = 'http://localhost:8080/recipe'

async function show() {
    const data = await getAllRecipes();
    document.getElementById('output').innerHTML = data;
}

async function getAllRecipes() {
    try {
        const response = await fetch(baseUrl);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const recipes = await response.json();
        return JSON.stringify(recipes);
    } catch (error) {
        console.error('Error fetching recipes:', error);
    }
}

async function getRecipeById(id) {
    try {
        const response = await fetch(`${baseUrl}/${id}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const recipe = await response.json();
        return recipe;
    } catch (error) {
        console.error('Error fetching recipe:', error);
    }
}

async function addRecipe(title, ingredients, instructions) {
    const newRecipe = { title, ingredients, instructions };
    try {
        const response = await fetch(baseUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newRecipe),
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const message = await response.text();
    } catch (error) {
        console.error('Error adding recipe:', error);
    }
}

async function removeRecipe(id) {
    try {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
    } catch (error) {
        console.error('Error removing recipe:', error);
    }
}
