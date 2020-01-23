package com.pulkit.cache.implementation.lru;

import com.pulkit.cache.shared.Cache;
import com.pulkit.cache.shared.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class LRUCache implements Cache {
    private DoublyLinkedListNode<Integer> headDLL = null;
    private DoublyLinkedListNode<Integer> tailDLL = null;
    private Map<Integer, DoublyLinkedListNode<Integer>> keyNodeMap = new HashMap<>();
    private int cacheSize = 0;

    private static final int EVICTION_THRESHOLD = 10;

    @Override
    public void add(int streamElement) {
        if (cacheSize == EVICTION_THRESHOLD) {
            evictLeastRecentlyUsedElement();
        }

        if (headDLL == null && tailDLL == null) {
            addFirstElement(streamElement);
            return;
        }

        DoublyLinkedListNode<Integer> node;

        if (keyNodeMap.containsKey(streamElement)) {
            node = keyNodeMap.get(streamElement);
        } else {
            node = new DoublyLinkedListNode<>(streamElement);
            cacheSize++;
        }

        if (keyNodeMap.containsKey(streamElement)) {
            putElementToBeAddedAtHead(node);
        }

        node.setPreviousNode(null);
        node.setNextNode(headDLL);

        headDLL.setPreviousNode(node);
        headDLL = node;
    }

    @Override
    public void purgeCache() {
        keyNodeMap = new HashMap<>();
        headDLL = null;
        tailDLL = null;
    }

    @Override
    public Integer getElement() {
        if (headDLL != null)
            return headDLL.getData();
        return -1;
    }

    private void putElementToBeAddedAtHead(DoublyLinkedListNode<Integer> node) {
        DoublyLinkedListNode<Integer> previousNode = node.getPreviousNode();
        DoublyLinkedListNode<Integer> nextNode = node.getNextNode();

        if (previousNode != null) {
            previousNode.setNextNode(nextNode);
        }

        if (nextNode != null) {
            nextNode.setPreviousNode(previousNode);
        }
    }

    private void addFirstElement(int streamElement) {
        DoublyLinkedListNode<Integer> node = new DoublyLinkedListNode<>(streamElement);
        cacheSize++;

        headDLL = node;
        tailDLL = node;

        keyNodeMap.put(streamElement, node);
        return;
    }

    private void evictLeastRecentlyUsedElement() {
        out.println("Evicting least recently used element " + tailDLL.getData());
        DoublyLinkedListNode<Integer> previousNode = tailDLL.getPreviousNode();
        previousNode.setNextNode(null);
        tailDLL.setPreviousNode(null);
        tailDLL = previousNode;
    }
}