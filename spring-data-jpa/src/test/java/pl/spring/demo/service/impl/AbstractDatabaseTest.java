package pl.spring.demo.service.impl;

import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {"classpath:test_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:drop_database.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public abstract class AbstractDatabaseTest {
}



