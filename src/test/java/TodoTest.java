import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.entities.Todo;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TodoTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        List<String> listtasks1= new ArrayList<>();
        listtasks1.add("task1");
        listtasks1.add("task2");
        final Todo todo = new Todo((long) 1,"todo1", "desc1",listtasks1);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/Todo.json"), Todo.class));

        assertThat(MAPPER.writeValueAsString(todo)).isEqualTo(expected);
    }
    @Test
    public void deserializesFromJSON() throws Exception {
        List<String> listtasks1= new ArrayList<>();
        listtasks1.add("task1");
        listtasks1.add("task2");
        final Todo todo = new Todo(1L,"todo1", "desc1",listtasks1);
        assertThat(MAPPER.readValue(fixture("fixtures/Todo.json"), Todo.class).getId())
                .isEqualTo(todo.getId());
        assertThat(MAPPER.readValue(fixture("fixtures/Todo.json"), Todo.class).getName())
                .isEqualTo(todo.getName());
        assertThat(MAPPER.readValue(fixture("fixtures/Todo.json"), Todo.class).getDescription())
                .isEqualTo(todo.getDescription());
        assertThat(MAPPER.readValue(fixture("fixtures/Todo.json"), Todo.class).getTasks())
                .isEqualTo(todo.getTasks());
    }



}
