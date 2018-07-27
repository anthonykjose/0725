package com.companyseventwentyfive;

import java.util.UUID;

public class Main {

    public static void main(String[] args){
        ChangRoberts head = new ChangRoberts();
        ChangRoberts node = head;
        for (int i = 0; i < 100; i++) {
            node.nextNeighbour = new ChangRoberts();
            node = node.nextNeighbour;
        }
        node.nextNeighbour = head;

        head.startElection();
    }


}
