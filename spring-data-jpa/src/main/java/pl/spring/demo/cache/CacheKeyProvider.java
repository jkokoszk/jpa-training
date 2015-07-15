package pl.spring.demo.cache;


import com.google.common.base.Objects;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("cacheKeyProvider")
public class CacheKeyProvider implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        List<Object> hashCodeMembers = new ArrayList<>();
        hashCodeMembers.add(method.getName());
        hashCodeMembers.addAll(Arrays.asList(params));
        return Objects.hashCode(hashCodeMembers);
    }
}


