package pl.spring.demo.derby;

import org.hibernate.dialect.DerbyTenSevenDialect;

import java.sql.Types;

/**
 * Fixed dialect for Derby DB (clob type included)
 */
public class DerbyDialect extends DerbyTenSevenDialect {

    public DerbyDialect() {
        super();
        registerColumnType(Types.CLOB, "clob");
    }
}
