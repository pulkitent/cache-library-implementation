package com.pulkit.cache.shared;

public class DoublyLinkedListNode<T> {
    private final T data;
    private DoublyLinkedListNode<T> next;
    private DoublyLinkedListNode<T> previous;

    public DoublyLinkedListNode(T data) {
        this.data = data;
    }

    public void makePreviousAndNextReferencesNull() {
        this.next = null;
        this.previous = null;
    }

    public void setNextNode(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    public void setPreviousNode(DoublyLinkedListNode<T> previous) {
        this.previous = previous;
    }

    public DoublyLinkedListNode<T> getNextNode() {
        return next;
    }

    public DoublyLinkedListNode<T> getPreviousNode() {
        return previous;
    }

    public T getData() {
        return data;
    }
}