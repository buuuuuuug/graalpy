package com.chaney.infra.graalpy;

import org.hibernate.community.dialect.SQLiteDialect;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class DatabaseRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Change this to use your database Dialect class when using other databases
        hints
                .reflection()
                .registerType(SQLiteDialect.class, MemberCategory.values());
    }
}
