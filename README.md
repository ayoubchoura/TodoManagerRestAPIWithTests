# TodoManagerRestAPIWithTests
TodoManagerRestAPIWithTests is a RESTful API built using the Dropwizard Java framework to manage storing and retrieving of Todo items in/from a MySQL data store.

# Todo
The aim was to create a simple API that manages a list of TODO items of the following structure.

A Todo contains an arbitrary list of subtasks and is structured as follows:

{
  
  id
  
  name
  
  description
  
  tasks: [task_name]
  
}

Our Todo Manager Restful-API implements the folowing endpoints:


GET	/TodoManager/todos	Returns a list of all TODOs 

POST	/TodoManager/todos	Expects a TODO (without id) and returns a TODO with ID

GET	/TodoManager/todos/{id}	Returns a TODO /TodoManager

PUT	/TodoManager/todos/{id}	Overwrites an existing TODO /TodoManager

DELETE	/TodoManager/todos/{id}	Deletes a TODO (by id)
