package it.unibo.inner.test.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> list = new ArrayList<>();
    private Predicate<T> filter = null;

    /* public IterableWithPolicyImpl(List<T> list){
        this.list.addAll(list);
    } */
    
    public IterableWithPolicyImpl(List<T> list){
        this(list, new Predicate<T>() {
            @Override
            public boolean test(T t){
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(List<T> list, Predicate<T> filter){
        this.list.addAll(list);
        this.filter = filter;
    } 

    private class Inner implements Iterator<T>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            while (current < list.size()) {
                if (filter.test(list.get(current))) {
                    return true;
                } else {
                    current++;
                }
            }
            return false; 
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                return list.get(current++);
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Inner();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }


}
