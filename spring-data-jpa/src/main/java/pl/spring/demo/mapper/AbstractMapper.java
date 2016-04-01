package pl.spring.demo.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractMapper<S, T> {

    public abstract T convertToBookTo(S source);

    public abstract S convertToBook(T target);

    public Collection<T> mapSourceCollection(Collection<S> collection) {
        return collection.stream().map(this::convertToBookTo).collect(Collectors.toList());
    }

    public Collection<S> mapTargetCollection(Collection<T> collection) {
        return collection.stream().map(this::convertToBook).collect(Collectors.toList());
    }

}
