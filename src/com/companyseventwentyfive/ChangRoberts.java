package com.companyseventwentyfive;

import java.util.UUID;

public class ChangRoberts {
    private boolean isParticipant;
    private String uid;
    private String electedUid;
    private boolean isLeader;

    public ChangRoberts() {
        this.uid = UUID.randomUUID().toString();
        this.isParticipant = false;
        this.isLeader = false;
        this.electedUid = null;
    }

    public ChangRoberts nextNeighbour;

    public void startElection() {
        ElectionMessage m = new ElectionMessage();
        m.type = MessageType.ELECTION;
        m.uid = this.uid;
        this.isParticipant = true;
        this.nextNeighbour.put(m);
    }

    public void put(ElectionMessage electionMessage) {
        if(electionMessage.type == MessageType.ELECTION) {
            if(electionMessage.uid.compareTo(this.uid) > 0) {
                nextNeighbour.put(electionMessage);
            }
            else if(electionMessage.uid.equals(this.uid)) {
                isParticipant = false;
                isLeader = true;
                electionMessage.type = MessageType.ELECTED;
                nextNeighbour.put(electionMessage);
            }
            else if(!isParticipant) {
                electionMessage.uid = this.uid;
                this.isParticipant = true;
                nextNeighbour.put(electionMessage);
            }
        }
        else {
            if(isLeader) {
                System.out.println("Election is over. Leader is " + this.uid);
                return;
            }

            isParticipant = false;
            this.electedUid = electionMessage.uid;
            System.out.println("Our leader is " + this.electedUid);
            nextNeighbour.put(electionMessage);
        }

    }
}
