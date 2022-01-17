import com.dao.TodoDao;
import com.entities.Todo;
import com.resources.TodoManagerResource;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.persistence.TemporalType;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TodoResourceTest {
    private static final TodoDao DAO = mock(TodoDao.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .addResource(new TodoManagerResource(DAO))
            .build();

    private Todo todo;
    private ArgumentCaptor<Todo> todoCaptor = ArgumentCaptor.forClass(Todo.class);


    @BeforeEach
    void setup() {
        todo = new Todo();
        todo.setId((long) 1);
    }

    @AfterEach
    void tearDown() {
        reset(DAO);
    }

    @Test
    void getTodo() {

        when(DAO.findTodoById((long) 1)).thenReturn(todo);
        Todo found = EXT.target("/TodoManager/todos/1").request().get(Todo.class);
        assertThat(found.getId()).isEqualTo(todo.getId());
        verify(DAO).findTodoById((long)1);

    }


    @Test
    void getAllTodos() {

        final List<Todo> todos = new ArrayList<>(Collections.singletonList(todo));
        when(DAO.findAllTodos()).thenReturn(todos);

        final List<Todo> response = EXT.target("/TodoManager/todos/")
                .request().get(new GenericType<List<Todo>>() {
                });

        verify(DAO).findAllTodos();
        assertThat(response.size()).isEqualTo(todos.size());
        assertThat(response.get(0).getId()).isEqualTo(todos.get(0).getId());
        assertThat(response.containsAll(todos));

    }

    @Test
    void postTodo() {

        when(DAO.addNewTodo(any(Todo.class))).thenReturn(todo);
        final Response response = EXT.target("/TodoManager/todos/")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(todo, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(DAO).addNewTodo(todoCaptor.capture());
        assertThat(todoCaptor.getValue().getId()).isEqualTo(todo.getId());
        assertThat(todoCaptor.getValue().getName()).isEqualTo(todo.getName());
        assertThat(todoCaptor.getValue().getDescription()).isEqualTo(todo.getDescription());
        assertThat(todoCaptor.getValue().getTasks()).isEqualTo(todo.getTasks());
    }

    @Test
    void putTodo() {

        List<String> listtasks1= new ArrayList<>();
        listtasks1.add("task11");
        listtasks1.add("task12");
        final Todo todo1 = new Todo(2L,"todo1", "desc1",listtasks1);
        List<String> listtasks2= new ArrayList<>();
        listtasks1.add("task21");
        listtasks1.add("task22");
        final Todo todo2 = new Todo(3L,"todo2", "desc2",listtasks2);
        when(DAO.findTodoById(2L)).thenReturn(todo1);
        when(DAO.addNewTodo(todo1)).thenReturn(todo);
        final Response response = EXT.target("/TodoManager/todos/2")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(todo, MediaType.APPLICATION_JSON_TYPE));
        verify(DAO).addNewTodo(todo1);
        assertThat(todo1.getName()).isEqualTo(todo.getName());
        assertThat(todo1.getDescription()).isEqualTo(todo.getDescription());
        assertThat(todo1.getTasks()).isEqualTo(todo.getTasks());
    }

    @Test
    void deleteTodo() {

        when(DAO.findTodoById(1L)).thenReturn(todo);
        final Response response = EXT.target("/TodoManager/todos/1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();
        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.NO_CONTENT);
        verify(DAO).delTodo(todo);

    }




















}
