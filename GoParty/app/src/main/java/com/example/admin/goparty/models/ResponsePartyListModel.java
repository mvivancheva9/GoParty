package com.example.admin.goparty.models;

import java.util.LinkedList;
import java.util.List;

public class ResponsePartyListModel {
    List<Party> partyList;


    public List<Party> getPartyList() {
        return partyList;
    }

    public void add(Party party){
        if(partyList == null){
            partyList = new LinkedList<>();
        }
        partyList.add(party);
    }
}
