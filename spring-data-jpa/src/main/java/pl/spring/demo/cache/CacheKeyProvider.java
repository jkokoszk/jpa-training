package pl.spring.demo.cache;


import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CacheKeyProvider {

    // do not instantiate
    private CacheKeyProvider() {
        throw new AssertionError();
    }

    public static int generateKey(String methodName, Object[] methodArgs) {
        List<Object> hashCodeMembers = new ArrayList<>();
        hashCodeMembers.add(methodName);
        hashCodeMembers.addAll(Arrays.asList(methodArgs));
        return Objects.hashCode(hashCodeMembers);
    }
}


