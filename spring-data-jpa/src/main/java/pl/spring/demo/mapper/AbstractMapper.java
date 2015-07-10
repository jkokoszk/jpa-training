package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<S, T> {

    public abstract T mapSource(S source);

    public abstract S mapTarget(T target);

    public List<T> mapSourceCollection(Collection<S> collection) {
        return collection.stream().map(this :: mapSource).collect(Collectors.toList());
    }

    public List<S> mapTargetCollection(Collection<T> collection) {
        return collection.stream().map(this :: mapTarget).collect(Collectors.toList());
    }

}
