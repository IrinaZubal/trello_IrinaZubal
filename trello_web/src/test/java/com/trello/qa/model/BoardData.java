package com.trello.qa.model;

public class BoardData {
    private String boardName;

//    public BoardData(String boardName) {
//       this.boardName = boardName;
//   }


    public String getBoardName() {
        return boardName;
    }

    public BoardData withBoardName(String boardName) {  //setter
        this.boardName = boardName;
        return this;
    }
}
