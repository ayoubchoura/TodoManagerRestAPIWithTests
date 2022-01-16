package com;

import com.dao.TodoDao;
import com.entities.Todo;
import com.resources.TodoManagerResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TodoManagerApplication extends Application<TodoManagerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TodoManagerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(final Bootstrap<TodoManagerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final TodoManagerConfiguration configuration,
                    final Environment environment) {

        final TodoDao dao = new TodoDao(hibernate.getSessionFactory());
        environment.jersey().register(new TodoManagerResource(dao));
    }

    private final HibernateBundle<TodoManagerConfiguration> hibernate = new HibernateBundle<TodoManagerConfiguration>(Todo.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(TodoManagerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
}




