package it.unibo.inner.test.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> list = new ArrayList<>();

    public IterableWithPolicyImpl(List<T> elem){
        this.list.addAll(elem);
    }

    private class Inner implements Iterator<T>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < list.size(); 
        }

        @Override
        public T next() {
            return list.get(current++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Inner();
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
    }


}
