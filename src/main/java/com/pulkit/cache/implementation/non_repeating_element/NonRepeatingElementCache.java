package com.pulkit.cache.implementation.non_repeating_element;

import com.pulkit.cache.shared.Cache;
import com.pulkit.cache.shared.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class NonRepeatingElementCache implements Cache {
    private DoublyLinkedListNode<Integer> headDLL = null;
    private DoublyLinkedListNode<Integer> tailDLL = null;
    private Map<Integer, DoublyLinkedListNode<Integer>> elementDLLNodeMap = new HashMap<>();

    @Override
    public void add(int streamElement) {
        if (headDLL == null && tailDLL == null) {
            if (elementDLLNodeMap.containsKey(streamElement)) {
                return;
            }

            headDLL = new DoublyLinkedListNode<>(streamElement);
            tailDLL = headDLL;

            elementDLLNodeMap.put(streamElement, headDLL);
            return;
        }

        if (elementDLLNodeMap.containsKey(streamElement)) {
            DoublyLinkedListNode<Integer> streamElementNodeInDLL = elementDLLNodeMap.get(streamElement);

            if (streamElementNodeInDLL == tailDLL
                    && streamElementNodeInDLL == headDLL) {
                tailDLL = null;
                headDLL = null;
                return;
            }

            DoublyLinkedListNode<Integer> previousNode = streamElementNodeInDLL.getPreviousNode();
            DoublyLinkedListNode<Integer> nextNode = streamElementNodeInDLL.getNextNode();

            if (previousNode != null) {
                previousNode.setNextNode(nextNode);
            }

            if (streamElementNodeInDLL == tailDLL) {
                tailDLL = previousNode;
            }

            if (streamElementNodeInDLL == headDLL) {
                headDLL = nextNode;
            }

            streamElementNodeInDLL.makePreviousAndNextReferencesNull();

            if (headDLL != null) {
                return;
            }

            headDLL = new DoublyLinkedListNode<>(streamElement);
            tailDLL = headDLL;
            return;
        }

        DoublyLinkedListNode<Integer> node = new DoublyLinkedListNode<>(streamElement);
        tailDLL.setNextNode(node);
        node.setPreviousNode(tailDLL);
        tailDLL = node;

        elementDLLNodeMap.put(streamElement, node);
        return;
    }

    @Override
    public Integer getElement() throws RuntimeException {
        if (headDLL == null && elementDLLNodeMap.isEmpty()) {
            throw new RuntimeException();
        }

        if (elementDLLNodeMap.isEmpty()) {
            return -1;
        }

        if (headDLL != null) {
            return headDLL.getData();
        }

        return -1;
    }

    @Override
    public void purgeCache() {
        elementDLLNodeMap = new HashMap<>();
        headDLL = null;
        tailDLL = null;
    }
}