package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWIthPolicyImpl<T> implements IterableWithPolicy {


    private T[] array;
    private Predicate<T> filter;

    public IterableWIthPolicyImpl(T[] array){
        this(array, (x)->true);
    }

    public IterableWIthPolicyImpl(T[] array, Predicate<T> filter){
        this.array = array;
        this.filter = filter;
    }

    @Override
    public Iterator iterator() {
        return new IterableWithPolicyIterator(0);
    }

    public void setIterationPolicy(Predicate f) {
        this.filter = f;
    }

    private class IterableWithPolicyIterator implements Iterator<T>{

        private int current;

        public IterableWithPolicyIterator(final int current){
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            int temp = current;
            while(temp<(IterableWIthPolicyImpl.this.array.length)){
                if(filter.test(array[temp])){
                    return true;
                }
                temp++;
            }
            return false;
        }

        @Override
        public T next() {
            while(hasNext()){
                if(filter.test(array[(current)])){
                    this.current++;
                    return IterableWIthPolicyImpl.this.array[current-1];
                }
                this.current++;
            }
            throw new NoSuchElementException();
        }

        public String toString(){
            String s = "[";
            for (T t : array) {
                s.concat(t+",");
            }
            s.concat("]");
            return s;
        }

    }
    
}
