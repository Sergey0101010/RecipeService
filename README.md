# RecipesService 
Cooking recipes service.

**Stack:** Java 17, Spring boot, Spring Security, Hibernate, MySQL, Gradle.
## Description.
To access this service, registration is required, which is available to everyone on request `POST /api/register`. 
After registration, the following functions are available to the user:
- Getting a specific recipe from the database: `GET /api/recipe/{id}`
- Adding a new recipe: `POST /api/recipe/new`
- Deleting a recipe only by the user who added it: `DELETE /api/recipe/{id}`
- Updating a recipe by the user who added it: `PUT /api/recipe/{id}`
- Search for a recipe by category/word in its name: `GET /api/recipe/search`
